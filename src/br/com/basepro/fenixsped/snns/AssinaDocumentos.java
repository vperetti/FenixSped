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
package br.com.basepro.fenixsped.snns;

import br.com.basepro.fenixsped.abrasf.*;
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
import org.w3c.dom.Node;

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


		if (tipoDocumento.equals("EnviarLoteDpsEnvio")) {
			tag = "infDPS";
		} else if (tipoDocumento.equals("DPS")) {
			tag = "infDPS";
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
		factory.setNamespaceAware(true);

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
		String stringId = "Id";


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		if (tag.equalsIgnoreCase("tc:InfRps")){
                    factory.setNamespaceAware(true);
                }else{
                    factory.setNamespaceAware(false);
                }
		
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

                /*///
		if (  ( codigoMunicipio.equalsIgnoreCase("261160"))) {
			stringId = "Id";
		}

                if (  ( codigoMunicipio.equalsIgnoreCase("510790"))) {
			stringId = "Id";
		}
                if (  ( codigoMunicipio.equalsIgnoreCase("500370"))) {
			stringId = "Id";
                                                System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");
  //                      org.apache.xml.security.Init.init();
                        System.setProperty("com.sun.org.apache.xml.internal.security.ignoreLineBreaks", "true");
  //                         com.sun.org.apache.xml.internal.security.Init.init();
		}
                
                //Cuiaba
                if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
			stringId = "Id";
                        
                        System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");
  //                      org.apache.xml.security.Init.init();
                        System.setProperty("com.sun.org.apache.xml.internal.security.ignoreLineBreaks", "true");
  //                         com.sun.org.apache.xml.internal.security.Init.init();
		}

                     ///
                */           
                
		System.out.println(" (teste 4 ) id a assinar:" + stringId);

                


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
			}if (tag.equalsIgnoreCase("Pedido")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
		}

                                
		if (tipoDocumento.equals("CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("InfRps")) {
				tag = "InfPedidoCancelamento";	
			}if (tag.equalsIgnoreCase("Pedido")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
		}
                if (tipoDocumento.equals("CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("InfRps")) {
				tag = "InfPedidoCancelamento";	
			}if (tag.equalsIgnoreCase("Pedido")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
                        if (codigoMunicipio.equalsIgnoreCase("510340")) {
                            	tag = "InfPedidoCancelamento";	
                        }
                        if (codigoMunicipio.equalsIgnoreCase("510790")) {
                            	tag = "InfPedidoCancelamento";	
                        }
		} 
                
                
		if (tipoDocumento.equals("p1:CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("tc:InfRps")) {
				if (codigoMunicipio.equalsIgnoreCase("510340")) {
					tag = "tc:InfPedidoCancelamento";
				}else if (codigoMunicipio.equalsIgnoreCase("500370")) {
					tag = "InfPedidoCancelamento";
				}else{
					tag = "InfPedidoCancelamento";	
				}
			}else {
				tag = "";
			}
		}
                
                if (tipoDocumento.equals("ConsultarNfseRpsEnvio")) {
			if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
				tag = "Pedido";	
			}else {
				tag = "";
			}
		} 
/*
                if (  ( codigoMunicipio.equalsIgnoreCase("510790"))) {
                    if (tipoDocumento.equals("EnviarLoteRpsEnvio")) {
                        tag = "InfDeclaracaoPrestacaoServico";
                    }
                    if (tipoDocumento.equals("EnviarLoteRpsSincronoEnvio")) {
                        tag = "InfDeclaracaoPrestacaoServico";
                    }
                }
                */
		System.out.print("tag: "+tag);

		//Create a DOM XMLSignatureFactory that will be used to
		//generate the enveloped signature.        
		String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);
		
                
                XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());
                ////XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

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
		
                if (tag.equalsIgnoreCase("tc:InfRps")){
                    dbf.setNamespaceAware(true);
                }else{
                    dbf.setNamespaceAware(false);
                }
		//dbf.setNamespaceAware(false);
		
                
                //Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));



		//Lista cada tag a ser assinada
		//for (int i = 0; i < doc.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {
                for (int i = 0; i < docs.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {

			// Obtem elemento do documento a ser assinado, será criado uma
			// REFERENCE para o mesmo
			NodeList elements = docs.getElementsByTagName(tag);
			Element el = (Element) elements.item(i);
			String id = el.getAttribute(stringId);
                        el.setIdAttribute(stringId, true);
                        
                        Reference ref ;
                                
                        //Cuiaba
                        if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
                            ref = fac.newReference("" , fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
                        }else{
                            ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
                        }

			// Create the SignedInfo.
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
				CanonicalizationMethod.INCLUSIVE,
				(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				Collections.singletonList(ref));

			// Create a DOMSignContext and specify the RSA PrivateKey and
			// location of the resulting XMLSignature's parent element.
			//DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());
                        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), docs.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());

			// Create the XMLSignature, but don't sign it yet.
			XMLSignature signature = fac.newXMLSignature(si, ki);

			// Marshal, generate, and sign the enveloped signature.
			signature.sign(dsc);
		}




		//doc.setXmlStandalone(true);
                docs.setXmlStandalone(true);
		//doc.getDocumentElement().removeAttribute("xmlns:ns2");
                docs.getDocumentElement().removeAttribute("xmlns:ns2");
//        if (tag.equalsIgnoreCase("infNFe")) {
//            ((Element) doc.getDocumentElement().getElementsByTagName("NFe").item(0)).setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
//            ((Element) doc.getDocumentElement().getElementsByTagName("infNFe").item(0)).setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//        }


		// Output the resulting document.
		//OutputStream os = new FileOutputStream(caminhoXmlNovo);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		//trans.transform(new DOMSource(doc), new StreamResult(os));
                trans.transform(new DOMSource(docs), new StreamResult(os));

		//System.out.println("===\n"+os.toString()+"\n====");


		//novo
		File fout = new File(caminhoXmlNovo);
		FileOutputStream out = new FileOutputStream(fout);
		out.write(os.toByteArray());



		// Verifica as assinaturas
		// Find Signature element.
		//NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
                NodeList nl = docs.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		for (int i = 0; i < nl.getLength(); i++) {
			// Create a DOMValidateContext and specify a KeySelector and document context.
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(i));
                        
                        /*
                        if ( codigoMunicipio.equalsIgnoreCase("510340")) { // CUIABA

                            
                            if (tag.equalsIgnoreCase("tc:InfRps")){
                                
                                //NodeList nlb = doc.getElementsByTagNameNS("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd", "InfRps");
                                NodeList nlb = docs.getElementsByTagNameNS("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd", "InfRps");
                                //valContext.putNamespacePrefix("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd","tc");
                                Node body = nlb.item(0);
                                //valContext.setIdAttributeNS((Element)body, "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "id");
                                
                                valContext.setIdAttributeNS((Element)body, null, stringId);


                                
                            }
                        }*/
                        
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

        public void assinarBetha202(String caminhoXml, String caminhoCertificado, String senha, String caminhoXmlNovo, String tag, String codigoMunicipio) throws Exception {
		//String tag = ""; ///=
		String tipoDocumento = ""; ///=
		String stringId = "id"; ///=


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); ///=
                factory.setNamespaceAware(false); ///=
                DocumentBuilder builder = factory.newDocumentBuilder(); ///=
		Document docs = builder.parse(new File(caminhoXml)); ///=

		// Busca nome do elemento raiz para verificar q tipo de documento a assinar
		tipoDocumento = docs.getDocumentElement().getNodeName(); ///=

		System.out.println("Assinar Betha 202:");

		System.out.println("tipo documento:" + tipoDocumento);
		System.out.println("caminhoXml:" + caminhoXml);
		System.out.println("caminhoCertificado:" + caminhoCertificado);
		System.out.println("senha:" + senha);
		System.out.println("caminhoXmlNovo:" + caminhoXmlNovo);
		System.out.println("tag:" + tag);
                
                if (tipoDocumento.equalsIgnoreCase("CancelarNfseEnvio")) {
                        if (tag.equalsIgnoreCase("LoteRps")){
                            System.out.println("Duplicado não assina fim" );
                            return;
                        }
                }

		System.out.println("Municipio em assinar:" + codigoMunicipio);

		if (  ( codigoMunicipio.equalsIgnoreCase("261160"))) {
			stringId = "Id";
		}

                if (  ( codigoMunicipio.equalsIgnoreCase("510790"))) {
			stringId = "Id";
		}
                if (  ( codigoMunicipio.equalsIgnoreCase("500370"))) {
			stringId = "Id";
		}
                
                //Cuiaba
                if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
			stringId = "Id";
                        
                        System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");
  //                      org.apache.xml.security.Init.init();
                        System.setProperty("com.sun.org.apache.xml.internal.security.ignoreLineBreaks", "true");
  //                         com.sun.org.apache.xml.internal.security.Init.init();
		}

                                
		System.out.println(" (teste 4 ) id a assinar:" + stringId);

                


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
                t.assinarBetha202(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "InfDeclaracaoPrestacaoServico", codigoMunicipio);
		t.assinarBetha202(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteRps", codigoMunicipio);
		 */
		if (tipoDocumento.equals("CancelarNfseEnvio")) {
                        if (tag.equalsIgnoreCase("LoteRps")){
                            return;
                        }
			if (tag.equalsIgnoreCase("InfRps")) {
				tag = "InfPedidoCancelamento";	
			}if (tag.equalsIgnoreCase("Pedido")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
                        tag = "tc:InfPedidoCancelamento";
		}
                if (tipoDocumento.equals("CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("InfRps")) {
				tag = "InfPedidoCancelamento";	
			}if (tag.equalsIgnoreCase("Pedido")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
                        if (codigoMunicipio.equalsIgnoreCase("510340")) {
                            	tag = "InfPedidoCancelamento";	
                        }
                        tag = "InfPedidoCancelamento";
		} 
                
                
		if (tipoDocumento.equals("p1:CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("tc:InfRps")) {
				if (codigoMunicipio.equalsIgnoreCase("510340")) {
					tag = "tc:InfPedidoCancelamento";
				}else if (codigoMunicipio.equalsIgnoreCase("500370")) {
					tag = "InfPedidoCancelamento";
				}else{
					tag = "InfPedidoCancelamento";	
				}
			}else {
				tag = "";
			}
		}
                
                if (tipoDocumento.equals("ConsultarNfseRpsEnvio")) {
			if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
				tag = "Pedido";	
			}else {
				tag = "";
			}
		} 
/*
                if (  ( codigoMunicipio.equalsIgnoreCase("510790"))) {
                    if (tipoDocumento.equals("EnviarLoteRpsEnvio")) {
                        tag = "InfDeclaracaoPrestacaoServico";
                    }
                    if (tipoDocumento.equals("EnviarLoteRpsSincronoEnvio")) {
                        tag = "InfDeclaracaoPrestacaoServico";
                    }
                }
                */

                
		System.out.print("tag: "+tag);

		//Create a DOM XMLSignatureFactory that will be used to
		//generate the enveloped signature.        
		String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);///=                
                XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());///=
                ////XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

		// Create a Reference to the enveloped document (in this case,
		// you are signing the whole document, so a URI of "" signifies
		// that, and also specify the SHA1 digest algorithm and
		// the ENVELOPED Transform.
		ArrayList transformList = new ArrayList(); ///=
		TransformParameterSpec tps = null; ///=
		Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps); ///=
		Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps); ///=
		transformList.add(envelopedTransform); ///=
		transformList.add(c14NTransform); ///=




		// Load the KeyStore and get the signing key and certificate.
		KeyStore ks = KeyStore.getInstance("PKCS12"); ///=
		ks.load(new FileInputStream(caminhoCertificado), senha.toCharArray()); ///=
		Enumeration aliasesEnum = ks.aliases(); ///=
		String alias = ""; ///=
		while (aliasesEnum.hasMoreElements()) { ///=
			alias = (String) aliasesEnum.nextElement(); ///=

			if (ks.isKeyEntry(alias)) { ///=
				break; ///=
			} ///=
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
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); ///=
                System.out.println("dbf.setNamespaceAware(true);");
		dbf.setNamespaceAware(true); ///=
                //dbf.setNamespaceAware(false);
		Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));



		//Lista cada tag a ser assinada
		for (int i = 0; i < doc.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {
                //for (int i = 0; i < docs.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {

			// Obtem elemento do documento a ser assinado, será criado uma
			// REFERENCE para o mesmo
                        NodeList elements = doc.getElementsByTagName(tag);
			//NodeList elements = docs.getElementsByTagName(tag);
			Element el = (Element) elements.item(i);
			String id = el.getAttribute(stringId);
                        el.setIdAttribute(stringId, true);
                        
                        Reference ref ;
                                
                        //Cuiaba
                        if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
                            ref = fac.newReference("" , fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
                        }else{
                            ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
                        }

			// Create the SignedInfo.
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
				CanonicalizationMethod.INCLUSIVE,
				(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				Collections.singletonList(ref));

			// Create a DOMSignContext and specify the RSA PrivateKey and
			// location of the resulting XMLSignature's parent element.
			DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());
                        //DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), docs.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());

			// Create the XMLSignature, but don't sign it yet.
			XMLSignature signature = fac.newXMLSignature(si, ki);

			// Marshal, generate, and sign the enveloped signature.
			signature.sign(dsc);
		}




		doc.setXmlStandalone(true);
                ///docs.setXmlStandalone(true);
		doc.getDocumentElement().removeAttribute("xmlns:ns2");
                ///docs.getDocumentElement().removeAttribute("xmlns:ns2");
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
                ///trans.transform(new DOMSource(docs), new StreamResult(os));

		//System.out.println("===\n"+os.toString()+"\n====");


		//novo
		File fout = new File(caminhoXmlNovo);
		FileOutputStream out = new FileOutputStream(fout);
		out.write(os.toByteArray());



		// Verifica as assinaturas
		// Find Signature element.
		NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
                //NodeList nl = docs.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		for (int i = 0; i < nl.getLength(); i++) {
			// Create a DOMValidateContext and specify a KeySelector and document context.
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(i));
                        
                        /*
                        if ( codigoMunicipio.equalsIgnoreCase("510340")) { // CUIABA

                            
                            if (tag.equalsIgnoreCase("tc:InfRps")){
                                
                                //NodeList nlb = doc.getElementsByTagNameNS("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd", "InfRps");
                                NodeList nlb = docs.getElementsByTagNameNS("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd", "InfRps");
                                //valContext.putNamespacePrefix("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd","tc");
                                Node body = nlb.item(0);
                                //valContext.setIdAttributeNS((Element)body, "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "id");
                                
                                valContext.setIdAttributeNS((Element)body, null, stringId);


                                
                            }
                        }*/
                        
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

        public void assinarSemID(String caminhoXml, String caminhoCertificado, String senha, String caminhoXmlNovo, String tag, String codigoMunicipio) throws Exception {
		//String tag = "";
		String tipoDocumento = "";
		String stringId = "id";


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		if (tag.equalsIgnoreCase("tc:InfRps")){
                    factory.setNamespaceAware(true);
                }else{
                    factory.setNamespaceAware(false);
                }
		
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

		if (  ( codigoMunicipio.equalsIgnoreCase("261160"))) {
			stringId = "Id";
		}

                if (  ( codigoMunicipio.equalsIgnoreCase("510790"))) {
			stringId = "Id";
		}
                
                //Cuiaba
                if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
			stringId = "Id";
                        
                        System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");
  //                      org.apache.xml.security.Init.init();
                        System.setProperty("com.sun.org.apache.xml.internal.security.ignoreLineBreaks", "true");
  //                         com.sun.org.apache.xml.internal.security.Init.init();
		}

                                
		//System.out.println(" (teste 4 ) id a assinar:" + stringId);
                System.out.println(" (teste 4 ) sem id a assinar");

                


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
			}if (tag.equalsIgnoreCase("Pedido")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
		}
                if (tipoDocumento.equals("CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("InfRps")) {
				tag = "InfPedidoCancelamento";	
			}if (tag.equalsIgnoreCase("Pedido")) {
				tag = "InfPedidoCancelamento";	
			}else {
				tag = "";
			}
                        if (codigoMunicipio.equalsIgnoreCase("510340")) {
                            	tag = "InfPedidoCancelamento";	
                        }
		} 
                
                
		if (tipoDocumento.equals("p1:CancelarNfseEnvio")) {
			if (tag.equalsIgnoreCase("tc:InfRps")) {
				if (codigoMunicipio.equalsIgnoreCase("510340")) {
					tag = "tc:InfPedidoCancelamento";
				}else if (codigoMunicipio.equalsIgnoreCase("500370")) {
					tag = "InfPedidoCancelamento";
				}else{
					tag = "InfPedidoCancelamento";	
				}
			}else {
				tag = "";
			}
		}
                
                if (tipoDocumento.equals("ConsultarNfseRpsEnvio")) {
			if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
				tag = "Pedido";	
			}else {
				tag = "";
			}
		} 
/*
                if (  ( codigoMunicipio.equalsIgnoreCase("510790"))) {
                    if (tipoDocumento.equals("EnviarLoteRpsEnvio")) {
                        tag = "InfDeclaracaoPrestacaoServico";
                    }
                    if (tipoDocumento.equals("EnviarLoteRpsSincronoEnvio")) {
                        tag = "InfDeclaracaoPrestacaoServico";
                    }
                }
                */
		System.out.print("tag: "+tag);

		//Create a DOM XMLSignatureFactory that will be used to
		//generate the enveloped signature.        
		String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);
		
                
                XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());
                ////XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

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
		
                if (tag.equalsIgnoreCase("tc:InfRps")){
                    dbf.setNamespaceAware(true);
                }else{
                    dbf.setNamespaceAware(false);
                }
		//dbf.setNamespaceAware(false);
		
                
                //Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));



		//Lista cada tag a ser assinada
		//for (int i = 0; i < doc.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {
                for (int i = 0; i < docs.getDocumentElement().getElementsByTagName(tag).getLength(); i++) {

			// Obtem elemento do documento a ser assinado, será criado uma
			// REFERENCE para o mesmo
			NodeList elements = docs.getElementsByTagName(tag);
			Element el = (Element) elements.item(i);
			//String id = el.getAttribute(stringId);
                        //el.setIdAttribute(stringId, true);
                        
                        Reference ref ;
                                
                        //Cuiaba
                        //if (  ( codigoMunicipio.equalsIgnoreCase("510340"))) {
                            ref = fac.newReference("" , fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
                        //}else{
//                            ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
  //                      }

			// Create the SignedInfo.
			SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
				CanonicalizationMethod.INCLUSIVE,
				(C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
				Collections.singletonList(ref));

			// Create a DOMSignContext and specify the RSA PrivateKey and
			// location of the resulting XMLSignature's parent element.
			//DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());
                        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), docs.getDocumentElement().getElementsByTagName(tag).item(i).getParentNode());

			// Create the XMLSignature, but don't sign it yet.
			XMLSignature signature = fac.newXMLSignature(si, ki);

			// Marshal, generate, and sign the enveloped signature.
			signature.sign(dsc);
		}




		//doc.setXmlStandalone(true);
                docs.setXmlStandalone(true);
		//doc.getDocumentElement().removeAttribute("xmlns:ns2");
                docs.getDocumentElement().removeAttribute("xmlns:ns2");
//        if (tag.equalsIgnoreCase("infNFe")) {
//            ((Element) doc.getDocumentElement().getElementsByTagName("NFe").item(0)).setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
//            ((Element) doc.getDocumentElement().getElementsByTagName("infNFe").item(0)).setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//        }


		// Output the resulting document.
		//OutputStream os = new FileOutputStream(caminhoXmlNovo);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		//trans.transform(new DOMSource(doc), new StreamResult(os));
                trans.transform(new DOMSource(docs), new StreamResult(os));

		//System.out.println("===\n"+os.toString()+"\n====");


		//novo
		File fout = new File(caminhoXmlNovo);
		FileOutputStream out = new FileOutputStream(fout);
		out.write(os.toByteArray());



		// Verifica as assinaturas
		// Find Signature element.
		//NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
                NodeList nl = docs.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		for (int i = 0; i < nl.getLength(); i++) {
			// Create a DOMValidateContext and specify a KeySelector and document context.
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(i));
                        
                        /*
                        if ( codigoMunicipio.equalsIgnoreCase("510340")) { // CUIABA

                            
                            if (tag.equalsIgnoreCase("tc:InfRps")){
                                
                                //NodeList nlb = doc.getElementsByTagNameNS("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd", "InfRps");
                                NodeList nlb = docs.getElementsByTagNameNS("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd", "InfRps");
                                //valContext.putNamespacePrefix("http://www.issnetonline.com.br/webserviceabrasf/vsd/tipos_complexos.xsd","tc");
                                Node body = nlb.item(0);
                                //valContext.setIdAttributeNS((Element)body, "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "id");
                                
                                valContext.setIdAttributeNS((Element)body, null, stringId);


                                
                            }
                        }*/
                        
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

	public static void main(String[] args) throws Exception {
            
            
            
            	String tipoDocumento = "";






		if (args.length < 3) {
			System.out.println("Argumentos:\n\n comando <arquivoXmlOrigem> <arquivoCertificado> <senha> [arquivoXmlDestino]");
			return;
		}
		String caminhoXml = args[0];
		String caminhoCertificado = args[1];
		String senha = args[2];
		String arquivoXmlNovo = args[3];
		String codigoMunicipio = args[4];
                
                
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document docs = builder.parse(new File(caminhoXml));
                // Busca nome do elemento raiz para verificar q tipo de documento a assinar
		tipoDocumento = docs.getDocumentElement().getNodeName();

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
			if (codigoMunicipio.equalsIgnoreCase("510340")) {
                            if (tipoDocumento.equals("ConsultarNfseRpsEnvio")){
                                t.assinarSemID(caminhoXml, caminhoCertificado, senha, caminhoXml, "InfDeclaracaoPrestacaoServico", codigoMunicipio);
                            }else if (tipoDocumento.equals("CancelarNfseEnvio")) {
                                t.assinar(caminhoXml, caminhoCertificado, senha, arquivoXmlNovo, "InfDeclaracaoPrestacaoServico", codigoMunicipio);
                            }else{
                                t.assinar(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "InfDeclaracaoPrestacaoServico", codigoMunicipio);
				t.assinar(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteRps", codigoMunicipio);
                            }

			} else if (codigoMunicipio.equalsIgnoreCase("500370")) {
                            
                                if (tipoDocumento.equalsIgnoreCase("CancelarNfseEnvio")) {
                                    t.assinarBetha202(caminhoXml, caminhoCertificado, senha, arquivoXmlNovo, "InfDeclaracaoPrestacaoServico", codigoMunicipio);
                                }else{
                                    t.assinarBetha202(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "InfDeclaracaoPrestacaoServico", codigoMunicipio);
                                    t.assinarBetha202(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteRps", codigoMunicipio);
                                }

			} else if (codigoMunicipio.equalsIgnoreCase("510790")) { //SINOP
				t.assinar(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "InfDeclaracaoPrestacaoServico", codigoMunicipio);
				t.assinar(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteRps", codigoMunicipio);

			} else {
				t.assinar(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "InfRps", codigoMunicipio);
				t.assinar(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteRps", codigoMunicipio);

			}
*/

                        
                        //t.assinar(caminhoXml, caminhoCertificado, senha, caminhoXml + "-rpsAssinado", "infDPS", codigoMunicipio);
                        //t.assinar(caminhoXml + "-rpsAssinado", caminhoCertificado, senha, arquivoXmlNovo, "LoteDps", codigoMunicipio);
                        t.assinar(caminhoXml, caminhoCertificado, senha, arquivoXmlNovo, "infDPS", codigoMunicipio);


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
