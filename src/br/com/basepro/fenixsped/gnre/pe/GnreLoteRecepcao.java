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

package br.com.basepro.fenixsped.gnre.pe;

import br.com.basepro.fenixsped.ws.ClienteServico;
import java.io.StringReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;


/**
 *
 * @author vinicius
 */
public class GnreLoteRecepcao extends ClienteServico {
	public static void main(String[] args) throws Exception {
        
        
        //Recebe os Parametros
        recebeParametros(args);
        
        

            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
            XMLStreamReader dad = xmlInputFactory.createXMLStreamReader(new StringReader("<nfeDadosMsg>" + getConteudoXML().substring(38) + "</nfeDadosMsg>"));
            br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub.GnreDadosMsg dadosMsg = br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub.GnreDadosMsg.Factory.parse(dad);
            br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub.GnreCabecMsg cabecMsg = new br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub.GnreCabecMsg();
            cabecMsg.setVersaoDados("1.00");
            //br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub.NfeCabecMsgE cabecMsgE = new br.com.basepro.fenixsped.ws.mt2.NfeRecepcao2Stub.NfeCabecMsgE();
            //cabecMsgE.setNfeCabecMsg(cabecMsg);
            br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub stub = new br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub(getCaminhoWebService());
            setRetorno(stub.processar(dadosMsg, cabecMsg).getExtraElement().toString());

	    
	    /*
	    setCertificaServidor(true);
            br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub servico = new br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub(getCaminhoWebService());
            br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcaoStub.ConsultarNfseRpsEnvio envio = new br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.ConsultarNfseRpsEnvio();
            envio.setMensagemXML(getConteudoXML());                   
            br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.ConsultarNfseRpsResposta resposta = servico.consultarNfsePorRps(envio); 
            setRetorno(resposta.getConsultarNfseRpsResposta());
	    */


        // Salva o retorno
        salvaRetorno(getCaminhoXMLRetorno());
	}
}
