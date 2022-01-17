package org.stroganov.ws.handlers;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class RequestsValidator implements SOAPHandler<SOAPMessageContext> {
    Logger logger = Logger.getLogger(RequestsValidator.class);
    private final String USER = "Username";
    private final String PASSWORD = "Password";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        logger.info("Logger stars work");
        System.out.println("Client executing SOAP Handler");
        Boolean outBoundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        // if this is an incoming message from the client
        if (Boolean.FALSE.equals(outBoundProperty)) {
            try {
                // Get the SOAP Message and grab the headers
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();
                System.out.println("Зашли в блок мессадж и берем все хеадеры" + soapHeader.extractAllHeaderElements());

                if (soapHeader == null) {
                    soapHeader = soapEnv.addHeader();
                    generateSOAPErrMessage(soapMsg, "No SOAP header.");
                }
                // Grab an iterator to go through the headers
                Iterator<?> headerIterator = soapHeader.extractAllHeaderElements();
                // if there is no additional header
                if (headerIterator != null && headerIterator.hasNext()) {
                    // Extract the property node of the header
                    Node propertyNode = (Node) headerIterator.next();
                    String password = null;
                    if (propertyNode != null) {
                        password = propertyNode.getValue();
                        System.out.println(propertyNode.getLocalName());
                        System.out.println(propertyNode.getValue());
                        System.out.println(propertyNode.getTextContent());
                        System.out.println(propertyNode.getPrefix());
                        System.out.println(propertyNode.getParentNode());
                        System.out.println(propertyNode.getNodeValue());
                        System.out.println(propertyNode.getNodeType());
                        System.out.println(propertyNode.getNodeName());
                        System.out.println(propertyNode.getAttributes());
                    } else {
                        System.out.println("Property Node is null");
                        generateSOAPErrMessage(soapMsg, "Try Der Parol");
                    }
                    if (USER.equals(password)) {
                        logger.debug("Admin was found " + password);
                        System.out.println("Admin was found " + password);
                    } else {
                        generateSOAPErrMessage(soapMsg, "Invalid Property");
                    }
                } else {
                    System.out.println("Header is EMPTY");
                }
                soapMsg.writeTo(System.out);
            } catch (SOAPException | IOException e) {
                logger.error("SOAP Handler exception caused: " + e.getMessage());
            }
        }
        return true;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new SOAPFaultException(soapFault);
        } catch (SOAPException ignored) {
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return false;
    }

    @Override
    public void close(MessageContext messageContext) {

    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}