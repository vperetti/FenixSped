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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Inputs and Outputs xml files
 * @author Vinicius Peretti
 * 
 * Based on Leitor.java from Teacher Carlos
 */
public class IOXml {
    
    /**
     * Gets the root element from a  xml file
     * 
     * @param filePath the path to xml file
     * @return the root element
     * @throws java.io.FileNotFoundException
     */
    public static Document getDoc(String filePath) throws FileNotFoundException {
        try {
            SAXBuilder parser = new SAXBuilder();
            Document doc = parser.build(filePath);
            return doc;
        } catch (FileNotFoundException e){
                throw e;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    /**
     * Save the contento doc in filePath
     * @param doc xml document
     * @param filePath the path to xml file
     */
    public static void saveXML(Document doc, String filePath) {
        try {
            File file = new File(filePath);
            Format format = Format.getPrettyFormat();
            format.setEncoding("iso-8859-1");
            XMLOutputter outputter = new XMLOutputter(format);
            FileWriter writer = new FileWriter(file);
            outputter.output(doc,writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
