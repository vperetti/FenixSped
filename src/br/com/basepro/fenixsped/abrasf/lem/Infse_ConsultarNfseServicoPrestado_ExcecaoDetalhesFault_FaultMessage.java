
/**
 * Infse_ConsultarNfseServicoPrestado_ExcecaoDetalhesFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

package br.com.basepro.fenixsped.abrasf.lem;

public class Infse_ConsultarNfseServicoPrestado_ExcecaoDetalhesFault_FaultMessage extends java.lang.Exception{
    
    private br.com.basepro.fenixsped.abrasf.lem.NfseStub.ExcecaoDetalhesE faultMessage;

    
        public Infse_ConsultarNfseServicoPrestado_ExcecaoDetalhesFault_FaultMessage() {
            super("Infse_ConsultarNfseServicoPrestado_ExcecaoDetalhesFault_FaultMessage");
        }

        public Infse_ConsultarNfseServicoPrestado_ExcecaoDetalhesFault_FaultMessage(java.lang.String s) {
           super(s);
        }

        public Infse_ConsultarNfseServicoPrestado_ExcecaoDetalhesFault_FaultMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public Infse_ConsultarNfseServicoPrestado_ExcecaoDetalhesFault_FaultMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(br.com.basepro.fenixsped.abrasf.lem.NfseStub.ExcecaoDetalhesE msg){
       faultMessage = msg;
    }
    
    public br.com.basepro.fenixsped.abrasf.lem.NfseStub.ExcecaoDetalhesE getFaultMessage(){
       return faultMessage;
    }
}
    