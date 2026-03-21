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

import br.com.basepro.fenixsped.util.SOAPUtilities;
import java.io.StringReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;


/**
 *
 * @author vinicius
 */
public class NfeInutilizacao4 extends ClienteServico {

    public static void main(String[] args) throws Exception {

	    
        //Recebe os Parametros
        recebeParametros(args);
        if (getCodUf().equalsIgnoreCase("50")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 50 = MS t4.0");
            }
            
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub stub = new br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF(dadosMsg).getExtraElement().toString());
            
        }else{

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub.NfeDadosMsg dadosMsg = br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub.NfeDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub stub = new br.com.basepro.fenixsped.ws.nfeinutilizacao4.NFeInutilizacao4Stub(getCaminhoWebService());
            setRetorno(stub.nfeInutilizacaoNF(dadosMsg).getExtraElement().toString());
                        
        }

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
