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
 * NfseCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

    package br.com.basepro.fenixsped.abrasf.recifepe;

    /**
     *  NfseCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class NfseCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public NfseCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public NfseCallbackHandler(){
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
                    br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.ConsultarLoteRpsResposta result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarLoteRps operation
           */
            public void receiveErrorconsultarLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarNfsePorRps method
            * override this method for handling normal response from consultarNfsePorRps operation
            */
           public void receiveResultconsultarNfsePorRps(
                    br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.ConsultarNfseRpsResposta result
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
                    br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.ConsultarNfseResposta result
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
                    br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.EnviarLoteRpsResposta result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from recepcionarLoteRps operation
           */
            public void receiveErrorrecepcionarLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarSituacaoLoteRps method
            * override this method for handling normal response from consultarSituacaoLoteRps operation
            */
           public void receiveResultconsultarSituacaoLoteRps(
                    br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.ConsultarSituacaoLoteRpsNfseResposta result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarSituacaoLoteRps operation
           */
            public void receiveErrorconsultarSituacaoLoteRps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelarNfse method
            * override this method for handling normal response from cancelarNfse operation
            */
           public void receiveResultcancelarNfse(
                    br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.CancelarNfseResposta result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelarNfse operation
           */
            public void receiveErrorcancelarNfse(java.lang.Exception e) {
            }
                


    }
    