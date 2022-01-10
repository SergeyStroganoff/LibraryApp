package org.stroganov.ws;

import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class RequestsValidator implements SOAPHandler<SOAPMessageContext> {

    private final String VALID_PROPERTY = "RANDOM";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Boolean outBoundProperty = (Boolean) context
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // if this is an incoming message from the client
        if (Boolean.FALSE.equals(outBoundProperty)) {

            try {

                // Get the SOAP Message and grab the headers
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                // Grab an iterator to go through the headers
                Iterator<?> headerIterator = soapHeader
                        .extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

                // if there is no additional header
                if (headerIterator != null && headerIterator.hasNext()) {

                    // Extract the property node of the header
                    Node propertyNode = (Node) headerIterator.next();

                    String property = null;

                    if (propertyNode != null)
                        property = propertyNode.getValue();

                    if (VALID_PROPERTY.equals(property)) {
                        // Output the message to the Console -- for debug
                        soapMsg.writeTo(System.out);
                    } else {
                        // Restrict the execution of the Remote Method
                        // Attach an error message as a response
                        SOAPBody soapBody = soapMsg.getSOAPPart().getEnvelope().getBody();
                        SOAPFault soapFault = soapBody.addFault();
                        soapFault.setFaultString("Invalid Property");

                        throw new SOAPFaultException(soapFault);
                    }
                }
            } catch (SOAPException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        return true;
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