package org.stroganov.wsClient.handlers;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;
import org.stroganov.util.UserContainer;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class ClientHandler implements SOAPHandler<SOAPMessageContext> {

    private final Logger logger = Logger.getLogger(ClientHandler.class);

    public boolean handleMessage(SOAPMessageContext context) {
        logger.info("ClientHandler handleMessage..");
        if (UserContainer.getUser() == null) {
            return true;
        }
        boolean outBoundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        //if this is a request (outgoing message), true for outbound messages, false for inbound
        if (outBoundProperty) {
            try {
                SOAPMessage message = context.getMessage();
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                //if no header that we'll add one
                if (header == null) {
                    header = envelope.addHeader();
                }
                //add password for later check on server side
                String userLogin = UserContainer.getUser().getLogin();
                String password = UserContainer.getUser().getPasscodeHash();

                QName qName = new QName("http://ws.stroganov.org/", "password");
                SOAPHeaderElement soapHeaderElement = header.addHeaderElement(qName);
                soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                soapHeaderElement.addTextNode(password);

                qName = new QName("http://ws.stroganov.org/", "userLogin");
                soapHeaderElement = header.addHeaderElement(qName);
                soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                soapHeaderElement.addTextNode(userLogin);

                message.saveChanges();
                message.writeTo(System.out);
                logger.info(message.getSOAPHeader());
                logger.info(message.getSOAPPart());

            } catch (SOAPException | IOException e) {
                e.printStackTrace();
            }
        }
        //continue other handler chain
        return true;
    }

    public Set<QName> getHeaders() {
        logger.info("ClientHandler getHeaders..");
        return Collections.emptySet();
    }

    public boolean handleFault(SOAPMessageContext context) {
        logger.info("ClientHandler handleFault..");
        return false;
    }

    public void close(MessageContext context) {
        logger.info("ClientHandler close..");
    }
}
