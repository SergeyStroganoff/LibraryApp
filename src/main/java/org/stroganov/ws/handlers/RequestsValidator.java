package org.stroganov.ws.handlers;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;
import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;

import javax.xml.namespace.QName;
import java.util.*;

public class RequestsValidator implements SOAPHandler<SOAPMessageContext> {
    private final Logger logger = Logger.getLogger(RequestsValidator.class);
    private final List<String> adminRequests = Arrays.asList("ns2:deleteUser", "ns2:blockUser", "ns2:unblockUser", "ns2:getAllHistory", "ns2:addUser");
    private User user = null;

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        //for response message only, true for outbound messages, false for inbound
        if (!isRequest) {
            try {
                SOAPMessage soapMsg = context.getMessage();
                String requestName = soapMsg.getSOAPBody().getLastChild().getNodeName();
                System.out.println(requestName);
                if (requestName.equals("ns2:findUser")) {
                    System.out.println("Handler was missed");
                    logger.info("Handler was missed");
                    return true;
                }
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();
                //if no header, add one
                if (soapHeader == null) {
                    soapHeader = soapEnv.addHeader();
                    //throw exception
                    System.out.println("No SOAP header.");
                    generateSOAPErrMessage(soapMsg, "No SOAP header.");
                }
                Iterator<SOAPHeaderElement> it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                //if no header block for next actor found? throw exception
                if (it == null || !it.hasNext()) {
                    System.out.println("No header block for next actor.");
                    generateSOAPErrMessage(soapMsg, "No header block for next actor.");
                }

                assert it != null;
                Node loginNode = it.next();
                System.out.println(loginNode.getNodeName());
                String userLogin = (loginNode == null) ? null : loginNode.getValue();
                Node passNode = it.next();
                System.out.println(loginNode.getAttributes());
                String password = (passNode == null) ? null : passNode.getValue();
                System.out.println("We got credentials:" + userLogin + "&&" + password);
                logger.info("We got credentials:" + userLogin + " && " + password);
                if (userLogin == null || password == null) {
                    generateSOAPErrMessage(soapMsg, "NO Password");
                    return false;
                }

                if (checkUser(userLogin, password)) {
                    if (adminRequests.contains(requestName) && !hasUserAdminStatus(userLogin)) {
                        generateSOAPErrMessage(soapMsg, "permission denied");
                        return false;
                    }
                    return true;
                }
                generateSOAPErrMessage(soapMsg, "Не удалось получить правильный пароль:" + userLogin + "&&" + password);
                return false;
            } catch (SOAPException e) {
                logger.error(e);
            }
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {
        //not realized
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
            logger.error(e);
        }
    }

    private boolean hasUserAdminStatus(String userLogin) {
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        if (user == null) {
            libraryDAO.findUser(userLogin);
        }
        if (user != null) {
            return user.isAdmin();
        } else return false;
    }

    private boolean checkUser(String userLogin, String password) {
        if (userLogin == null || password == null) {
            return false;
        }
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();
        user = libraryDAO.findUser(userLogin);
        if (user == null) {
            return false;
        }
        return user.getPasscodeHash().equals(password);
    }
}
