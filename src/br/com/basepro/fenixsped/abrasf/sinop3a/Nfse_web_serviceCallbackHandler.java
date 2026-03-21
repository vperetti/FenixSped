
/**
 * Nfse_web_serviceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

    package br.com.basepro.fenixsped.abrasf.sinop3a;

    /**
     *  Nfse_web_serviceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class Nfse_web_serviceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public Nfse_web_serviceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public Nfse_web_serviceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for cONSULTARNFSEPORRPS method
            * override this method for handling normal response from cONSULTARNFSEPORRPS operation
            */
           public void receiveResultcONSULTARNFSEPORRPS(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceCONSULTARNFSEPORRPSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cONSULTARNFSEPORRPS operation
           */
            public void receiveErrorcONSULTARNFSEPORRPS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cONSULTARLOTERPS method
            * override this method for handling normal response from cONSULTARLOTERPS operation
            */
           public void receiveResultcONSULTARLOTERPS(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceCONSULTARLOTERPSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cONSULTARLOTERPS operation
           */
            public void receiveErrorcONSULTARLOTERPS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cANCELARNFSE method
            * override this method for handling normal response from cANCELARNFSE operation
            */
           public void receiveResultcANCELARNFSE(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceCANCELARNFSEResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cANCELARNFSE operation
           */
            public void receiveErrorcANCELARNFSE(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cONSULTARNFSEFAIXA method
            * override this method for handling normal response from cONSULTARNFSEFAIXA operation
            */
           public void receiveResultcONSULTARNFSEFAIXA(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceCONSULTARNFSEFAIXAResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cONSULTARNFSEFAIXA operation
           */
            public void receiveErrorcONSULTARNFSEFAIXA(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rECEPCIONARLOTERPS method
            * override this method for handling normal response from rECEPCIONARLOTERPS operation
            */
           public void receiveResultrECEPCIONARLOTERPS(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rECEPCIONARLOTERPS operation
           */
            public void receiveErrorrECEPCIONARLOTERPS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for gERARNFSE method
            * override this method for handling normal response from gERARNFSE operation
            */
           public void receiveResultgERARNFSE(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceGERARNFSEResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from gERARNFSE operation
           */
            public void receiveErrorgERARNFSE(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for rECEPCIONARLOTERPSSINCRONO method
            * override this method for handling normal response from rECEPCIONARLOTERPSSINCRONO operation
            */
           public void receiveResultrECEPCIONARLOTERPSSINCRONO(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPSSINCRONOResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rECEPCIONARLOTERPSSINCRONO operation
           */
            public void receiveErrorrECEPCIONARLOTERPSSINCRONO(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cONSULTARNFSESERVICOPRESTADO method
            * override this method for handling normal response from cONSULTARNFSESERVICOPRESTADO operation
            */
           public void receiveResultcONSULTARNFSESERVICOPRESTADO(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceCONSULTARNFSESERVICOPRESTADOResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cONSULTARNFSESERVICOPRESTADO operation
           */
            public void receiveErrorcONSULTARNFSESERVICOPRESTADO(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cONSULTARNFSESERVICOTOMADO method
            * override this method for handling normal response from cONSULTARNFSESERVICOTOMADO operation
            */
           public void receiveResultcONSULTARNFSESERVICOTOMADO(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceCONSULTARNFSESERVICOTOMADOResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cONSULTARNFSESERVICOTOMADO operation
           */
            public void receiveErrorcONSULTARNFSESERVICOTOMADO(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sUBSTITUIRNFSE method
            * override this method for handling normal response from sUBSTITUIRNFSE operation
            */
           public void receiveResultsUBSTITUIRNFSE(
                    br.com.basepro.fenixsped.abrasf.sinop3a.Nfse_web_serviceStub.Nfse_web_serviceSUBSTITUIRNFSEResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sUBSTITUIRNFSE operation
           */
            public void receiveErrorsUBSTITUIRNFSE(java.lang.Exception e) {
            }
                


    }
    