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
 * NfeStatusServicoNF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package br.com.basepro.fenixsped.ws.pr;

public class NfeStatusServicoNF  implements java.io.Serializable {
    private java.lang.String nfeCabecMsg;

    private java.lang.String nfeDadosMsg;

    public NfeStatusServicoNF() {
    }

    public NfeStatusServicoNF(
           java.lang.String nfeCabecMsg,
           java.lang.String nfeDadosMsg) {
           this.nfeCabecMsg = nfeCabecMsg;
           this.nfeDadosMsg = nfeDadosMsg;
    }


    /**
     * Gets the nfeCabecMsg value for this NfeStatusServicoNF.
     * 
     * @return nfeCabecMsg
     */
    public java.lang.String getNfeCabecMsg() {
        return nfeCabecMsg;
    }


    /**
     * Sets the nfeCabecMsg value for this NfeStatusServicoNF.
     * 
     * @param nfeCabecMsg
     */
    public void setNfeCabecMsg(java.lang.String nfeCabecMsg) {
        this.nfeCabecMsg = nfeCabecMsg;
    }


    /**
     * Gets the nfeDadosMsg value for this NfeStatusServicoNF.
     * 
     * @return nfeDadosMsg
     */
    public java.lang.String getNfeDadosMsg() {
        return nfeDadosMsg;
    }


    /**
     * Sets the nfeDadosMsg value for this NfeStatusServicoNF.
     * 
     * @param nfeDadosMsg
     */
    public void setNfeDadosMsg(java.lang.String nfeDadosMsg) {
        this.nfeDadosMsg = nfeDadosMsg;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NfeStatusServicoNF)) return false;
        NfeStatusServicoNF other = (NfeStatusServicoNF) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nfeCabecMsg==null && other.getNfeCabecMsg()==null) || 
             (this.nfeCabecMsg!=null &&
              this.nfeCabecMsg.equals(other.getNfeCabecMsg()))) &&
            ((this.nfeDadosMsg==null && other.getNfeDadosMsg()==null) || 
             (this.nfeDadosMsg!=null &&
              this.nfeDadosMsg.equals(other.getNfeDadosMsg())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNfeCabecMsg() != null) {
            _hashCode += getNfeCabecMsg().hashCode();
        }
        if (getNfeDadosMsg() != null) {
            _hashCode += getNfeDadosMsg().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NfeStatusServicoNF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico", ">nfeStatusServicoNF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nfeCabecMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico", "nfeCabecMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nfeDadosMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico", "nfeDadosMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
