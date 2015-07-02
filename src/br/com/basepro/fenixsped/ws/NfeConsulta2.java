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

import java.io.StringReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author vinicius
 */
public class NfeConsulta2 extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        recebeParametros(args);
        if (getCodUf().equalsIgnoreCase("51")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 50 = MT");
            }

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub stub = new br.com.basepro.fenixsped.ws.mt2.NfeConsulta2Stub(getCaminhoWebService());
            setRetorno(stub.nfeConsultaNF2(dadosMsg, cabecMsgE).getExtraElement().toString());

        }else if (getCodUf().equalsIgnoreCase("50")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 50 = MS");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub stub = new br.com.basepro.fenixsped.ws.ms2.NfeConsulta2Stub(getCaminhoWebService());
            setRetorno(stub.nfeConsultaNF2(dadosMsg, cabecMsgE).getExtraElement().toString());
        }else if (getCodUf().equalsIgnoreCase("43")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 43 = RS");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub stub = new br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub(getCaminhoWebService());
            setRetorno(stub.nfeConsultaNF2(dadosMsg, cabecMsgE).getExtraElement().toString());
        }else if (getCodUf().equalsIgnoreCase("29")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 29 = BA");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub stub = new br.com.basepro.fenixsped.ws.ba3.NfeConsultaStub(getCaminhoWebService());
            setRetorno(stub.nfeConsultaNF(dadosMsg, cabecMsgE).getExtraElement().toString());
        }else if (getCodUf().equalsIgnoreCase("26")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 26 = PE");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub stub = new br.com.basepro.fenixsped.ws.pe2.NfeConsulta2Stub(getCaminhoWebService());
            setRetorno(stub.nfeConsultaNF2(dadosMsg, cabecMsgE).getExtraElement().toString());
        }else if (getCodUf().equalsIgnoreCase("41")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 41 = PR");
            }
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub stub = new br.com.basepro.fenixsped.ws.pr2.NfeConsulta2Stub(getCaminhoWebService());
            setRetorno(stub.nfeConsultaNF2(dadosMsg, cabecMsgE).getExtraElement().toString());
        }else{

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsg();
            cabecMsg.setCUF(getCodUf());
            cabecMsg.setVersaoDados(getVersaoDados());
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub.NfeCabecMsgE();
            cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub stub = new br.com.basepro.fenixsped.ws.rs2.NfeConsulta2Stub(getCaminhoWebService());
            setRetorno(stub.nfeConsultaNF2(dadosMsg, cabecMsgE).getExtraElement().toString());

        }
        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
