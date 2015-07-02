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
 * NfeInutilizacaoLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package br.com.basepro.fenixsped.ws.ba;

public class NfeInutilizacaoLocator extends org.apache.axis.client.Service implements br.com.basepro.fenixsped.ws.ba.NfeInutilizacao {

    public NfeInutilizacaoLocator() {
    }


    public NfeInutilizacaoLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NfeInutilizacaoLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NfeInutilizacaoSoap12
    private java.lang.String NfeInutilizacaoSoap12_address = "https://hnfe.sefaz.ba.gov.br/webservices/nfe/NfeInutilizacao.asmx";

    public java.lang.String getNfeInutilizacaoSoap12Address() {
        return NfeInutilizacaoSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfeInutilizacaoSoap12WSDDServiceName = "NfeInutilizacaoSoap12";

    public java.lang.String getNfeInutilizacaoSoap12WSDDServiceName() {
        return NfeInutilizacaoSoap12WSDDServiceName;
    }

    public void setNfeInutilizacaoSoap12WSDDServiceName(java.lang.String name) {
        NfeInutilizacaoSoap12WSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_PortType getNfeInutilizacaoSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfeInutilizacaoSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfeInutilizacaoSoap12(endpoint);
    }

    public br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_PortType getNfeInutilizacaoSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap12Stub _stub = new br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap12Stub(portAddress, this);
            _stub.setPortName(getNfeInutilizacaoSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfeInutilizacaoSoap12EndpointAddress(java.lang.String address) {
        NfeInutilizacaoSoap12_address = address;
    }


    // Use to get a proxy class for NfeInutilizacaoSoap
    private java.lang.String NfeInutilizacaoSoap_address = "https://hnfe.sefaz.ba.gov.br/webservices/nfe/NfeInutilizacao.asmx";

    public java.lang.String getNfeInutilizacaoSoapAddress() {
        return NfeInutilizacaoSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfeInutilizacaoSoapWSDDServiceName = "NfeInutilizacaoSoap";

    public java.lang.String getNfeInutilizacaoSoapWSDDServiceName() {
        return NfeInutilizacaoSoapWSDDServiceName;
    }

    public void setNfeInutilizacaoSoapWSDDServiceName(java.lang.String name) {
        NfeInutilizacaoSoapWSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_PortType getNfeInutilizacaoSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfeInutilizacaoSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfeInutilizacaoSoap(endpoint);
    }

    public br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_PortType getNfeInutilizacaoSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_BindingStub _stub = new br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_BindingStub(portAddress, this);
            _stub.setPortName(getNfeInutilizacaoSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfeInutilizacaoSoapEndpointAddress(java.lang.String address) {
        NfeInutilizacaoSoap_address = address;
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
            if (br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap12Stub _stub = new br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap12Stub(new java.net.URL(NfeInutilizacaoSoap12_address), this);
                _stub.setPortName(getNfeInutilizacaoSoap12WSDDServiceName());
                return _stub;
            }
            if (br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_BindingStub _stub = new br.com.basepro.fenixsped.ws.ba.NfeInutilizacaoSoap_BindingStub(new java.net.URL(NfeInutilizacaoSoap_address), this);
                _stub.setPortName(getNfeInutilizacaoSoapWSDDServiceName());
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
        if ("NfeInutilizacaoSoap12".equals(inputPortName)) {
            return getNfeInutilizacaoSoap12();
        }
        else if ("NfeInutilizacaoSoap".equals(inputPortName)) {
            return getNfeInutilizacaoSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao", "NfeInutilizacao");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao", "NfeInutilizacaoSoap12"));
            ports.add(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao", "NfeInutilizacaoSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NfeInutilizacaoSoap12".equals(portName)) {
            setNfeInutilizacaoSoap12EndpointAddress(address);
        }
        else 
if ("NfeInutilizacaoSoap".equals(portName)) {
            setNfeInutilizacaoSoapEndpointAddress(address);
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
