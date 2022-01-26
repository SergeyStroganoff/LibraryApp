package org.stroganov.ws.handlers;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;

import java.util.Iterator;

public class AdminValidator extends UserValidator {
    public static final String CREDENTIALS_ERROR = "Не удалось получить правильный пароль для пользователя:";
    private final Logger logger = Logger.getLogger(AdminValidator.class);

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isRequest) {
            try {
                SOAPMessage soapMsg = context.getMessage();
                String requestName = soapMsg.getSOAPBody().getLastChild().getNodeName();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                if (soapHeader == null) {
                    soapHeader = soapEnv.addHeader();
                    generateSOAPErrMessage(soapMsg, "No SOAP header.");
                }
                Iterator<SOAPHeaderElement> it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                if (it == null || !it.hasNext()) {
                    generateSOAPErrMessage(soapMsg, "No header block for next actor.");
                }
                String userLogin = null;
                String password = null;
                assert it != null;
                while (it.hasNext()) {
                    Node node = it.next();
                    if (node.getNodeName().equals("login")) {
                        userLogin = node.getValue();
                    }
                    if (node.getNodeName().equals("password")) {
                        password = node.getValue();
                    }
                }
                if (userLogin == null || password == null) {
                    generateSOAPErrMessage(soapMsg, "No Password");
                    return false;
                }
                if (checkUser(userLogin, password)) {
                    if (RequestTypes.contains(requestName) && !hasUserAdminStatus(userLogin)) {
                        generateSOAPErrMessage(soapMsg, "permission denied");
                        return false;
                    }
                    return true;
                }
                generateSOAPErrMessage(soapMsg, CREDENTIALS_ERROR + userLogin);
                return false;
            } catch (SOAPException e) {
                logger.error(e);
            }
        }
        return true;
    }

    private boolean hasUserAdminStatus(String userLogin) {

        if (user == null) {
            libraryDAO.findUser(userLogin);
        }
        if (user != null) {
            return user.isAdmin();
        } else return false;
    }
}
