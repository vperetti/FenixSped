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

import br.com.basepro.fenixsped.ws.nfeconsulta.NfeConsulta_PortType;
import br.com.basepro.fenixsped.ws.nfeconsulta.NfeConsulta_Service;
import br.com.basepro.fenixsped.ws.nfeconsulta.NfeConsulta_ServiceLocator;
import java.net.URL;

/**
 *
 * @author vinicius
 */
public class NfeConsulta extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        recebeParametros(args);
        if (getCodUf().equalsIgnoreCase("51")) {

            // Instancia objetos especificos
            NfeConsulta_Service service = new NfeConsulta_ServiceLocator();
            NfeConsulta_PortType servico = service.getNfeConsulta(new URL(getCaminhoWebService()));

            // Executa buscando retorno
            setRetorno(servico.nfeConsultaNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("43")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 43 = RS");
            }
            br.com.basepro.fenixsped.ws.rs.NfeConsulta service = new br.com.basepro.fenixsped.ws.rs.NfeConsultaLocator();
            br.com.basepro.fenixsped.ws.rs.NfeConsultaSoap_PortType servico = service.getNfeConsultaSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeConsultaNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("29")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 29 = BA");
            }
            br.com.basepro.fenixsped.ws.ba.NfeConsulta service = new br.com.basepro.fenixsped.ws.ba.NfeConsultaLocator();
            br.com.basepro.fenixsped.ws.ba.NfeConsultaSoap_PortType servico = service.getNfeConsultaSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeConsultaNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("41")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 41 = PR");
            }
            br.com.basepro.fenixsped.ws.pr.NfeConsultaService service = new br.com.basepro.fenixsped.ws.pr.NfeConsultaServiceLocator();
            br.com.basepro.fenixsped.ws.pr.NfeConsulta servico = service.getnfeConsultaNF(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeConsultaNF(getCabecalhoMensagem(), getConteudoXML()));
        }else{

            // Instancia objetos especificos
            NfeConsulta_Service service = new NfeConsulta_ServiceLocator();
            NfeConsulta_PortType servico = service.getNfeConsulta(new URL(getCaminhoWebService()));

            // Executa buscando retorno
            setRetorno(servico.nfeConsultaNF(getCabecalhoMensagem(), getConteudoXML()));
        }
        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
