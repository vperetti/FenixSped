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
 * NfeAutorizacaoCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

    package br.com.basepro.fenixsped.ws.ms3;

import br.com.basepro.fenixsped.ws.mt3.*;

    /**
     *  NfeAutorizacaoCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class NfeAutorizacaoCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public NfeAutorizacaoCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public NfeAutorizacaoCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for nfeAutorizacaoLoteZip method
            * override this method for handling normal response from nfeAutorizacaoLoteZip operation
            */
           public void receiveResultnfeAutorizacaoLoteZip(
                    br.com.basepro.fenixsped.ws.mt3.NfeAutorizacaoStub.NfeAutorizacaoLoteZipResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from nfeAutorizacaoLoteZip operation
           */
            public void receiveErrornfeAutorizacaoLoteZip(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for nfeAutorizacaoLote method
            * override this method for handling normal response from nfeAutorizacaoLote operation
            */
           public void receiveResultnfeAutorizacaoLote(
                    br.com.basepro.fenixsped.ws.mt3.NfeAutorizacaoStub.NfeAutorizacaoLoteResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from nfeAutorizacaoLote operation
           */
            public void receiveErrornfeAutorizacaoLote(java.lang.Exception e) {
            }
                


    }
    