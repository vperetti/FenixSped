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
package br.com.basepro.fenixsped.assinaturaXml;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class GeralXML {
  public static final String PREFIXNODE = "inf";
  public static final String TIPODOC_NFE = "NFe";
  public static final String TIPODOC_CTE = "Cte";
  
  public static Document createDocument() throws ParserConfigurationException {
    //return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
    factory.setNamespaceAware(true);   
    DocumentBuilder builder = factory.newDocumentBuilder();   
    Document doc = builder.newDocument();      
    return doc;
  }
  public static Document createDocumentFromXML(String nomeArqXML) throws ParserConfigurationException, IOException, SAXException {
    //return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(nomeArqXML));
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
    factory.setNamespaceAware(true);   
    DocumentBuilder builder = factory.newDocumentBuilder();   
    Document doc = builder.parse(new File(nomeArqXML));      
    return doc;
  }
  
  public static Element createElement(Document doc,
    String xmlnsElement, String fieldElement, String valueElement) {
    Element elem = doc.createElementNS(xmlnsElement,fieldElement);
    elem.setTextContent(valueElement);
    return elem;
  }
  
  public static Element createElement(Document doc,
    String xmlnsElement, String fieldElement, 
    String attribElement, String valueAttribElement) {
    Element elem = doc.createElementNS(xmlnsElement,fieldElement);
    elem.setAttribute(attribElement, valueAttribElement);
    return elem;
  }

  /*
  static class ErrorHandlerXSD implements ErrorHandler {
      public void warning(SAXParseException anException) throws SAXException {
          System.out.println("[warning] " + anException);
      }
      public void error(SAXParseException anException) throws SAXException {
          System.out.println("[error] " + anException);
      }
      public void fatalError(SAXParseException anException) throws SAXException {       
          System.out.println("[fatal error] " + anException);
      }
  } 
   */ 

  public static boolean validarXMLporXSD(String nomeArqXML, String nomeArqXSD,
    String NomeArqException, boolean DebugException) {
    try {
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      //schemaFactory.setErrorHandler(new ErrorHandlerXSD());
      //
      Schema schemaXSD = schemaFactory.newSchema(new File(nomeArqXSD));
      Validator validator = schemaXSD.newValidator();
      //
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true); // Must enable namespace processing!!
      DocumentBuilder parser = dbf.newDocumentBuilder();
      Document document = parser.parse(new File(nomeArqXML));
      //
      //validator.setErrorHandler(new ErrorHandlerXSD());      
      validator.validate(new DOMSource(document));
      //
      return true;
    } catch (ParserConfigurationException e) {
      // exception handling
      //Erro.GerarException(e, NomeArqException, DebugException, "validarXMLporXSD");
      return false;
    } catch (SAXException e) {
      // exception handling - document not valid!
      //Erro.GerarException(e, NomeArqException, DebugException, "validarXMLporXSD");
      return false;
    } catch (IOException e) {
      // exception handling
      //Erro.GerarException(e, NomeArqException, DebugException, "validarXMLporXSD");
      return false;
    }    
  }
  
  public static void setKeyStore(
    String nomeArqCertifCliente, String senhaCertifCliente,
    String nomeArqCertifServidor, String senhaCertifServidor
    ) {
    setKeyStore(
      nomeArqCertifCliente,senhaCertifCliente,
      nomeArqCertifServidor,senhaCertifServidor,
      false);
  }
  public static void setKeyStore(
    String nomeArqCertifCliente, String senhaCertifCliente,
    String nomeArqCertifServidor, String senhaCertifServidor,
    boolean debug) {
    if (debug)
      System.setProperty("javax.net.debug", "ssl");
    //
    System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    // Keystore com o certificado do cliente
    System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
    System.setProperty("javax.net.ssl.keyStore", nomeArqCertifCliente);
    System.setProperty("javax.net.ssl.keyStorePassword", senhaCertifCliente);
    //
    System.setProperty("javax.net.ssl.trustStoreType", "JKS");
    System.setProperty("javax.net.ssl.trustStore", nomeArqCertifServidor);
    System.setProperty("javax.net.ssl.trustStorePassword", senhaCertifServidor);
  }
  
  public static boolean assinarXML(
    String nomeArqXML, String nomeArqAssXML, String tipoDoc,
    String nomeArqCertifCliente, String senhaCertifCliente) {
    return assinarXML(
      nomeArqXML, nomeArqAssXML, tipoDoc,
      nomeArqCertifCliente, senhaCertifCliente, 
      false);
  }
  public static boolean assinarXML(
    String nomeArqXML, String nomeArqAssXML, String tipoDoc,
    String nomeArqCertifCliente, String senhaCertifCliente,
    boolean debug) {
    //
    try {
      Document doc = GeralXML.createDocumentFromXML(nomeArqXML);

      // Create a DOM XMLSignatureFactory that will be used to
      // generate the enveloped signature.
      XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

      // Create a Reference to the enveloped document (in this case,
      // you are signing the whole document, so a URI of "" signifies
      // that, and also specify the SHA1 digest algorithm and
      // the ENVELOPED Transform.
      ArrayList transformList = new ArrayList();
      TransformParameterSpec tps = null;
      Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
      Transform c14NTransform = fac.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);
      transformList.add(envelopedTransform);
      transformList.add(c14NTransform);

      // Load the KeyStore and get the signing key and certificate.
      char[] pin = senhaCertifCliente.toCharArray();
      KeyStore ks = KeyStore.getInstance("PKCS12");
      FileInputStream fis = new FileInputStream(new File(nomeArqCertifCliente));
      ks.load(fis, pin);
      KeyStore.PrivateKeyEntry pkEntry = null;
      Enumeration aliasesEnum = ks.aliases();
      PrivateKey privateKey = null;
      while (aliasesEnum.hasMoreElements()) {
        String alias = (String) aliasesEnum.nextElement();
        if (debug)
          System.out.println(alias);
        if (ks.isKeyEntry(alias)) {
          pkEntry = (KeyStore.PrivateKeyEntry) 
            ks.getEntry(alias, new KeyStore.PasswordProtection(pin));
          privateKey = pkEntry.getPrivateKey();
          break;
        }
      }
      X509Certificate cert = (X509Certificate) pkEntry.getCertificate();

      // Create the KeyInfo containing the X509Data.
      KeyInfoFactory kif = fac.getKeyInfoFactory();
      List x509Content = new ArrayList();
      x509Content.add(cert);
      X509Data xd = kif.newX509Data(x509Content);
      KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

      for (int i = 0; i < doc.getDocumentElement().getElementsByTagName(PREFIXNODE+tipoDoc).getLength(); i++) {
        assinarTipoDoc(fac, transformList, privateKey, ki, doc, PREFIXNODE+tipoDoc, i);
      }
      
      // Output the resulting document.
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer trans = tf.newTransformer();
      trans.transform(new DOMSource(doc), new StreamResult(os));

      File fout = new File(nomeArqAssXML);
      FileOutputStream out = new FileOutputStream(fout);
      out.write(os.toByteArray());
      if (debug)
        System.out.println(os.toString());

      return true;
    } catch (Exception e) {
      // exception handling
      e.printStackTrace();
      return false;
    }
  }
  
  private static void assinarTipoDoc(XMLSignatureFactory fac,
    ArrayList transformList, PrivateKey privateKey, KeyInfo ki,
    Document doc, String tipoDoc, int i) throws Exception {

    // Obtem elemento do documento a ser assinado, será criado uma
    // REFERENCE para o mesmo
    NodeList elements = doc.getElementsByTagName(tipoDoc);
    Element el = (Element) elements.item(i);
    String id = el.getAttribute("Id");

    // Create a DOM XMLSignatureFactory that will be used to
    // generate the enveloped signature.
    Reference ref = fac.newReference("#" + id, 
      fac.newDigestMethod(DigestMethod.SHA1, null), 
      transformList, null, null);

    // Create the SignedInfo.
    SignedInfo si = fac.newSignedInfo(
      fac.newCanonicalizationMethod(
        CanonicalizationMethod.INCLUSIVE,
        (C14NMethodParameterSpec) null), 
      fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
      Collections.singletonList(ref));

    // Create the XMLSignature, but don't sign it yet.
    XMLSignature signature = fac.newXMLSignature(si, ki);

    // Marshal, generate, and sign the enveloped signature.
    // Create a DOMSignContext and specify the RSA PrivateKey and
    // location of the resulting XMLSignature's parent element.
    ////DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
    DOMSignContext dsc = new DOMSignContext(privateKey, el.getParentNode());
    signature.sign(dsc);
  }

}