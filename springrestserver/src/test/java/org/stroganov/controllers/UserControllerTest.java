package org.stroganov.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.stroganov.entities.User;
import org.stroganov.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Disabled //TODO
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    private static final String BASE_PATH = "/api";

    @Test
    void when_saveUser_then_findThisUser_and_return_Ok() throws Exception {
        //GIVEN
        User user = new User(0, "OnlyTest", "onlyTest", "123", false, false);
        User savedUser = userRepository.save(user);
        //WHEN
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_PATH + "/admin/user/" + savedUser.getLogin()));
        //THEN
        String response = resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        User userFromServer = objectMapper.readValue(response, User.class);
        assertThat(userFromServer).isEqualTo(savedUser);
        userRepository.delete(savedUser);
    }

    @Test
    void deleteUser() throws Exception {
        //GIVEN
        User user = new User(0, "OnlyTest", "onlyTest", "123", false, false);
        User savedUser = userRepository.save(user);
        userRepository.delete(savedUser);
        //WHEN
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_PATH + "/admin/user/" + savedUser.getLogin()));
        //THEN
        String response = resultActions
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void when_addUser_then_getOKStatus() throws Exception {
        //GIVEN
        User user = new User(0, "OnlyTest", "onlyTest", "123", false, false);
        ObjectMapper objectMapper = new ObjectMapper();
        // WHEN
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_PATH + "/admin/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
        );
        // THEN
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk());
        userRepository.delete(user);
    }
}