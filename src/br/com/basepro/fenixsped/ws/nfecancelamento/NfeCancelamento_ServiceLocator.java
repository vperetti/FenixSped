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
 * NfeCancelamento_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package br.com.basepro.fenixsped.ws.nfecancelamento;

public class NfeCancelamento_ServiceLocator extends org.apache.axis.client.Service implements br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamento_Service {

    public NfeCancelamento_ServiceLocator() {
    }


    public NfeCancelamento_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NfeCancelamento_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NfeCancelamento
    private java.lang.String NfeCancelamento_address = "";

    public java.lang.String getNfeCancelamentoAddress() {
        return NfeCancelamento_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NfeCancelamentoWSDDServiceName = "NfeCancelamento";

    public java.lang.String getNfeCancelamentoWSDDServiceName() {
        return NfeCancelamentoWSDDServiceName;
    }

    public void setNfeCancelamentoWSDDServiceName(java.lang.String name) {
        NfeCancelamentoWSDDServiceName = name;
    }

    public br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamento_PortType getNfeCancelamento() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NfeCancelamento_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNfeCancelamento(endpoint);
    }

    public br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamento_PortType getNfeCancelamento(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamentoSoapBindingStub _stub = new br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamentoSoapBindingStub(portAddress, this);
            _stub.setPortName(getNfeCancelamentoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNfeCancelamentoEndpointAddress(java.lang.String address) {
        NfeCancelamento_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamento_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamentoSoapBindingStub _stub = new br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamentoSoapBindingStub(new java.net.URL(NfeCancelamento_address), this);
                _stub.setPortName(getNfeCancelamentoWSDDServiceName());
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
        if ("NfeCancelamento".equals(inputPortName)) {
            return getNfeCancelamento();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento", "NfeCancelamento");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento", "NfeCancelamento"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NfeCancelamento".equals(portName)) {
            setNfeCancelamentoEndpointAddress(address);
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
