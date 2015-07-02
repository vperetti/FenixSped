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
 * NfeRecepcaoLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package br.com.basepro.fenixsped.ws.ba;

public class NfeRecepcaoLocator extends org.apache.axis.client.Service implements br.com.basepro.fenixsped.ws.ba.NfeRecepcao {

    public NfeRecepcaoLocator() {
    }


    public NfeRecepcaoLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NfeRecepcaoLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NfeRecepcaoSoap
    private java.lang.String NfeRecepcaoSoap_address = "https://hnfe.sefaz.ba.gov.br/webservices/nfe/NfeRecepcao.asmx";

    public java.lang.String getNfeRecepcaoSoapAddress() {
        return NfeRecepcaoSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfeRecepcaoSoapWSDDServiceName = "NfeRecepcaoSoap";

    public java.lang.String getNfeRecepcaoSoapWSDDServiceName() {
        return NfeRecepcaoSoapWSDDServiceName;
    }

    public void setNfeRecepcaoSoapWSDDServiceName(java.lang.String name) {
        NfeRecepcaoSoapWSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_PortType getNfeRecepcaoSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfeRecepcaoSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfeRecepcaoSoap(endpoint);
    }

    public br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_PortType getNfeRecepcaoSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_BindingStub _stub = new br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_BindingStub(portAddress, this);
            _stub.setPortName(getNfeRecepcaoSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfeRecepcaoSoapEndpointAddress(java.lang.String address) {
        NfeRecepcaoSoap_address = address;
    }


    // Use to get a proxy class for NfeRecepcaoSoap12
    private java.lang.String NfeRecepcaoSoap12_address = "https://hnfe.sefaz.ba.gov.br/webservices/nfe/NfeRecepcao.asmx";

    public java.lang.String getNfeRecepcaoSoap12Address() {
        return NfeRecepcaoSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfeRecepcaoSoap12WSDDServiceName = "NfeRecepcaoSoap12";

    public java.lang.String getNfeRecepcaoSoap12WSDDServiceName() {
        return NfeRecepcaoSoap12WSDDServiceName;
    }

    public void setNfeRecepcaoSoap12WSDDServiceName(java.lang.String name) {
        NfeRecepcaoSoap12WSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_PortType getNfeRecepcaoSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfeRecepcaoSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfeRecepcaoSoap12(endpoint);
    }

    public br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_PortType getNfeRecepcaoSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap12Stub _stub = new br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap12Stub(portAddress, this);
            _stub.setPortName(getNfeRecepcaoSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfeRecepcaoSoap12EndpointAddress(java.lang.String address) {
        NfeRecepcaoSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_BindingStub _stub = new br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_BindingStub(new java.net.URL(NfeRecepcaoSoap_address), this);
                _stub.setPortName(getNfeRecepcaoSoapWSDDServiceName());
                return _stub;
            }
            if (br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap12Stub _stub = new br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap12Stub(new java.net.URL(NfeRecepcaoSoap12_address), this);
                _stub.setPortName(getNfeRecepcaoSoap12WSDDServiceName());
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
        if ("NfeRecepcaoSoap".equals(inputPortName)) {
            return getNfeRecepcaoSoap();
        }
        else if ("NfeRecepcaoSoap12".equals(inputPortName)) {
            return getNfeRecepcaoSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao", "NfeRecepcao");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao", "NfeRecepcaoSoap"));
            ports.add(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao", "NfeRecepcaoSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NfeRecepcaoSoap".equals(portName)) {
            setNfeRecepcaoSoapEndpointAddress(address);
        }
        else 
if ("NfeRecepcaoSoap12".equals(portName)) {
            setNfeRecepcaoSoap12EndpointAddress(address);
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
