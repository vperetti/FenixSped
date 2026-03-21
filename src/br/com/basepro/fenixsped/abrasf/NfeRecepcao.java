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
/*
 * Fenix Sped
 * 
 */
package br.com.basepro.fenixsped.abrasf;

import br.com.basepro.fenixsped.ws.ClienteServico;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPFactory;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNode;
import org.apache.axiom.om.impl.llom.OMElementImpl;
import org.apache.axiom.om.impl.llom.OMNodeImpl;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axis2.Constants;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;

/**
 *
 * @author vinicius
 */
public class NfeRecepcao extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        String codigoMunicipio = args[5];
        
        // LEM
        if (codigoMunicipio.equalsIgnoreCase("291955")) {
            setCertificaServidor(false);
        }else{
            setCertificaServidor(true);    
        }
        
        setPadrao("abrasf");
        recebeParametros(args);


        //SE FOR SALVADOR-BA
        if (codigoMunicipio.equalsIgnoreCase("292740")) {
            br.com.basepro.fenixsped.abrasf.salvadorba.EnvioLoteRPS service = new br.com.basepro.fenixsped.abrasf.salvadorba.EnvioLoteRPSLocator();
            br.com.basepro.fenixsped.abrasf.salvadorba.IEnvioLoteRPS servico = service.getBasicHttpBinding_IEnvioLoteRPS(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.enviarLoteRPS(getConteudoXML()));

            //SE FOR RECIFE-PE
        } else if (codigoMunicipio.equalsIgnoreCase("261160-ant")) {
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.recifepe.NfseStub servico = new br.com.basepro.fenixsped.abrasf.recifepe.NfseStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.EnviarLoteRpsEnvio envio = new br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.EnviarLoteRpsEnvio();
            envio.setMensagemXML(getConteudoXML());
            br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.EnviarLoteRpsResposta resposta = servico.recepcionarLoteRps(envio);
            setRetorno(resposta.getEnviarLoteRpsResposta());
            
	
        //} else if (codigoMunicipio.equalsIgnoreCase("261160-v01")) {
        } else if (codigoMunicipio.equalsIgnoreCase("261160")) {
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub servico = new br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.RecepcionarLoteRpsRequest envio = new br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.RecepcionarLoteRpsRequest();
            envio.setInputXML(getConteudoXML());
            br.com.basepro.fenixsped.abrasf.recifev01.Nfse_v01Stub.RecepcionarLoteRpsResponse resposta = servico.recepcionarLoteRps(envio);
            setRetorno(resposta.getOutputXML());
            
	    //SE FOR CUIABA
        } else if  (codigoMunicipio.equalsIgnoreCase("510340")){
            //setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.issnetonline204.NfseWSServiceStub servico = new br.com.basepro.fenixsped.abrasf.issnetonline204.NfseWSServiceStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.issnetonline204.NfseWSServiceStub.RecepcionarLoteRps envio = new br.com.basepro.fenixsped.abrasf.issnetonline204.NfseWSServiceStub.RecepcionarLoteRps();
            br.com.basepro.fenixsped.abrasf.issnetonline204.NfseWSServiceStub.Input inputReq = new br.com.basepro.fenixsped.abrasf.issnetonline204.NfseWSServiceStub.Input();
            inputReq.setNfseCabecMsg("<cabecalho versao=\"2.04\" xmlns=\"http://www.abrasf.org.br/nfse.xsd\">\n"+
"<versaoDados>2.04</versaoDados>\n"+
"</cabecalho>");
            inputReq.setNfseDadosMsg(getConteudoXML().substring(38));
            
            envio.setRecepcionarLoteRps(inputReq);           
            
            
//            br.com.basepro.fenixsped.abrasf.issnetonline204.NfseWSServiceStub.RecepcionarLoteRpsResponse resposta = servico.recepcionarLoteRps(envio);
//            System.out.println("resposta.getRecepcionarLoteRpsResponse().getOutputXML()");
//            System.out.println(resposta.getRecepcionarLoteRpsResponse().getOutputXML());
//            setRetorno(resposta.getRecepcionarLoteRpsResponse().getOutputXML());
            setRetorno(servico.recepcionarLoteRps2String(envio));
            
            
        } else if  (codigoMunicipio.equalsIgnoreCase("510340issnet1.0")){
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub servico = new br.com.basepro.fenixsped.abrasf.issnet.ServicosStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.RecepcionarLoteRps envio = new br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.RecepcionarLoteRps();
            envio.setXml(getConteudoXML());
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.RecepcionarLoteRpsResponse resposta = servico.recepcionarLoteRps(envio);
            setRetorno(resposta.getRecepcionarLoteRpsResult());

	    //SE FOR DOURADOS
        } else if (codigoMunicipio.equalsIgnoreCase("500370")) {
            
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub servico = new br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.RecepcionarLoteRpsE envio = new br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.RecepcionarLoteRpsE();
            br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.RecepcionarLoteRps inputReq = new br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.RecepcionarLoteRps();
            inputReq.setNfseCabecMsg("<cabecalho xmlns=\"http://www.betha.com.br/e-nota-contribuinte-ws\" versao=\"2.02\"><versaoDados>2.02</versaoDados></cabecalho>");
            inputReq.setNfseDadosMsg(getConteudoXML().substring(38));
            envio.setRecepcionarLoteRps(inputReq);
            br.com.basepro.fenixsped.abrasf.bethav1.NfseWSServiceStub.RecepcionarLoteRpsResponseE resposta = servico.recepcionarLoteRps(envio);
            setRetorno(resposta.getRecepcionarLoteRpsResponse().get_return());
            
	    //SE FOR DOURADOS
        } else if (codigoMunicipio.equalsIgnoreCase("500370ant")) {
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub servico = new br.com.basepro.fenixsped.abrasf.issnet.ServicosStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.RecepcionarLoteRps envio = new br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.RecepcionarLoteRps();
            envio.setXml(getConteudoXML());
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.RecepcionarLoteRpsResponse resposta = servico.recepcionarLoteRps(envio);
            setRetorno(resposta.getRecepcionarLoteRpsResult());
        //SE FOR SINOP
         } else if (codigoMunicipio.equalsIgnoreCase("510790")) {

            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.sinop4.Nfse_web_serviceStub servico = new br.com.basepro.fenixsped.abrasf.sinop4.Nfse_web_serviceStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.sinop4.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPS envio = new br.com.basepro.fenixsped.abrasf.sinop4.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPS();
            br.com.basepro.fenixsped.abrasf.sinop4.Nfse_web_serviceStub.Input inputReq = new br.com.basepro.fenixsped.abrasf.sinop4.Nfse_web_serviceStub.Input();
            inputReq.setNfseCabecMsg("<?xml version = \"2.01\" encoding = \"utf-8\"?>    \n" +
"            	<cabecalho\n" +
"    				xmlns=\"http://www.abrasf.org.br/nfse.xsd\" versao=\"2.01\">\n" +
"    				<versaoDados>2.01</versaoDados>\n" +
"			    </cabecalho>");
            inputReq.setNfseDadosMsg(getConteudoXML().substring(38)); 
            System.out.println(getConteudoXML().substring(38));
            envio.setRecepcionarloterpsrequest(inputReq);
            br.com.basepro.fenixsped.abrasf.sinop4.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPSResponse resposta = servico.rECEPCIONARLOTERPS(envio);
            setRetorno(resposta.getRecepcionarloterpsresponse().getOutputXML());
            
            
        } else if (codigoMunicipio.equalsIgnoreCase("510790-teste2")) {

            
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.sinop.NfseWSServiceStub servico = new br.com.basepro.fenixsped.abrasf.sinop.NfseWSServiceStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.sinop.NfseWSServiceStub.RecepcionarLoteRpsRequest envio = new br.com.basepro.fenixsped.abrasf.sinop.NfseWSServiceStub.RecepcionarLoteRpsRequest();
            
            br.com.basepro.fenixsped.abrasf.sinop.NfseWSServiceStub.Input inputReq = new br.com.basepro.fenixsped.abrasf.sinop.NfseWSServiceStub.Input();
            inputReq.setNfseCabecMsg("<versaoDados>2.01</versaoDados>");
            inputReq.setNfseDadosMsg(getConteudoXML());  
            
            envio.setRecepcionarLoteRpsRequest(inputReq);
            br.com.basepro.fenixsped.abrasf.sinop.NfseWSServiceStub.RecepcionarLoteRpsResponse resposta = servico.recepcionarLoteRps(envio);
            setRetorno(resposta.getRecepcionarLoteRpsResponse().getOutputXML()); 
            
            
            
            
            
        } else if (codigoMunicipio.equalsIgnoreCase("510790bk")) {
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.coplan.Nfse_web_serviceStub servico = new br.com.basepro.fenixsped.abrasf.coplan.Nfse_web_serviceStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.coplan.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPS envio = new br.com.basepro.fenixsped.abrasf.coplan.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPS();
            


            
            
            //envio.setRecepcionarloterpsrequest(getConteudoXML());
            
            br.com.basepro.fenixsped.abrasf.coplan.Nfse_web_serviceStub.Input inputReq = new br.com.basepro.fenixsped.abrasf.coplan.Nfse_web_serviceStub.Input();
            
            inputReq.setNfseCabecMsg("<versaoDados>2.01</versaoDados>");
            inputReq.setNfseDadosMsg(getConteudoXML());            
            envio.setRecepcionarloterpsrequest(inputReq);
                        
            
            
            //envio.getRecepcionarloterpsrequest().setNfseCabecMsg("");
            //envio.getRecepcionarloterpsrequest().setNfseDadosMsg(getConteudoXML());
            
            //servico.rECEPCIONARLOTERPS(envio);
            //servico.
            
            br.com.basepro.fenixsped.abrasf.coplan.Nfse_web_serviceStub.Nfse_web_serviceRECEPCIONARLOTERPSResponse resposta = servico.rECEPCIONARLOTERPS(envio);
                   
            
            setRetorno(resposta.getRecepcionarloterpsresponse().getOutputXML());
 
        }else if (codigoMunicipio.equalsIgnoreCase("291955")) {
            // LUIZ EDUARDO MAGALHAES
            String NFSE_CABECALHO_MSG = "<cabecalho xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://www.abrasf.org.br/nfse.xsd\" versao=\"2.01\"><versaoDados>2.01</versaoDados></cabecalho>";
            br.com.basepro.fenixsped.abrasf.lem.NfseStub servico = new br.com.basepro.fenixsped.abrasf.lem.NfseStub(getCaminhoWebService());
            System.out.println("===================================================================");
            System.out.println("Auth 6 OK");
            System.out.println("===================================================================");

            Options options = servico._getServiceClient().getOptions();
            OMFactory omFactory = OMAbstractFactory.getOMFactory();
           
            OMElement omSecurityElement = omFactory.createOMElement(new QName( "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse"), null);
            omSecurityElement.addAttribute("soapenv:mustUnderstand", "1", null);
            omSecurityElement.addAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", null);            
            OMElement omusertoken = omFactory.createOMElement(new QName("", "wsse:UsernameToken", ""), null);
            omusertoken.addAttribute("wsu:Id", "UsernameToken-2", null);
            OMElement omuserName = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Username", "wsse"), null);
            omuserName.setText("84704829472");
            OMElement omPassword = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Password", "wsse"), null);
            omPassword.addAttribute("Type",
                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText",null );
            omPassword.setText("1139236008");
            omusertoken.addChild(omuserName);
            omusertoken.addChild(omPassword);
            omSecurityElement.addChild(omusertoken);
            servico._getServiceClient().addHeader(omSecurityElement);
                        
            //////////////////////////////////////////////////////////////////////////////////        
            //options.setProperty(Constants.Configuration.MESSAGE_TYPE,"text/xml"); //https://stackoverflow.com/questions/14584600/issue-with-setting-the-content-type-for-http-request-header-with-axis2
            /*
            Your client may be using the wrong SOAP version to format its request. text/xml is the SOAP 1.1 content type. application/soap+xml is the content type for SOAP 1.2.
            */
            options.setSoapVersionURI(org.apache.axiom.soap.SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
            
         
            br.com.basepro.fenixsped.abrasf.lem.NfseStub.RecepcionarLoteRpsRequest envio = new br.com.basepro.fenixsped.abrasf.lem.NfseStub.RecepcionarLoteRpsRequest();
            envio.setNfseCabecMsg(NFSE_CABECALHO_MSG);
            envio.setNfseDadosMsg(getConteudoXML());           
            br.com.basepro.fenixsped.abrasf.lem.NfseStub.RecepcionarLoteRpsResponse resposta = servico.recepcionarLoteRps(envio);
            setRetorno(resposta.getOutputXML());
            
        } else {
            //PARA MANTER COMPATIBILIDADE FOI MANTIDO PADRÃO SALVADOR
            br.com.basepro.fenixsped.abrasf.salvadorba.EnvioLoteRPS service = new br.com.basepro.fenixsped.abrasf.salvadorba.EnvioLoteRPSLocator();
            br.com.basepro.fenixsped.abrasf.salvadorba.IEnvioLoteRPS servico = service.getBasicHttpBinding_IEnvioLoteRPS(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.enviarLoteRPS(getConteudoXML()));


        }

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
