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
package br.com.basepro.fenixsped.ws;

import br.com.basepro.fenixsped.util.SOAPUtilities;
import java.io.StringReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;


/**
 *
 * @author vinicius
 */
public class NfeInutilizacao2 extends ClienteServico {

    public static void main(String[] args) throws Exception {

	    
        //Recebe os Parametros
        recebeParametros(args);
        if (getCodUf().equalsIgnoreCase("51")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 51 = MT");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub stub = new br.com.basepro.fenixsped.ws.mt2.NfeInutilizacao2Stub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF2(dadosMsg, cabecMsgE).getExtraElement().toString());

        }else if (getCodUf().equalsIgnoreCase("50")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 50 = MS t3.2");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
	    
	    //xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
            ///XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2\" versao=\"2.00\">" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
	    ///br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeDadosMsg.Factory.parse(dad);
	    
	    
	    
	    OMElement[] e1 = new OMElement[1] ;
	    e1[0] = AXIOMUtil.stringToOM(getConteudoXML().substring(38));
	    br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = new br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeDadosMsg();
	    
	    br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeInutilizacaoNF2Result nfe2result = new 	    br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeInutilizacaoNF2Result();
	    nfe2result.setExtraElement(e1);

	    
	    dadosMsg.setNfeDadosMsg(nfe2result);
	    
	    
	    
	    
            br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
	    //cabecMsg.setVersaoDados("2.00");
	    
            br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub stub = new br.com.basepro.fenixsped.ws.ms2.NfeInutilizacao2Stub(getCaminhoWebService());
            
            //stub.
            setRetorno(stub.nfeInutilizacaoNF2(dadosMsg, cabecMsgE).getExtraElement()[0].toString());
            
            //SOAPUtilities.SoapMessageOutput(stub);
            
            
            
            
            /*
            
            
            HashMap msgContexts = stub._getServiceClient().getLastOperationContext().getMessageContexts();
            Set<String> keySet = msgContexts.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
               System.out.println("------------------------------------------------");
               System.out.println("Verificando mensagens SOAP");
               String key = keySetIterator.next();
               System.out.println("key: " + key + "  " );
               System.out.println("--------------<mensagem remetida>----------------");
               System.out.println("tostring:"+msgContexts.get(key).toString());
               System.out.println("classname:"+msgContexts.get(key).getClass().getName());
               
               MessageContext msgContext = (MessageContext) msgContexts.get(key);
               System.out.println("messagecontext.tostring:"+msgContext.toString());
               System.out.println("messagecontext.getenvelope.tostring:"+msgContext.getEnvelope().toString());
               //Message requestMessage = msgContext.getEnvelope().toS
                //       RequestMessage();
               //System.out.println(requestMessage.getSOAPEnvelope().getAsString());
               System.out.println("--------------<mensagem recebida>----------------");
               //Message responseMessage = msgContext.getResponseMessage();
               //System.out.println(responseMessage.getSOAPEnvelope().getAsString());
               System.out.println("------------------------------------------------");

               //TESTANDO!!!!!!!!!!
               //[root@fenixdms executaveis]# java -jar /basepro/executaveis/FenixSpedt3.2.jar nfeInutilizacao /basepro/sav0001/gm/nf_e/certificado/5014070379546500106555001000004276190870165-ped-inu.xml /basepro/sav0001/gm/nf_e/5014070379546500106555001000004276190870165-inu.xml /basepro/executaveis/tecnoeste20140815.pfx 5659 https://nfe.fazenda.ms.gov.br/producao/services2/NfeInutilizacao2  

               
            }*/


            
            
            
            
            
            
            
            

        }else if (getCodUf().equalsIgnoreCase("43")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 43 = RS");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub stub = new br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF2(dadosMsg, cabecMsgE).getExtraElement().toString());

        }else if (getCodUf().equalsIgnoreCase("29")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 29 = BA");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub stub = new br.com.basepro.fenixsped.ws.ba3.NfeInutilizacaoStub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF(dadosMsg, cabecMsgE).getExtraElement().toString());
            
            
            
            

        }else if (getCodUf().equalsIgnoreCase("26")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 26 = PE");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub stub = new br.com.basepro.fenixsped.ws.pe2.NfeInutilizacao2Stub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF2(dadosMsg, cabecMsgE).getExtraElement().toString());

        }else if (getCodUf().equalsIgnoreCase("41")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 41 = PR");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub stub = new br.com.basepro.fenixsped.ws.pr2.NfeInutilizacao2Stub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF2(dadosMsg, cabecMsgE).getExtraElement().toString());
        }else{

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub stub = new br.com.basepro.fenixsped.ws.rs2.NfeInutilizacao2Stub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF2(dadosMsg, cabecMsgE).getExtraElement().toString());
            
        }

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
