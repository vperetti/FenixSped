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

import br.com.basepro.fenixsped.ws.nferetrecepcao.NfeRetRecepcao_PortType;
import br.com.basepro.fenixsped.ws.nferetrecepcao.NfeRetRecepcao_Service;
import br.com.basepro.fenixsped.ws.nferetrecepcao.NfeRetRecepcao_ServiceLocator;
import java.net.URL;

/**
 *
 * @author vinicius
 */
public class NfeRetRecepcao extends ClienteServico {
	public static void main(String[] args) throws Exception {
                
        //Recebe os Parametros
        recebeParametros(args);

        
        
         if (getCodUf().equalsIgnoreCase("51")){
            // Instancia objetos especificos
            NfeRetRecepcao_Service service = new NfeRetRecepcao_ServiceLocator();
    		NfeRetRecepcao_PortType servico = service.getNfeRetRecepcao(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.nfeRetRecepcao(getCabecalhoMensagem(),getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("43")){
            if (isDebugando()) System.out.println("Comunicando com 43 = RS");
            br.com.basepro.fenixsped.ws.rs.NfeRetRecepcao service = new br.com.basepro.fenixsped.ws.rs.NfeRetRecepcaoLocator();
            br.com.basepro.fenixsped.ws.rs.NfeRetRecepcaoSoap_PortType servico = service.getNfeRetRecepcaoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeRetRecepcao(getCabecalhoMensagem(),getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("29")){
            if (isDebugando()) System.out.println("Comunicando com 29 = BA");
            br.com.basepro.fenixsped.ws.ba.NfeRetRecepcao service = new br.com.basepro.fenixsped.ws.ba.NfeRetRecepcaoLocator();
            br.com.basepro.fenixsped.ws.ba.NfeRetRecepcaoSoap_PortType servico = service.getNfeRetRecepcaoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeRetRecepcao(getCabecalhoMensagem(),getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("41")){
            if (isDebugando()) System.out.println("Comunicando com 41 = PR");
            br.com.basepro.fenixsped.ws.pr.NfeRetRecepcaoService service = new br.com.basepro.fenixsped.ws.pr.NfeRetRecepcaoServiceLocator();
            br.com.basepro.fenixsped.ws.pr.NfeRetRecepcao servico = service.getnfeRetRecepcao(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeRetRecepcao(getCabecalhoMensagem(),getConteudoXML()));
        }else{
            // Instancia objetos especificos
            NfeRetRecepcao_Service service = new NfeRetRecepcao_ServiceLocator();
    		NfeRetRecepcao_PortType servico = service.getNfeRetRecepcao(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.nfeRetRecepcao(getCabecalhoMensagem(),getConteudoXML()));
        }
        
        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
	}
}
