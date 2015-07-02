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

package br.com.basepro.fenixsped.util;

import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import javax.xml.validation.Validator;
import java.io.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;


/**
 *
 * @author Rodrigo Padilha
 */
public class XsdSchemaValidator {
	
	


  private static int errorCount = 0;
  
  public static void main(String[] a) {
     
      String schemaName=a[1];//"/home/rodrigo/basepro/PL_006g/enviNFe_v2.00.xsd";  
      String xmlName=a[0];//"/home/rodrigo/basepro/2611070396766600010155001000000469195113983-env-nfe.xml";  
      validateXml(schemaName, xmlName);
    
  }
  public static void validateXml(String schemaName, String xmlName) {
      Schema schema = loadSchema(schemaName);
      validateXml(schema, xmlName);
  }
  public static void validateXml(Schema schema, String xmlName) {
    try {
      // criando instância para validadorcreating a Validator instance
      Validator validator = schema.newValidator();

      // definindo tratador de erro
      validator.setErrorHandler(new MyErrorHandler());

      // preparando arquivo XML 
      SAXSource source = new SAXSource(
        new InputSource(new java.io.FileInputStream(xmlName)));

      // validando a fonte Sax de acordo com o schema
      validator.validate(source);
      System.out.println();
      if (errorCount>0) {
        System.out.println("Documento com erros: "+errorCount);
	System.exit(1);
      } else {
        System.out.println("ok");
	System.exit(0);
      } 

    } catch (Exception e) {
      // captura exceções de validação
      System.out.println();
      System.out.println(e.toString());
    }
  }
  public static Schema loadSchema(String name) {
    Schema schema = null;
    try {
      String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
      SchemaFactory factory = SchemaFactory.newInstance(language);
      schema = factory.newSchema(new File(name));
      
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return schema;
  }
  
  private static class MyErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
       System.out.println("Warning: "); 
       printException(e);
    }
    public void error(SAXParseException e) throws SAXException {
       System.out.println("Erro: "); 
       printException(e);
    }
    public void fatalError(SAXParseException e) throws SAXException {
       System.out.println("Fatal error: "); 
       printException(e);
    }
    private void printException(SAXParseException e) {
      errorCount++;
      //imprime o erro, linha e coluna
      System.out.println("   Linha: "+e.getLineNumber());
      System.out.println("   Coluna: "+e.getColumnNumber());
      System.out.println("   Mensagem: "+e.getMessage());
      System.out.println();
    }
  }

	
	
	
}
