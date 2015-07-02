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

import br.com.basepro.fenixsped.ws.cadconsultacadastro.CadConsultaCadastro_PortType;
import br.com.basepro.fenixsped.ws.cadconsultacadastro.CadConsultaCadastro_Service;
import br.com.basepro.fenixsped.ws.cadconsultacadastro.CadConsultaCadastro_ServiceLocator;
import java.net.URL;

/**
 *
 * @author vinicius
 */
public class CadConsultaCadastro extends ClienteServico {
	public static void main(String[] args) throws Exception {
                
        //Recebe os Parametros
        recebeParametros(args);
        
        // Instancia objetos especificos
        CadConsultaCadastro_Service service = new CadConsultaCadastro_ServiceLocator();
		CadConsultaCadastro_PortType servico = service.getCadConsultaCadastro(new URL(getCaminhoWebService()));

        // Executa buscando retorno
        setRetorno(servico.consultaCadastro(getCabecalhoMensagem(),getConteudoXML()));
        
        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
	}
}
