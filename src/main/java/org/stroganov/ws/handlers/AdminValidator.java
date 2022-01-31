package org.stroganov.ws.handlers;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;
import org.stroganov.entities.User;

import java.util.Iterator;

public class AdminValidator extends UserValidator {
    private final Logger logger = Logger.getLogger(AdminValidator.class);

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isRequest) {
            try {
                SOAPMessage soapMsg = context.getMessage();
                Iterator<SOAPHeaderElement> it = getSoapHeaders(soapMsg);
                User user = getAuthorizedUserFromHeaders(it, soapMsg);

                if (user == null || !user.isAdmin()) {
                    generateSOAPErrMessage(soapMsg, "permission denied");
                    return false;
                }
                return true;
            } catch (SOAPException e) {
                logger.error(e);
            }
        }
        return true;
    }
}
