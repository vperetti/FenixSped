
/**
 * Nfse_v01CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

    package br.com.basepro.fenixsped.abrasf.recifev01;

    /**
     *  Nfse_v01CallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class Nfse_v01CallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public Nfse_v01CallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public Nfse_v01CallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for consultarLoteRps method
            * override this method for handling normal response from consultarLoteRps operation
            */
           public void receiveResultconsultarLoteRps(
                    br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.ConsultarLoteRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarLoteRps operation
           */
            public void receiveErrorconsultarLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelarNfse method
            * override this method for handling normal response from cancelarNfse operation
            */
           public void receiveResultcancelarNfse(
                    br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.CancelarNfseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelarNfse operation
           */
            public void receiveErrorcancelarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarSituacaoLoteRps method
            * override this method for handling normal response from consultarSituacaoLoteRps operation
            */
           public void receiveResultconsultarSituacaoLoteRps(
                    br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.ConsultarSituacaoLoteRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarSituacaoLoteRps operation
           */
            public void receiveErrorconsultarSituacaoLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for gerarNfse method
            * override this method for handling normal response from gerarNfse operation
            */
           public void receiveResultgerarNfse(
                    br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.GerarNfseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from gerarNfse operation
           */
            public void receiveErrorgerarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfsePorRps method
            * override this method for handling normal response from consultarNfsePorRps operation
            */
           public void receiveResultconsultarNfsePorRps(
                    br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.ConsultarNfsePorRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfsePorRps operation
           */
            public void receiveErrorconsultarNfsePorRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfse method
            * override this method for handling normal response from consultarNfse operation
            */
           public void receiveResultconsultarNfse(
                    br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.ConsultarNfseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfse operation
           */
            public void receiveErrorconsultarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for recepcionarLoteRps method
            * override this method for handling normal response from recepcionarLoteRps operation
            */
           public void receiveResultrecepcionarLoteRps(
                    br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.RecepcionarLoteRpsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from recepcionarLoteRps operation
           */
            public void receiveErrorrecepcionarLoteRps(java.lang.Exception e) {
            }
                


    }
    