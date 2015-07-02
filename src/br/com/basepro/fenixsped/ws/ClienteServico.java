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

import br.com.basepro.fenixsped.util.IOXml;
import br.com.basepro.fenixsped.ws.nfestatusservico.NfeStatusServico_PortType;
import br.com.basepro.fenixsped.ws.nfestatusservico.NfeStatusServico_Service;
import br.com.basepro.fenixsped.ws.nfestatusservico.NfeStatusServico_ServiceLocator;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Timestamp;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Text;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author vinicius
 */
public class ClienteServico {

	private static String caminhoXML;
	private static String caminhoXMLRetorno;
	private static String caminhoCertificado;
	private static String senhaCertificado;
	private static String caminhoWebService;
	private static String retorno;
	private static Document documentoXml;
	private static Document documentoXmlRetorno;
	private static String versaoDados;
	private static String codUf;
	private static boolean debugando = true;
	private static boolean certificaServidor = true;
	private static boolean certificaCliente = true;
	private static String padrao = "spednfe";

	
	/**
	 * Recebe parametros recebidos de linha de comando e preenche as variáveis.
	 * @param args Array de argumentos
	 */
	public static void recebeParametros(String[] args) {
		if (isDebugando()) {
			System.out.println("recebendo parametros v 0.1.2");
		}
		if (args.length < 5) {
			System.out.println("Informar os 5 Paramentros:"
				+ "\n caminhoXML "
				+ "\n caminhoXMLRetorno "
				+ "\n caminhoCertificado "
				+ "\n senhaCertificado "
				+ "\n caminhoWebService ");
			System.exit(1);
		}
		try {
			setCaminhoXML(args[0]);
			setCaminhoXMLRetorno(args[1]);
			setCaminhoCertificado(args[2]);
			setSenhaCertificado(args[3]);
			setCaminhoWebService(args[4]);
			if (isCertificaCliente()) {
				setCertificadoCliente();
			}
			if (isCertificaServidor()) {
				setCertificadoServidor();
			}
			setVersaoDados(getDocumentoXml().getDocumentElement().getAttribute("versao"));

			String cVemCodUf = "";

			if (getPadrao().equalsIgnoreCase("spednfe")) {
				if (getDocumentoXml().getDocumentElement().getNodeName().equalsIgnoreCase("consSitNFe")) {
					cVemCodUf = getDocumentoXml().getDocumentElement().getElementsByTagName("chNFe").item(0).getTextContent().substring(0, 2);
				}
				if (getDocumentoXml().getDocumentElement().getNodeName().equalsIgnoreCase("cancNFe")) {
					cVemCodUf = getDocumentoXml().getDocumentElement().getElementsByTagName("chNFe").item(0).getTextContent().substring(0, 2);
				}
				if (getDocumentoXml().getDocumentElement().getNodeName().equalsIgnoreCase("envEvento")) {
					cVemCodUf = getDocumentoXml().getDocumentElement().getElementsByTagName("chNFe").item(0).getTextContent().substring(0, 2);
				}
				if (getDocumentoXml().getDocumentElement().getNodeName().equalsIgnoreCase("consReciNFe")) {
					cVemCodUf = getDocumentoXml().getDocumentElement().getElementsByTagName("nRec").item(0).getTextContent().substring(0, 2);
				}
				if (getDocumentoXml().getDocumentElement().getNodeName().equalsIgnoreCase("downloadNFe")) {
					cVemCodUf = args[5];
				}
				if (cVemCodUf.equalsIgnoreCase("")) {
					cVemCodUf = getDocumentoXml().getDocumentElement().getElementsByTagName("cUF").item(0).getTextContent();
				}
			}

			setCodUf(cVemCodUf);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		//Recebe os Parametros
		recebeParametros(args);

		// Instancia objetos especificos
		NfeStatusServico_Service service = new NfeStatusServico_ServiceLocator();
		NfeStatusServico_PortType nfeStatus = service.getNfeStatusServico(new URL(getCaminhoWebService()));

		// Executa buscando retorno
		setRetorno(nfeStatus.nfeStatusServicoNF(getCabecalhoMensagem(), getConteudoXML()));

		// Salva o retorno
		salvaRetorno(getCaminhoXMLRetorno());
	}

	public static void setCertificadoCliente() {
		if (isDebugando()) {
			System.out.println("set certificado cliente");
		}



		//System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		//Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider()); 


		System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		System.setProperty("javax.net.ssl.keyStore", getCaminhoCertificado() + "con");
		System.setProperty("javax.net.ssl.keyStorePassword", getSenhaCertificado());
	}

	public static void setCertificadoServidor() throws Exception {
		if (isDebugando()) {
			System.out.println("set certificado fornecedor");
		}
		System.out.println((new URL(getCaminhoWebService())).getHost());
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
		InstallCert.main(new String[]{new String((new URL(getCaminhoWebService())).getHost())});
	}

	public static String getConteudoXML() throws TransformerConfigurationException, TransformerException, FileNotFoundException, IOException {
		if (isDebugando()) {
			System.out.println("get conteudo xml");
		}
		/*
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		//transformer.setOutputProperty(OutputKeys.INDENT, "no");
		
		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(getDocumentoXml());
		transformer.transform(source, result);
		
		String xmlString = result.getWriter().toString();
		System.out.println(xmlString);
		
		return xmlString;
		 */

		BufferedReader reader = new BufferedReader(new FileReader(getCaminhoXML()));

		StringBuffer buffer = new StringBuffer();
		String line = reader.readLine();
		while (line != null) {
			buffer.append(line);
			buffer.append('\n');
			line = reader.readLine();
		}
		return buffer.substring(0);
	}
/**
	 * Salva o arquivo de retorno com a resposta do webserver
	 * Salva 2x o arquivo acrescentando timestamp .xml ao nome do arquivo, importante para não sobre-escrever arquivos de retorno perdendo o original. Se não desejar este comportamento utilize: salvaRetorno(String nomeArquivoSaida, fales)
	 * @param nomeArquivoSaida Caminho onde salvar o retorno
	 * @throws IOException 
	 */
	public static void salvaRetorno(String nomeArquivoSaida) throws IOException {
		salvaRetorno(nomeArquivoSaida, true);
	}
	
/**
	 * Salva o arquivo de retorno com a resposta do webserver
	 * @param nomeArquivoSaida Caminho onde salvar o retorno
	 * @param lBkp Salva 2x o arquivo acrescentando timestamp .xml ao nome do arquivo, importante para não sobre-escrever arquivos de retorno perdendo o original.
	 * @throws IOException 
	 */
	public static void salvaRetorno(String nomeArquivoSaida, boolean lBkp) throws IOException {
		java.util.Date date= new java.util.Date();
		String cTimestamp = (new Timestamp(date.getTime())).toString();
		String nomeArquivoSaidaBkp = nomeArquivoSaida+"-"+cTimestamp+".xml";
		if (isDebugando()) {
			System.out.println("salvando retorno");
			if (lBkp){
				System.out.println(nomeArquivoSaidaBkp);
			}
			
		}
		
		System.out.println();
		FileWriter fw = new FileWriter(nomeArquivoSaida);
		fw.write(getRetorno());
		fw.close();
		if (lBkp){
			FileWriter fwbkp = new FileWriter(nomeArquivoSaidaBkp);
			fwbkp.write(getRetorno());
			fwbkp.close();
		}
	}

	/**
	 * Salva conteúdo em arquivo
	 * @param nomeArquivoSaida Destino do novo arquivo a ser salvo
	 * @param conteudo Conteúdo a ser salvo
	 * @throws IOException 
	 */
	public static void salvaArquivo(String nomeArquivoSaida, String conteudo) throws IOException {
		//if (isDebugando()) {
		System.out.println("salvando arquivo:" + nomeArquivoSaida);
		//}
		FileWriter fw = new FileWriter(nomeArquivoSaida);
		fw.write(conteudo);
		fw.close();
	}

	/**
	 * Salva conteúdo de documento XML em arquivo
	 * @param nomeArquivoSaida Destino do novo arquivo a ser salvo
	 * @param conteudo Documento XML a ser salvo.
	 * @throws IOException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException 
	 */
	public static void salvaArquivo(String nomeArquivoSaida, Document conteudo) throws IOException, TransformerConfigurationException, TransformerException {
		if (isDebugando()) {
			System.out.println("salvando documento em arquivo:" + nomeArquivoSaida);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(conteudo);
		StreamResult result = new StreamResult(new File(nomeArquivoSaida));
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out); 

		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		transformer.transform(source, result);

	}

	/**
	 * Salva conteúdo de um Node XML em um arquivo separado
	 * @param nomeArquivoSaida Destino do novo arquivo a ser 
	 * @param conteudo Node do XML a ser salvo.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 * @throws SAXException 
	 */
	public static void salvaArquivo(String nomeArquivoSaida, Node conteudo) throws ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException, SAXException {
		if (isDebugando()) {
			System.out.println("salvando documento em arquivo:" + nomeArquivoSaida);
		}

		// Fabrica documento xml
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(false);
		// Construtor 
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		// Documento
		Document doc = docBuilder.newDocument();

		doc.setXmlStandalone(true);
		doc.appendChild(doc.importNode(conteudo, true));

		salvaArquivo(nomeArquivoSaida, doc);

	}

	public static String getCaminhoXML() {
		if (isDebugando()) {
			System.out.println("getcaminhoxml");
		}
		return caminhoXML;
	}

	public static void setCaminhoXML(String CaminhoXML) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		if (isDebugando()) {
			System.out.println("set caminho xml");
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(CaminhoXML));

		setDocumentoXml(doc);

		ClienteServico.caminhoXML = CaminhoXML;

	}

	public static String getCaminhoCertificado() {
		if (isDebugando()) {
			System.out.println("get caminho certificado");
		}
		return caminhoCertificado;
	}

	public static void setCaminhoCertificado(String CaminhoCertificado) {
		if (isDebugando()) {
			System.out.println("set caminho certificado");
		}
		ClienteServico.caminhoCertificado = CaminhoCertificado;
	}

	public static String getSenhaCertificado() {
		if (isDebugando()) {
			System.out.println("get senha certificado");
		}
		return senhaCertificado;
	}

	public static void setSenhaCertificado(String SenhaCertificado) {
		if (isDebugando()) {
			System.out.println("set senha certificado");
		}
		ClienteServico.senhaCertificado = SenhaCertificado;
	}

	public static String getCaminhoWebService() {
		if (isDebugando()) {
			System.out.println("get caminho webservice");
		}
		return caminhoWebService;
	}

	public static void setCaminhoWebService(String CaminhoWebService) {
		if (isDebugando()) {
			System.out.println("caminho web service");
		}
		ClienteServico.caminhoWebService = CaminhoWebService;
	}

	public static String getCabecalhoMensagem() {
		if (isDebugando()) {
			System.out.println("get cabecalho mensagem");
		}
		String versao = getVersaoDados();
		System.out.println("Versão dos dados:" + versao);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<cabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe\" " + "versao=\"1.02\"><versaoDados>" + versao + "</versaoDados>" + "</cabecMsg>";
	}

	public static String getRetorno() {
		if (isDebugando()) {
			System.out.println("get retorno");
		}
		return retorno;
	}

	public static void setRetorno(String aRetorno) {
		if (isDebugando()) {
			System.out.println("set retorno");
		}
		retorno = aRetorno;
	}

	public static String getCaminhoXMLRetorno() {
		if (isDebugando()) {
			System.out.println("get caminho xml retorno");
		}
		return caminhoXMLRetorno;
	}

	public static void setCaminhoXMLRetorno(String aCaminhoXMLRetorno) {
		if (isDebugando()) {
			System.out.println("set caminho xml retorno");
		}
		caminhoXMLRetorno = aCaminhoXMLRetorno;
	}

	public static Document getDocumentoXml() {
		if (isDebugando()) {
			System.out.println("get documento xml");
		}
		return documentoXml;
	}

	/**
	 * Acesso ao documento xml de retorno
	 * @return Documento XML do retorno
	 */
	public static Document getDocumentoXmlRetorno() {
		if (isDebugando()) {
			System.out.println("get documento xml");
		}
		return documentoXmlRetorno;
	}

	public static void setDocumentoXml(Document aDocumentoXml) {
		if (isDebugando()) {
			System.out.println("set documento xml");
		}
		documentoXml = aDocumentoXml;
	}

	/**
	 * Define documento de retorno para ser maniplado via doc
	 * @param aDocumentoXml 
	 */
	public static void setDocumentoXmlRetorno(Document aDocumentoXml) {
		if (isDebugando()) {
			System.out.println("set documento xml");
		}
		documentoXmlRetorno = aDocumentoXml;
	}

	/**
	 * Define documento xml de retorno para ser manipulado via string
	 * 
	 * @param cCaminhoXML Caminho do XML do retorno a definir no documento.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException 
	 */
	public static void setDocumentoXmlRetorno(String cCaminhoXML) throws ParserConfigurationException, SAXException, IOException {
		if (isDebugando()) {
			System.out.println("set documento xml");
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(cCaminhoXML));

		setDocumentoXmlRetorno(doc);
	}

	public static String getVersaoDados() {
		if (isDebugando()) {
			System.out.println("get versao dados");
		}
		return versaoDados;
	}

	public static void setVersaoDados(String aVersaoDados) {
		if (isDebugando()) {
			System.out.println("set versao dados");
		}
		versaoDados = aVersaoDados;
	}

	public static String getCodUf() {
		if (isDebugando()) {
			System.out.println("get coduf :" + codUf);
		}
		return codUf;
	}

	public static void setCodUf(String aCodUf) {
		if (isDebugando()) {
			System.out.println("set coduf: " + aCodUf);
		}
		codUf = aCodUf;
	}

	public static boolean isDebugando() {
		return debugando;
	}

	public static void setDebugando(boolean aDebugando) {
		debugando = aDebugando;
	}

	/**
	 * @return the certificaServidor
	 */
	public static boolean isCertificaServidor() {
		return certificaServidor;
	}

	/**
	 * @param aCertificaServidor the certificaServidor to set
	 */
	public static void setCertificaServidor(boolean aCertificaServidor) {
		certificaServidor = aCertificaServidor;
	}

	/**
	 * @return the certificaCliente
	 */
	public static boolean isCertificaCliente() {
		return certificaCliente;
	}

	/**
	 * @param aCertificaCliente the certificaCliente to set
	 */
	public static void setCertificaCliente(boolean aCertificaCliente) {
		certificaCliente = aCertificaCliente;
	}

	/**
	 * @return the padrao
	 */
	public static String getPadrao() {
		return padrao;
	}

	/**
	 * @param aPadrao the padrao to set
	 */
	public static void setPadrao(String aPadrao) {
		padrao = aPadrao;
	}
}
