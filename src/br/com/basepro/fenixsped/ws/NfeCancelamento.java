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

import br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamento_PortType;
import br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamento_Service;
import br.com.basepro.fenixsped.ws.nfecancelamento.NfeCancelamento_ServiceLocator;
import java.net.URL;

/**
 *
 * @author vinicius
 */
public class NfeCancelamento extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        recebeParametros(args);
        if (getCodUf().equalsIgnoreCase("51")) {

            // Instancia objetos especificos
            NfeCancelamento_Service service = new NfeCancelamento_ServiceLocator();
            NfeCancelamento_PortType servico = service.getNfeCancelamento(new URL(getCaminhoWebService()));

            // Executa buscando retorno
            setRetorno(servico.nfeCancelamentoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("43")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 43 = RS");
            }
            br.com.basepro.fenixsped.ws.rs.NfeCancelamento service = new br.com.basepro.fenixsped.ws.rs.NfeCancelamentoLocator();
            br.com.basepro.fenixsped.ws.rs.NfeCancelamentoSoap_PortType servico = service.getNfeCancelamentoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeCancelamentoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("29")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 29 = BA");
            }
            br.com.basepro.fenixsped.ws.ba.NfeCancelamento service = new br.com.basepro.fenixsped.ws.ba.NfeCancelamentoLocator();
            br.com.basepro.fenixsped.ws.ba.NfeCancelamentoSoap_PortType servico = service.getNfeCancelamentoSoap(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeCancelamentoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else if (getCodUf().equalsIgnoreCase("41")) {
            if (isDebugando()) {
                System.out.println("Comunicando com 41 = RS");
            }
            br.com.basepro.fenixsped.ws.pr.NfeCancelamentoService service = new br.com.basepro.fenixsped.ws.pr.NfeCancelamentoServiceLocator();
            br.com.basepro.fenixsped.ws.pr.NfeCancelamento servico = service.getnfeCancelamentoNF(new URL(getCaminhoWebService()));
            setRetorno(servico.nfeCancelamentoNF(getCabecalhoMensagem(), getConteudoXML()));
        }else {

            // Instancia objetos especificos
            NfeCancelamento_Service service = new NfeCancelamento_ServiceLocator();
            NfeCancelamento_PortType servico = service.getNfeCancelamento(new URL(getCaminhoWebService()));

            // Executa buscando retorno
            setRetorno(servico.nfeCancelamentoNF(getCabecalhoMensagem(), getConteudoXML()));
        }

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}
