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
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class UserValidator implements SOAPHandler<SOAPMessageContext> {
    public static final String CREDENTIALS_ERROR = "Не удалось получить правильный пароль для пользователя:";
    private final Logger logger = Logger.getLogger(UserValidator.class);
    protected final LibraryDAO libraryDAO = DataManager.getLibraryDAO();
    protected User user = null;

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isRequest) {
            try {
                SOAPMessage soapMsg = context.getMessage();
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

    protected void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
            logger.error(e);
        }
    }

    protected boolean checkUser(String userLogin, String password) {
        if (userLogin == null || password == null) {
            return false;
        }
        user = libraryDAO.findUser(userLogin);
        if (user == null) {
            return false;
        }
        return user.getPasscodeHash().equals(password);
    }
}
