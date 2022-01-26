
package org.stroganov.wsClient;

import jakarta.jws.HandlerChain;
import jakarta.xml.ws.*;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "BookServiceImplService", targetNamespace = "http://ws.stroganov.org/", wsdlLocation = "http://127.0.0.1:8082/LibraryService/bookServices?wsdl")
@HandlerChain(file = "clientHandler.xml")
public class BookServiceImplService
        extends Service {

    private static final URL BOOKSERVICEIMPLSERVICE_WSDL_LOCATION;
    private static final WebServiceException BOOKSERVICEIMPLSERVICE_EXCEPTION;
    private static final QName BOOKSERVICEIMPLSERVICE_QNAME = new QName("http://ws.stroganov.org/", "BookServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8082/LibraryService/bookServices?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BOOKSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        BOOKSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public BookServiceImplService() {
        super(__getWsdlLocation(), BOOKSERVICEIMPLSERVICE_QNAME);
    }

    public BookServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BOOKSERVICEIMPLSERVICE_QNAME, features);
    }

    public BookServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, BOOKSERVICEIMPLSERVICE_QNAME);
    }

    public BookServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BOOKSERVICEIMPLSERVICE_QNAME, features);
    }

    public BookServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BookServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * @return returns BookService
     */
    @WebEndpoint(name = "BookServiceImplPort")
    public BookServiceClient getBookServiceImplPort() {
        return super.getPort(new QName("http://ws.stroganov.org/", "BookServiceImplPort"), BookServiceClient.class);
    }

    /**
     * @param features A list of  to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns BookService
     */
    @WebEndpoint(name = "BookServiceImplPort")
    public BookServiceClient getBookServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.stroganov.org/", "BookServiceImplPort"), BookServiceClient.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BOOKSERVICEIMPLSERVICE_EXCEPTION != null) {
            throw BOOKSERVICEIMPLSERVICE_EXCEPTION;
        }
        return BOOKSERVICEIMPLSERVICE_WSDL_LOCATION;
    }
}