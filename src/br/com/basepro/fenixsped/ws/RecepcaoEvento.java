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
public class RecepcaoEvento extends ClienteServico {

	public static void main(String[] args) throws Exception {

		//Recebe os Parametros
		recebeParametros(args);

		if (getCodUf().equalsIgnoreCase("50")) {
			if (isDebugando()) {
				System.out.println("Comunicando com 50 = MS");
			}

			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
			XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeDadosMsg.Factory.parse(dad);
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsg();
			cabecMsg.setCUF(getCodUf());
			cabecMsg.setVersaoDados(getVersaoDados());
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsgE();
			cabecMsgE.setNfeCabecMsg(cabecMsg);
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub stub = new br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub(getCaminhoWebService());
			setRetorno(stub.nfeRecepcaoEvento(dadosMsg, cabecMsgE).getExtraElement().toString());

		} else if (getCodUf().equalsIgnoreCase("51")) {
			if (isDebugando()) {
				System.out.println("Comunicando com 51 = MT");
			}

			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
			XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
			br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub.NfeDadosMsg.Factory.parse(dad);
			br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub.NfeCabecMsg();
			cabecMsg.setCUF(getCodUf());
			cabecMsg.setVersaoDados(getVersaoDados());
			br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub.NfeCabecMsgE();
			cabecMsgE.setNfeCabecMsg(cabecMsg);
			br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub stub = new br.com.basepro.fenixsped.ws.mt2.RecepcaoEventoStub(getCaminhoWebService());
			setRetorno(stub.nfeRecepcaoEvento(dadosMsg, cabecMsgE).getExtraElement().toString());

		} else {
			if (isDebugando()) {
				System.out.println("Comunicando com geral");
			}

			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
			XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeDadosMsg.Factory.parse(dad);
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsg();
			cabecMsg.setCUF(getCodUf());
			cabecMsg.setVersaoDados(getVersaoDados());
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub.NfeCabecMsgE();
			cabecMsgE.setNfeCabecMsg(cabecMsg);
			br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub stub = new br.com.basepro.fenixsped.ws.recepcaoevento.RecepcaoEventoStub(getCaminhoWebService());
			setRetorno(stub.nfeRecepcaoEvento(dadosMsg, cabecMsgE).getExtraElement().toString());

		}
        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
	}
}
