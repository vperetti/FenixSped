
/**
 * NfseWSServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

    package br.com.basepro.fenixsped.abrasf.bethav1;

    /**
     *  NfseWSServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class NfseWSServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public NfseWSServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public NfseWSServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for consultarNfseFaixa method
            * override this method for handling normal response from consultarNfseFaixa operation
            */
           public void receiveResultconsultarNfseFaixa(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.ConsultarNfseFaixaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfseFaixa operation
           */
            public void receiveErrorconsultarNfseFaixa(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelarNfse method
            * override this method for handling normal response from cancelarNfse operation
            */
           public void receiveResultcancelarNfse(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.CancelarNfseResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelarNfse operation
           */
            public void receiveErrorcancelarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarLoteRps method
            * override this method for handling normal response from consultarLoteRps operation
            */
           public void receiveResultconsultarLoteRps(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.ConsultarLoteRpsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarLoteRps operation
           */
            public void receiveErrorconsultarLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for gerarNfse method
            * override this method for handling normal response from gerarNfse operation
            */
           public void receiveResultgerarNfse(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.GerarNfseResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from gerarNfse operation
           */
            public void receiveErrorgerarNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for substituirNfse method
            * override this method for handling normal response from substituirNfse operation
            */
           public void receiveResultsubstituirNfse(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.SubstituirNfseResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from substituirNfse operation
           */
            public void receiveErrorsubstituirNfse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for recepcionarLoteRpsSincrono method
            * override this method for handling normal response from recepcionarLoteRpsSincrono operation
            */
           public void receiveResultrecepcionarLoteRpsSincrono(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.RecepcionarLoteRpsSincronoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from recepcionarLoteRpsSincrono operation
           */
            public void receiveErrorrecepcionarLoteRpsSincrono(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for recepcionarLoteRps method
            * override this method for handling normal response from recepcionarLoteRps operation
            */
           public void receiveResultrecepcionarLoteRps(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.RecepcionarLoteRpsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from recepcionarLoteRps operation
           */
            public void receiveErrorrecepcionarLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfsePorRps method
            * override this method for handling normal response from consultarNfsePorRps operation
            */
           public void receiveResultconsultarNfsePorRps(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.ConsultarNfsePorRpsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfsePorRps operation
           */
            public void receiveErrorconsultarNfsePorRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfseServicoTomado method
            * override this method for handling normal response from consultarNfseServicoTomado operation
            */
           public void receiveResultconsultarNfseServicoTomado(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.ConsultarNfseServicoTomadoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfseServicoTomado operation
           */
            public void receiveErrorconsultarNfseServicoTomado(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfseServicoPrestado method
            * override this method for handling normal response from consultarNfseServicoPrestado operation
            */
           public void receiveResultconsultarNfseServicoPrestado(
                    br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.ConsultarNfseServicoPrestadoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarNfseServicoPrestado operation
           */
            public void receiveErrorconsultarNfseServicoPrestado(java.lang.Exception e) {
            }
                


    }
    