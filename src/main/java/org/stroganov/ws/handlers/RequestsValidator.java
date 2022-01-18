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
    private final Logger logger = Logger.getLogger(RequestsValidator.class);

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("HandlerValidator on server side");
        System.out.println("Server : handleMessage()......");

        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        //for response message only, true for outbound messages, false for inbound
        if (!isRequest) {
            try {
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                //if no header, add one
                if (soapHeader == null) {
                    soapHeader = soapEnv.addHeader();
                    //throw exception
                    generateSOAPErrMessage(soapMsg, "No SOAP header.");
                }

                //Get client mac address from SOAP header
                Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

                //if no header block for next actor found? throw exception
                if (it == null || !it.hasNext()) {
                    generateSOAPErrMessage(soapMsg, "No header block for next actor.");
                }

                //if no mac address found > throw exception
                assert it != null;
                Node passNode = (Node) it.next();
                String password = (passNode == null) ? null : passNode.getValue();

                if (password == null) {
                    generateSOAPErrMessage(soapMsg, "Try Der Parol");
                }

                //if mac address is not match, throw exception
                if ("For the Horde.".equals(password)) {
                    generateSOAPErrMessage(soapMsg, "The sky is falling");
                } else if ("Life for Ner'Zhul".equals(password)) {
                    System.out.println("Все получилось - получилось получить пароль:: " + password);
                }

                //tracking
                soapMsg.writeTo(System.out);

            } catch (SOAPException | IOException e) {
                System.err.println(e);
            }

        }
        //continue other handler chain
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

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
}