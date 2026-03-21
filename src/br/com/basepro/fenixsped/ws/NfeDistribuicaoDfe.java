/*
    Copyright 2009-2018 Vinicius Peretti
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
import br.com.basepro.fenixsped.ws.nfedistribuicaodfe.*;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

/**
 *
 * @author vinicius
 */
public class NfeDistribuicaoDfe extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        recebeParametros(args);

        if (isDebugando()) {
            System.out.println("Comunicando com geral");
        }

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
        //XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<soap12:Body><nfeDistDFeInteresse xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NFeDistribuicaoDFe\"><nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg></nfeDistDFeInteresse></soap12:Body>"));
        XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
        //XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader(getConteudoXML().substring(38)));
        
        System.out.println("xml: "+getConteudoXML());
        
        //// NFeDistribuicaoDFeStub.NfeDistDFeInteresse  dadosMsg = NFeDistribuicaoDFeStub.NfeDistDFeInteresse.Factory.parse(dad);
        
        
        
        //NFeDistribuicaoDFeStub.NfeDadosMsg_type0  nfeDadosMsg = NFeDistribuicaoDFeStub.NfeDadosMsg_type0.Factory.parse(dad);
        
        //dadosMsg.setNfeDadosMsg(nfeDadosMsg);
        
        
  //      br.com.basepro.fenixsped.ws.nfedistribuicaodfe.NFeDistribuicaoDFeStub.NfeDadosMsg_type0 nfeDadosMsg = br.com.basepro.fenixsped.ws.nfedistribuicaodfe.NFeDistribuicaoDFeStub.NfeDadosMsg_type0.Factory.parse(dad);
        
//        dadosMsg.setNfeDadosMsg(nfeDadosMsg);
        
        
        
        //br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsg();
        //cabecMsg.setCUF(getCodUf());
        //cabecMsg.setVersaoDados(getVersaoDados());
        //br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsgE();
        //cabecMsgE.setNfeCabecMsg(cabecMsg);
        
        		OMElement ome = AXIOMUtil.stringToOM( getConteudoXML().substring(38));

        
			NFeDistribuicaoDFeStub.NfeDadosMsg_type0 dadosMsgType0 = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();  
			dadosMsgType0.setExtraElement(ome);  
			  
			NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();  
			distDFeInteresse.setNfeDadosMsg(dadosMsgType0);  
        
        
        
        NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub(getCaminhoWebService());
        
        
        
        setRetorno(stub.nfeDistDFeInteresse(distDFeInteresse).getNfeDistDFeInteresseResult().getExtraElement().toString());
        
        //setRetorno(stub.nfeDistDFeInteresse(dadosMsg).getNfeDistDFeInteresseResult().getExtraElement().toString());

        
        

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
