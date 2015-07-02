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
package br.com.basepro.fenixsped.ndsf;

import br.com.basepro.fenixsped.ws.ClienteServico;


/**
 *
 * @author vinicius
 */
public class NfeStatusServico extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        setCertificaServidor(false);
        setPadrao("ndsf");
        recebeParametros(args);
        setRetorno("Nao implementado");

        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
    }
}