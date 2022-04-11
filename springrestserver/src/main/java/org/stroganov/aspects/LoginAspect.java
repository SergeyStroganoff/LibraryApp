package org.stroganov.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.stroganov.entities.User;
import org.stroganov.exceptions.HistorySavingException;
import org.stroganov.models.AuthenticationRequestDto;
import org.stroganov.models.HistoryDTO;
import org.stroganov.models.UserDTO;
import org.stroganov.servise.HistoryService;
import org.stroganov.servise.UserService;

import java.util.Optional;

@Aspect
@Component
@Log4j2
public class LoginAspect {
    private final HistoryService historyService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public LoginAspect(HistoryService historyService, UserService userService, ModelMapper modelMapper) {
        this.historyService = historyService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Pointcut("within(org.stroganov.controllers.*)")
    public void callAnyControllers() {
    }

    @Pointcut("@annotation(AuthLoggable) && args(authenticationRequestDto,..)")
    public void callAuthControllers(AuthenticationRequestDto authenticationRequestDto) {
    }

    @AfterReturning(pointcut = "callAuthControllers(authenticationRequestDto)", returning = "retVal", argNames = "joinPoint,authenticationRequestDto,retVal")
    public void logAuthentication(JoinPoint joinPoint, AuthenticationRequestDto authenticationRequestDto, ResponseEntity<String> retVal) {

        User incomingUser = null;
        Optional<User> userOptional = userService.findUserByUserLogin(authenticationRequestDto.getLogin());
        if (userOptional.isPresent()) {
            incomingUser = userOptional.get();
        } else
            incomingUser = userService.findUserByUserLogin("notAuthoredUser").get();
        UserDTO userDTO = modelMapper.map(incomingUser, UserDTO.class);
        String event = retVal.getStatusCode().is2xxSuccessful() ? "User have got authentication successfully"
                : "User attempted to get authentication  not successfully from" + retVal.getHeaders().getHost();
        HistoryDTO historyDTO = new HistoryDTO(userDTO, event);
        try {
            historyService.historyEventSave(historyDTO);
        } catch (HistorySavingException e) {
            log.error(e.getMessage());
        }
    }

    @After("callAnyControllers()")
    public Object logExecutionTime(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        UserDTO userDTO = null;
        if (CurrentPrincipal.getPrincipalName() != null) {
            Optional<User> userOptional = userService.findUserByUserLogin(CurrentPrincipal.getPrincipalName());
            if (userOptional.isPresent()) {
                userDTO = modelMapper.map(userOptional.get(), UserDTO.class);
            }
        }
        HistoryDTO historyDTO = new HistoryDTO(userDTO, methodName);
        try {
            historyService.historyEventSave(historyDTO);
        } catch (HistorySavingException e) {
            log.error(e.getMessage());
        }
        return joinPoint.getThis();
    }
}
