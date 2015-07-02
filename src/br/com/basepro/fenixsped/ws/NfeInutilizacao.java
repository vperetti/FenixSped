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

import br.com.basepro.fenixsped.ws.nfeinutilizacao.NfeInutilizacao_PortType;
import br.com.basepro.fenixsped.ws.nfeinutilizacao.NfeInutilizacao_Service;
import br.com.basepro.fenixsped.ws.nfeinutilizacao.NfeInutilizacao_ServiceLocator;
import java.net.URL;

/**
 *
 * @author vinicius
 */
public class NfeInutilizacao extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        recebeParametros(args);
        if (getCodUf().equalsIgnoreCase("51")) {

            // Instancia objetos especificos
            NfeInutilizacao_Service service = new NfeInutilizacao_ServiceLocator();
            NfeInutilizacao_PortType servico = service.getNfeInutilizacao(new URL(getCaminhoWebService()));

            // Executa buscando retorno
            setRetorno(servico.nfeInutilizacaoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("43")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 43 = RS");
            }
            br.com.basepro.fenixsped.ws.rs.NfeInutilizacao service = new br.com.basepro.fenixsped.ws.rs.NfeInutilizacaoLocator();
            br.com.basepro.fenixsped.ws.rs.NfeInutilizacaoSoap_PortType servico = service.getNfeInutilizacaoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeInutilizacaoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("29")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 29 = BA");
            }
            br.com.basepro.fenixsped.ws.rs.NfeInutilizacao service = new br.com.basepro.fenixsped.ws.rs.NfeInutilizacaoLocator();
            br.com.basepro.fenixsped.ws.rs.NfeInutilizacaoSoap_PortType servico = service.getNfeInutilizacaoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeInutilizacaoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("41")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 41 = PR");
            }
            br.com.basepro.fenixsped.ws.pr.NfeInutilizacaoService service = new br.com.basepro.fenixsped.ws.pr.NfeInutilizacaoServiceLocator();
            br.com.basepro.fenixsped.ws.pr.NfeInutilizacao servico = service.getnfeInutilizacaoNF(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeInutilizacaoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else{

            // Instancia objetos especificos
            NfeInutilizacao_Service service = new NfeInutilizacao_ServiceLocator();
            NfeInutilizacao_PortType servico = service.getNfeInutilizacao(new URL(getCaminhoWebService()));

            // Executa buscando retorno
            setRetorno(servico.nfeInutilizacaoNF(getCabecalhoMensagem(), getConteudoXML()));
        }

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
