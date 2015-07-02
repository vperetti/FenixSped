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

import br.com.basepro.fenixsped.assinaturaXml.*;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
//import sun.misc.BASE64Encoder;

import java.util.Enumeration;

/**
 * Classe para assinatura de notas eletrônicas de serviços padrão abrasf
 * @author Vinicius Peretti
 */
public class AssinaDocumentos {

	private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
	private static final String PROVIDER_CLASS_NAME = "org.jcp.xml.dsig.internal.dom.XMLDSigRI";
	private static final String PROVIDER_NAME = "jsr105Provider";

	public void assinar3(String caminhoXml, String caminhoCertificado, String senha, String caminhoXmlNovo) throws Exception {
		String tag = "";
		String tipoDocumento = "";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docs = builder.parse(new File(caminhoXml));

		// Busca nome do elemento raiz para verificar q tipo de documento a assinar
		tipoDocumento = docs.getDocumentElement().getNodeName();


		if (tipoDocumento.equals("EnviarLoteRpsEnvio")) {
			tag = "Rps";
		} else if (tipoDocumento.equals("Rps")) {
			tag = "Rps";
		} else if (tipoDocumento.equals("CancelarNfseEnvio")) {
			tag = "InfPedidoCancelamento";
		} else if (tipoDocumento.equals("inutNFe")) {
			tag = "Inut";
		} else {
			throw new Exception("Tipo de documento não reconhecido para assinatura.");
		}
		GeralXML.assinarXML(caminhoXml, caminhoXmlNovo, tag, caminhoCertificado, senha);
	}

	public Document assinarDocument(Document doc, String tag, String caminhoCertificado, String senha) throws Exception {
		//DocumentBuilder builder = factory.newDocumentBuilder();
		//Document docs = builder.parse(new File(caminhoXml));
		//tag = "infNFe";


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);

		//Create a DOM XMLSignatureFactory that will be used to
		//generate the enveloped signature.        
		String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());

		// Create a Reference to the enveloped document (in this case,
		// you are signing the whole document, so a URI of "" signifies
		// that, and also specify the SHA1 digest algorithm and
		// the ENVELOPED Transform.
		ArrayList transformList = new ArrayList();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
		transformList.add(envelopedTransform);
		transformList.add(c14NTransform);




		// Load the KeyStore and get the signing key and certificate.
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(caminhoCertificado), senha.toCharArray());
		Enumeration aliasesEnum = ks.aliases();
		String alias = "";
		while (aliasesEnum.hasMoreElements()) {
			alias = (String) aliasesEnum.nextElement();

			if (ks.isKeyEntry(alias)) {
				break;
			}
		}

		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha.toCharArray()));

		X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

		// Create the KeyInfo containing the X509Data.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List x509Content = new ArrayList();
		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

		// Instantiate the document to be signed.
		//DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//dbf.setNamespaceAware(true);
		//Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));

		//doc.setXmlStandalone(true);  

		//Lista cada tag a ser assinada
		for (int i = 0; i < doc.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {

			// Obtem elemento do documento a ser assinado, será criado uma
			// REFERENCE para o mesmo
			NodeList elements = doc.getElementsByTagName(tag);
			Element el = (Element) elements.item(i);
			String id = el.getAttribute("Id");
			Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

			// Create the SignedInfo.
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
				CanonicalizationMethod.INCLUSIVE,
				(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				Collections.singletonList(ref));

			// Create a DOMSignContext and specify the RSA PrivateKey and
			// location of the resulting XMLSignature's parent element.
			DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());

			// Create the XMLSignature, but don't sign it yet.
			XMLSignature signature = fac.newXMLSignature(si, ki);

			// Marshal, generate, and sign the enveloped signature.
			signature.sign(dsc);
		}

		// Output the resulting document.
		//OutputStream os = new FileOutputStream(caminhoXmlNovo);
		//TransformerFactory tf = TransformerFactory.newInstance();
		//Transformer trans = tf.newTransformer();
		//trans.transform(new DOMSource(doc), new StreamResult(os));

		// Verifica as assinaturas
		// Find Signature element.
		NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		for (int i = 0; i < nl.getLength(); i++) {
			// Create a DOMValidateContext and specify a KeySelector and document context.
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(i));
			// Unmarshal the XMLSignature.
			XMLSignature signatures = fac.unmarshalXMLSignature(valContext);
			// Validate the XMLSignature.
			boolean coreValidity = signatures.validate(valContext);
			// Check core validation status.
			if (coreValidity == false) {
				System.err.println("Falha na Assinatura!");
			} else {
				System.out.println("Assinatura Correta!");
			}
		}
		return doc;
	}

	public void assinar(String caminhoXml, String caminhoCertificado, String senha, String caminhoXmlNovo, String tag, String codigoMunicipio) throws Exception {
		//String tag = "";
		String tipoDocumento = "";
		String stringId = "id";


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docs = builder.parse(new File(caminhoXml));

		// Busca nome do elemento raiz para verificar q tipo de documento a assinar
		tipoDocumento = docs.getDocumentElement().getNodeName();


		System.out.println("tipo documento:" + tipoDocumento);
		System.out.println("caminhoXml:" + caminhoXml);
		System.out.println("caminhoCertificado:" + caminhoCertificado);
		System.out.println("senha:" + senha);
		System.out.println("caminhoXmlNovo:" + caminhoXmlNovo);
		System.out.println("tag:" + tag);

		System.out.println("Municipio em assinar:" + codigoMunicipio);

		if (codigoMunicipio.equalsIgnoreCase("261160")) {
			stringId = "Id";
		}

		System.out.println("id a assinar:" + stringId);


		/*
		if (tipoDocumento.equals("EnviarLoteRpsEnvio")) {
		tag = "LoteRps";
		} else if (tipoDocumento.equals("Rps")) {
		tag = "InfRps";
		} else if (tipoDocumento.equals("cancNFe")) {
		tag = "infCanc";
		} else if (tipoDocumento.equals("inutNFe")) {
		tag = "infInut";
		} else {
		throw new Exception("Tipo de documento não reconhecido para assinatura.");
		}
		 */
		if (tipoDocumento.equals("CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("InfRps")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
		} 
		if (tipoDocumento.equals("p1:CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("tc:InfRps")) {
				if (codigoMunicipio.equalsIgnoreCase("510340")) {
					tag = "tc:InfPedidoCancelamento";
				}else{
					tag = "InfPedidoCancelamento";	
				}
			}else {
				tag = "";
			}
		}
		System.out.print("tag: "+tag);

		//Create a DOM XMLSignatureFactory that will be used to
		//generate the enveloped signature.        
		String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());

		// Create a Reference to the enveloped document (in this case,
		// you are signing the whole document, so a URI of "" signifies
		// that, and also specify the SHA1 digest algorithm and
		// the ENVELOPED Transform.
		ArrayList transformList = new ArrayList();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
		transformList.add(envelopedTransform);
		//transformList.add(c14NTransform);




		// Load the KeyStore and get the signing key and certificate.
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(caminhoCertificado), senha.toCharArray());
		Enumeration aliasesEnum = ks.aliases();
		String alias = "";
		while (aliasesEnum.hasMoreElements()) {
			alias = (String) aliasesEnum.nextElement();

			if (ks.isKeyEntry(alias)) {
				break;
			}
		}

		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha.toCharArray()));

		X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

		// Create the KeyInfo containing the X509Data.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List x509Content = new ArrayList();
		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

		// Instantiate the document to be signed.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		//dbf.setNamespaceAware(false);
		Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));



		//Lista cada tag a ser assinada
		for (int i = 0; i < doc.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {

			// Obtem elemento do documento a ser assinado, será criado uma
			// REFERENCE para o mesmo
			NodeList elements = docs.getElementsByTagName(tag);
			Element el = (Element) elements.item(i);
			String id = el.getAttribute(stringId);
			Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

			// Create the SignedInfo.
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
				CanonicalizationMethod.INCLUSIVE,
				(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				Collections.singletonList(ref));

			// Create a DOMSignContext and specify the RSA PrivateKey and
			// location of the resulting XMLSignature's parent element.
			DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());

			// Create the XMLSignature, but don't sign it yet.
			XMLSignature signature = fac.newXMLSignature(si, ki);

			// Marshal, generate, and sign the enveloped signature.
			signature.sign(dsc);
		}




		doc.setXmlStandalone(true);
		doc.getDocumentElement().removeAttribute("xmlns:ns2");
//        if (tag.equalsIgnoreCase("infNFe")) {
//            ((Element) doc.getDocumentElement().getElementsByTagName("NFe").item(0)).setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
//            ((Element) doc.getDocumentElement().getElementsByTagName("infNFe").item(0)).setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//        }


		// Output the resulting document.
		//OutputStream os = new FileOutputStream(caminhoXmlNovo);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));

		//System.out.println("===\n"+os.toString()+"\n====");


		//novo
		File fout = new File(caminhoXmlNovo);
		FileOutputStream out = new FileOutputStream(fout);
		out.write(os.toByteArray());



		// Verifica as assinaturas
		// Find Signature element.
		NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		for (int i = 0; i < nl.getLength(); i++) {
			// Create a DOMValidateContext and specify a KeySelector and document context.
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(i));
			// Unmarshal the XMLSignature.
			XMLSignature signatures = fac.unmarshalXMLSignature(valContext);
			// Validate the XMLSignature.
			boolean coreValidity = signatures.validate(valContext);
			// Check core validation status.
			if (coreValidity == false) {
				System.err.println("Falha na Assinatura!");
			} else {
				System.out.println("Assinatura Correta!");
			}
		}
	}

	public void assinar_f(String caminhoXml, String caminhoCertificado, String senha, String caminhoXmlNovo) throws Exception {
		String tag = "";
		String tipoDocumento = "";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docs = builder.parse(new File(caminhoXml));

		// Busca nome do elemento raiz para verificar q tipo de documento a assinar
		tipoDocumento = docs.getDocumentElement().getNodeName();


		if (tipoDocumento.equals("EnviarLoteRpsEnvio")) {
			tag = "InfRps";
		} else if (tipoDocumento.equals("Rps")) {
			tag = "InfRps";
		} else if (tipoDocumento.equals("cancNFe")) {
			tag = "infCanc";
		} else if (tipoDocumento.equals("inutNFe")) {
			tag = "infInut";
		} else {
			throw new Exception("Tipo de documento não reconhecido para assinatura.");
		}



		//Create a DOM XMLSignatureFactory that will be used to
		//generate the enveloped signature.        
		String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());

		// Create a Reference to the enveloped document (in this case,
		// you are signing the whole document, so a URI of "" signifies
		// that, and also specify the SHA1 digest algorithm and
		// the ENVELOPED Transform.
		ArrayList transformList = new ArrayList();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
		transformList.add(envelopedTransform);
		transformList.add(c14NTransform);




		// Load the KeyStore and get the signing key and certificate.
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(caminhoCertificado), senha.toCharArray());
		Enumeration aliasesEnum = ks.aliases();
		String alias = "";
		while (aliasesEnum.hasMoreElements()) {
			alias = (String) aliasesEnum.nextElement();

			if (ks.isKeyEntry(alias)) {
				break;
			}
		}

		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha.toCharArray()));

		X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

		// Create the KeyInfo containing the X509Data.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List x509Content = new ArrayList();
		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

		// Instantiate the document to be signed.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));

		doc.setXmlStandalone(true);

		//Lista cada tag a ser assinada
		for (int i = 0; i < doc.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {

			// Obtem elemento do documento a ser assinado, será criado uma
			// REFERENCE para o mesmo
			NodeList elements = docs.getElementsByTagName(tag);
			Element el = (Element) elements.item(i);
			String id = el.getAttribute("id");
			Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

			// Create the SignedInfo.
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
				CanonicalizationMethod.INCLUSIVE,
				(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				Collections.singletonList(ref));

			// Create a DOMSignContext and specify the RSA PrivateKey and
			// location of the resulting XMLSignature's parent element.
			DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());

			// Create the XMLSignature, but don't sign it yet.
			XMLSignature signature = fac.newXMLSignature(si, ki);

			// Marshal, generate, and sign the enveloped signature.
			////signature.sign(dsc);
		}

		// Output the resulting document.
		OutputStream os = new FileOutputStream(caminhoXmlNovo);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));

		// Verifica as assinaturas
		// Find Signature element.
		NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		for (int i = 0; i < nl.getLength(); i++) {
			// Create a DOMValidateContext and specify a KeySelector and document context.
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(i));
			// Unmarshal the XMLSignature.
			XMLSignature signatures = fac.unmarshalXMLSignature(valContext);
			// Validate the XMLSignature.
			boolean coreValidity = signatures.validate(valContext);
			// Check core validation status.
			if (coreValidity == false) {
				System.err.println("Falha na Assinatura!");
			} else {
				System.out.println("Assinatura Correta!");
			}
		}
	}
	/*
	public static void main(String[] args) throws Exception {
	
	if (args.length < 3) {
	System.out.println("Argumentos:\n\n comando <arquivoXmlOrigem> <arquivoCertificado> <senha> [arquivoXmlDestino]");
	return;
	}
	String caminhoXml = args[0];
	String caminhoCertificado = args[1];
	String senha = args[2];
	String arquivoXmlNovo = args[0];
	if (args.length == 4) {
	arquivoXmlNovo = args[3];
	}
	
	
	File file = new File(caminhoXml);
	if (!file.exists()) {
	System.out.println("Arquivo " + caminhoXml + " não encontrado!");
	return;
	}
	file = new File(caminhoCertificado);
	if (!file.exists()) {
	System.out.println("Arquivo " + caminhoCertificado + " não encontrado!");
	return;
	}
	try {
	AssinaDocumentos t = new AssinaDocumentos();
	if (isLote(caminhoXml)){
	String caminhoXmlNFe = caminhoXml+"-nfe.xml";
	StringBuffer bufferLoteCab = new StringBuffer();  
	StringBuffer bufferLoteRod = new StringBuffer();  
	StringBuffer bufferNFe = new StringBuffer();
	StringBuffer bufferLoteAss = new StringBuffer();
	
	
	
	//separa nfe em caminhoXmlNFe
	BufferedReader reader = new BufferedReader(new FileReader(caminhoXml));  
	
	bufferNFe.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	
	String line = reader.readLine();  
	int nLinha = 1;
	while (line!=null) {  
	if (nLinha <= 8){
	bufferLoteCab.append(line);  
	//bufferLoteCab.append('\n');  
	}else{
	if (line.equalsIgnoreCase("</ListaRps>") ){
	bufferLoteRod.append(line);
	}else if (line.equalsIgnoreCase("</LoteRps>") ){
	bufferLoteRod.append(line);
	}else if (line.equalsIgnoreCase("</EnviarLoteRpsEnvio>") ){
	bufferLoteRod.append(line);
	}else{
	bufferNFe.append(line);
	}
	}
	nLinha++;
	line = reader.readLine();  
	}  
	
	FileWriter fw = new FileWriter(caminhoXmlNFe);  
	fw.write(bufferNFe.substring(0));  
	fw.close();
	
	t.assinar(caminhoXmlNFe, caminhoCertificado, senha, caminhoXmlNFe+"-assinado");    
	
	
	
	//monta arquivo lote no arquivoXmlNovo
	bufferLoteAss.append(bufferLoteCab);
	BufferedReader readerAss = new BufferedReader(new FileReader(caminhoXmlNFe+"-assinado"));  
	
	String lineAss = readerAss.readLine();  
	
	int nLinhaAss = 1;
	while (lineAss!=null) {  
	if (nLinhaAss == 1){
	bufferLoteAss.append(lineAss.substring(38));  
	bufferLoteAss.append('\n');  
	}else{
	bufferLoteAss.append(lineAss); 
	bufferLoteAss.append('\n');  
	}
	nLinhaAss++;
	lineAss = readerAss.readLine();  
	}  
	bufferLoteAss.append(bufferLoteRod);
	
	
	FileWriter fwAss = new FileWriter(arquivoXmlNovo);  
	fwAss.write(bufferLoteAss.substring(0));  
	fwAss.close();                
	
	
	
	
	
	
	
	t.assinar(arquivoXmlNovo, caminhoCertificado, senha, arquivoXmlNovo);
	
	
	
	
	
	}else{
	t.assinar(caminhoXml, caminhoCertificado, senha, arquivoXmlNovo);
	}
	
	
	
	
	System.out.println("Arquivo xml assinado com sucesso " + caminhoXml + "!");
	} catch (Exception e) {
	System.out.println("Erro ao tentar assinar arquivo xml! \n\n" );
	e.printStackTrace();
	}
	}
	
	
	 *
	 */

	public static void main(String[] args) throws Exception {

		if (args.length < 3) {
			System.out.println("Argumentos:\n\n comando <arquivoXmlOrigem> <arquivoCertificado> <senha> [arquivoXmlDestino]");
			return;
		}
		String caminhoXml = args[0];
		String caminhoCertificado = args[1];
		String senha = args[2];
		String arquivoXmlNovo = args[3];
		String codigoMunicipio = args[4];

		System.out.println("Municipio" + codigoMunicipio);

		File file = new File(caminhoXml);
		if (!file.exists()) {
			System.out.println("Arquivo " + caminhoXml + " não encontrado!");
			return;
		}
		file = new File(caminhoCertificado);
		if (!file.exists()) {
			System.out.println("Arquivo " + caminhoCertificado + " não encontrado!");
			return;
		}
		try {
			AssinaDocumentos t = new AssinaDocumentos();
			/*
			if (isLote(caminhoXml)){
			String caminhoXmlNFe = caminhoXml+"-nfe.xml";
			StringBuffer bufferLoteCab = new StringBuffer();
			StringBuffer bufferLoteRod = new StringBuffer();
			StringBuffer bufferNFe = new StringBuffer();
			StringBuffer bufferLoteAss = new StringBuffer();
			
			
			
			//separa nfe em caminhoXmlNFe
			BufferedReader reader = new BufferedReader(new FileReader(caminhoXml));
			
			bufferNFe.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			
			String line = reader.readLine();
			int nLinha = 1;
			while (line!=null) {
			if (nLinha <= 8){
			bufferLoteCab.append(line);
			//bufferLoteCab.append('\n');
			}else{
			if (line.equalsIgnoreCase("</ListaRps>") ){
			bufferLoteRod.append(line);
			}else if (line.equalsIgnoreCase("</LoteRps>") ){
			bufferLoteRod.append(line);
			}else if (line.equalsIgnoreCase("</EnviarLoteRpsEnvio>") ){
			bufferLoteRod.append(line);
			}else{
			bufferNFe.append(line);
			}
			}
			nLinha++;
			line = reader.readLine();
			}
			
			FileWriter fw = new FileWriter(caminhoXmlNFe);
			fw.write(bufferNFe.substring(0));
			fw.close();
			
			//              t.assinar(caminhoXmlNFe, caminhoCertificado, senha, caminhoXmlNFe+"-assinado");
			
			
			
			//monta arquivo lote no arquivoXmlNovo
			bufferLoteAss.append(bufferLoteCab);
			BufferedReader readerAss = new BufferedReader(new FileReader(caminhoXmlNFe+"-assinado"));
			
			String lineAss = readerAss.readLine();
			
			int nLinhaAss = 1;
			while (lineAss!=null) {
			if (nLinhaAss == 1){
			bufferLoteAss.append(lineAss.substring(38));
			bufferLoteAss.append('\n');
			}else{
			bufferLoteAss.append(lineAss);
			bufferLoteAss.append('\n');
			}
			nLinhaAss++;
			lineAss = readerAss.readLine();
			}
			bufferLoteAss.append(bufferLoteRod);
			
			
			FileWriter fwAss = new FileWriter(arquivoXmlNovo);
			fwAss.write(bufferLoteAss.substring(0));
			fwAss.close();
			
			
			
			
			
			
			
			//                t.assinar(arquivoXmlNovo, caminhoCertificado, senha, arquivoXmlNovo);
			
			
			
			
			
			}else{
			//              t.assinar(caminhoXml, caminhoCertificado, senha, arquivoXmlNovo);
			}
			
			 */
			if (codigoMunicipio.equalsIgnoreCase("510340")) {
				t.assinar(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "tc:InfRps", codigoMunicipio);
				t.assinar(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteRps", codigoMunicipio);

			} else {
				t.assinar(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "InfRps", codigoMunicipio);
				t.assinar(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteRps", codigoMunicipio);

			}




			System.out.println("Arquivo xml assinado com sucesso " + caminhoXml + "!");
		} catch (Exception e) {
			System.out.println("Erro ao tentar assinar arquivo xml! \n\n");
			e.printStackTrace();
		}
	}

	public static boolean isLote(String caminhoXml) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docs = builder.parse(new File(caminhoXml));

		// Busca nome do elemento raiz para verificar q tipo de documento a assinar

		if (docs.getDocumentElement().getNodeName().equals("EnviarLoteRpsEnvio")) {
			return true;
		} else {
			return false;
		}
	}
}
