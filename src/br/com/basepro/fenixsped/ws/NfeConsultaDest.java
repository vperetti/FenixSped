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

/**
 *
 * @author vinicius
 */
public class NfeConsultaDest extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        recebeParametros(args);

        if (isDebugando()) {
            System.out.println("Comunicando com geral");
        }

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
        XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
        br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeDadosMsg.Factory.parse(dad);
        br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsg();
        cabecMsg.setCUF(getCodUf());
        cabecMsg.setVersaoDados(getVersaoDados());
        br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub.NfeCabecMsgE();
        cabecMsgE.setNfeCabecMsg(cabecMsg);
        br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub stub = new br.com.basepro.fenixsped.ws.nfeconsultadest.NFeConsultaDestStub(getCaminhoWebService());
        setRetorno(stub.nfeConsultaNFDest(dadosMsg, cabecMsgE).getExtraElement().toString());

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
