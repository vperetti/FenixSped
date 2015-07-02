/*
    Copyright 2009-2015 Vinicius Peretti
    This file is part of FenixSped.

    FenixSped is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixSped is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * ConsultaSituacaoNfseLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package br.com.basepro.fenixsped.abrasf.salvadorba;

public class ConsultaSituacaoNfseLocator extends org.apache.axis.client.Service implements br.com.basepro.fenixsped.abrasf.salvadorba.ConsultaSituacaoNfse {

    public ConsultaSituacaoNfseLocator() {
    }


    public ConsultaSituacaoNfseLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaSituacaoNfseLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IConsultaSituacaoNfse
    private java.lang.String BasicHttpBinding_IConsultaSituacaoNfse_address = "https://nfse.sefaz.salvador.ba.gov.br/CONSULTASITUACAONFSE/ConsultaSituacaoNfse.svc";

    public java.lang.String getBasicHttpBinding_IConsultaSituacaoNfseAddress() {
        return BasicHttpBinding_IConsultaSituacaoNfse_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IConsultaSituacaoNfseWSDDServiceName = "BasicHttpBinding_IConsultaSituacaoNfse";

    public java.lang.String getBasicHttpBinding_IConsultaSituacaoNfseWSDDServiceName() {
        return BasicHttpBinding_IConsultaSituacaoNfseWSDDServiceName;
    }

    public void setBasicHttpBinding_IConsultaSituacaoNfseWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IConsultaSituacaoNfseWSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.abrasf.salvadorba.IConsultaSituacaoNfse getBasicHttpBinding_IConsultaSituacaoNfse() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IConsultaSituacaoNfse_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IConsultaSituacaoNfse(endpoint);
    }

    public br.com.basepro.fenixsped.abrasf.salvadorba.IConsultaSituacaoNfse getBasicHttpBinding_IConsultaSituacaoNfse(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.abrasf.salvadorba.BasicHttpBinding_IConsultaSituacaoNfseStub _stub = new br.com.basepro.fenixsped.abrasf.salvadorba.BasicHttpBinding_IConsultaSituacaoNfseStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IConsultaSituacaoNfseWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IConsultaSituacaoNfseEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IConsultaSituacaoNfse_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.basepro.fenixsped.abrasf.salvadorba.IConsultaSituacaoNfse.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.abrasf.salvadorba.BasicHttpBinding_IConsultaSituacaoNfseStub _stub = new br.com.basepro.fenixsped.abrasf.salvadorba.BasicHttpBinding_IConsultaSituacaoNfseStub(new java.net.URL(BasicHttpBinding_IConsultaSituacaoNfse_address), this);
                _stub.setPortName(getBasicHttpBinding_IConsultaSituacaoNfseWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IConsultaSituacaoNfse".equals(inputPortName)) {
            return getBasicHttpBinding_IConsultaSituacaoNfse();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ConsultaSituacaoNfse");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IConsultaSituacaoNfse"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IConsultaSituacaoNfse".equals(portName)) {
            setBasicHttpBinding_IConsultaSituacaoNfseEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
