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
 * NfeStatusServicoLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package br.com.basepro.fenixsped.ws.pr;

public class NfeStatusServicoLocator extends org.apache.axis.client.Service implements br.com.basepro.fenixsped.ws.pr.NfeStatusServico {

    public NfeStatusServicoLocator() {
    }


    public NfeStatusServicoLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NfeStatusServicoLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NfeStatusServicoSoap12
    private java.lang.String NfeStatusServicoSoap12_address = "https://homologacao.nfe.fazenda.pr.gov.br/NFENWebServices/services/nfeStatusServicoNF";

    public java.lang.String getNfeStatusServicoSoap12Address() {
        return NfeStatusServicoSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfeStatusServicoSoap12WSDDServiceName = "NfeStatusServicoSoap12";

    public java.lang.String getNfeStatusServicoSoap12WSDDServiceName() {
        return NfeStatusServicoSoap12WSDDServiceName;
    }

    public void setNfeStatusServicoSoap12WSDDServiceName(java.lang.String name) {
        NfeStatusServicoSoap12WSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_PortType getNfeStatusServicoSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfeStatusServicoSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfeStatusServicoSoap12(endpoint);
    }

    public br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_PortType getNfeStatusServicoSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap12Stub _stub = new br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap12Stub(portAddress, this);
            _stub.setPortName(getNfeStatusServicoSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfeStatusServicoSoap12EndpointAddress(java.lang.String address) {
        NfeStatusServicoSoap12_address = address;
    }


    // Use to get a proxy class for NfeStatusServicoSoap
    private java.lang.String NfeStatusServicoSoap_address = "https://homologacao.nfe.fazenda.pr.gov.br/NFENWebServices/services/nfeStatusServicoNF";

    public java.lang.String getNfeStatusServicoSoapAddress() {
        return NfeStatusServicoSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfeStatusServicoSoapWSDDServiceName = "NfeStatusServicoSoap";

    public java.lang.String getNfeStatusServicoSoapWSDDServiceName() {
        return NfeStatusServicoSoapWSDDServiceName;
    }

    public void setNfeStatusServicoSoapWSDDServiceName(java.lang.String name) {
        NfeStatusServicoSoapWSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_PortType getNfeStatusServicoSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfeStatusServicoSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfeStatusServicoSoap(endpoint);
    }

    public br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_PortType getNfeStatusServicoSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_BindingStub _stub = new br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_BindingStub(portAddress, this);
            _stub.setPortName(getNfeStatusServicoSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfeStatusServicoSoapEndpointAddress(java.lang.String address) {
        NfeStatusServicoSoap_address = address;
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
            if (br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap12Stub _stub = new br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap12Stub(new java.net.URL(NfeStatusServicoSoap12_address), this);
                _stub.setPortName(getNfeStatusServicoSoap12WSDDServiceName());
                return _stub;
            }
            if (br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_BindingStub _stub = new br.com.basepro.fenixsped.ws.pr.NfeStatusServicoSoap_BindingStub(new java.net.URL(NfeStatusServicoSoap_address), this);
                _stub.setPortName(getNfeStatusServicoSoapWSDDServiceName());
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
        if ("NfeStatusServicoSoap12".equals(inputPortName)) {
            return getNfeStatusServicoSoap12();
        }
        else if ("NfeStatusServicoSoap".equals(inputPortName)) {
            return getNfeStatusServicoSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico", "NfeStatusServico");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico", "NfeStatusServicoSoap12"));
            ports.add(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico", "NfeStatusServicoSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NfeStatusServicoSoap12".equals(portName)) {
            setNfeStatusServicoSoap12EndpointAddress(address);
        }
        else 
if ("NfeStatusServicoSoap".equals(portName)) {
            setNfeStatusServicoSoapEndpointAddress(address);
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
