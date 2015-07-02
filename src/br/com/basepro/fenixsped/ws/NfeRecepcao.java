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

import br.com.basepro.fenixsped.assinaturaXml.AssinaDocumentos;
import br.com.basepro.fenixsped.ws.nferecepcao.NfeRecepcao_PortType;
import br.com.basepro.fenixsped.ws.nferecepcao.NfeRecepcao_Service;
import br.com.basepro.fenixsped.ws.nferecepcao.NfeRecepcao_ServiceLocator;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author vinicius
 */
public class NfeRecepcao extends ClienteServico {
	public static void main(String[] args) throws Exception {
        
        Document doc;
        AssinaDocumentos assinador = new AssinaDocumentos();
        
        //Recebe os Parametros
        recebeParametros(args);
        
        
        if (getCodUf().equalsIgnoreCase("51")){
            // Instancia objetos especificos
            NfeRecepcao_Service service = new NfeRecepcao_ServiceLocator();
    		NfeRecepcao_PortType servico = service.getNfeRecepcao(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.nfeRecepcaoLote(getCabecalhoMensagem(),getConteudoXML()));
        } else if (getCodUf().equalsIgnoreCase("43")){
            if (isDebugando()) System.out.println("Comunicando com 43 = RS");
            br.com.basepro.fenixsped.ws.rs.NfeRecepcao service = new br.com.basepro.fenixsped.ws.rs.NfeRecepcaoLocator();
            br.com.basepro.fenixsped.ws.rs.NfeRecepcaoSoap_PortType servico = service.getNfeRecepcaoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeRecepcaoLote(getCabecalhoMensagem(),getConteudoXML()));
        } else if (getCodUf().equalsIgnoreCase("29")){
            if (isDebugando()) System.out.println("Comunicando com 29 = BA");
            br.com.basepro.fenixsped.ws.ba.NfeRecepcao service = new br.com.basepro.fenixsped.ws.ba.NfeRecepcaoLocator();
            br.com.basepro.fenixsped.ws.ba.NfeRecepcaoSoap_PortType servico = service.getNfeRecepcaoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeRecepcaoLote(getCabecalhoMensagem(),getConteudoXML()));
        } else if (getCodUf().equalsIgnoreCase("41")){
            if (isDebugando()) System.out.println("Comunicando com 41 = PR");
            br.com.basepro.fenixsped.ws.pr.NfeRecepcao service = new br.com.basepro.fenixsped.ws.pr.NfeRecepcaoLocator();
            br.com.basepro.fenixsped.ws.pr.NfeRecepcaoSoap_PortType servico = service.getNfeRecepcaoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeRecepcaoLote(getCabecalhoMensagem(),getConteudoXML()));
        }else{
            // Instancia objetos especificos
            NfeRecepcao_Service service = new NfeRecepcao_ServiceLocator();
    		NfeRecepcao_PortType servico = service.getNfeRecepcao(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.nfeRecepcaoLote(getCabecalhoMensagem(),getConteudoXML()));
        }
        
        
        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
	}
}
