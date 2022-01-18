package org.stroganov.wsClient.handlers;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Set;

public class ClientHandler implements SOAPHandler<SOAPMessageContext> {

    private final Logger logger = Logger.getLogger(ClientHandler.class);

    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("ClientHandler handleMessage..");

        Boolean outBoundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

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
                String password = "Life for Ner'Zhul";

                //add soap header name as 'password'
                QName qName = new QName("http://localhost", "password");
                SOAPHeaderElement soapHeaderElement = header.addHeaderElement(qName);

                soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                soapHeaderElement.addTextNode(password);
                message.saveChanges();
                //write to console for debug
                message.writeTo(System.out);

            } catch (SOAPException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //continue other handler chain
        return true;
    }

    public Set<QName> getHeaders() {
        System.out.println("ClientHandler getHeaders..");
        return null;
    }

    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("ClientHandler handleFault..");
        return false;
    }

    public void close(MessageContext context) {
        System.out.println("ClientHandler close..");
    }
}