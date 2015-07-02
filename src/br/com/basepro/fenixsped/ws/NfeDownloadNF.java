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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.basepro.fenixsped.ws;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Consome serviço de download do XML da NFe
 * 
 * @author Vinicius Peretti
 */
public class NfeDownloadNF extends ClienteServico {

	public static void main(String[] args) throws Exception {
		
		//Se deseja debugar saida deste
		//setDebugando(false);

		//Recebe os Parametros
		recebeParametros(args);

		// Consome serviço
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
		XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
		br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub.NfeDadosMsg.Factory.parse(dad);
		br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub.NfeCabecMsg cabecMsg = new br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub.NfeCabecMsg();
		cabecMsg.setCUF(getCodUf());
		cabecMsg.setVersaoDados(getVersaoDados());
		br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub.NfeCabecMsgE();
		cabecMsgE.setNfeCabecMsg(cabecMsg);
		br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub stub = new br.com.basepro.fenixsped.ws.nfedownloadnf.NfeDownloadNFStub(getCaminhoWebService());
		setRetorno(stub.nfeDownloadNF(dadosMsg, cabecMsgE).getExtraElement().toString());

		// Salva o retorno
		salvaRetorno(getCaminhoXMLRetorno());

		// Define documento de retorno para tratamento
		setDocumentoXmlRetorno(getCaminhoXMLRetorno());

		// Se retornou xml da nota
		if (getDocumentoXmlRetorno().getDocumentElement().getElementsByTagName("nfeProc").getLength() > 0) {
			if (isDebugando()) {
				System.out.println("vai salvar em args[6]");
				System.out.println(args[6]);
			}

			// Fabrica de Documento
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(false);
			// Construtor
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			// Documento
			Document doc = docBuilder.newDocument();
			
			// Define Standalone
			doc.setXmlStandalone(true);
			// Adiciona somentee o nfeProc do retorno como Root do novo documento
			doc.appendChild(doc.importNode(getDocumentoXmlRetorno().getDocumentElement().getElementsByTagName("nfeProc").item(0), true));
			
			// Define os namespaces para compatibilizar com o schema
			((Element) doc.getElementsByTagName("nfeProc").item(0)).setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");

			((Element) doc.getElementsByTagName("NFe").item(0)).setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");

			((Element) doc.getElementsByTagName("protNFe").item(0)).setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
			
			// Salva
			salvaArquivo(args[6], doc);
		}


	}
}
