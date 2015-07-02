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
package br.com.basepro.fenixsped.abrasf;

import br.com.basepro.fenixsped.ws.ClienteServico;
import java.net.URL;

/**
 *
 * @author vinicius
 */
public class NfeCancelamento extends ClienteServico {

    public static void main(String[] args) throws Exception {

        //Recebe os Parametros
        String codigoMunicipio = args[5];
        setPadrao("abrasf");
        recebeParametros(args);


        //SE FOR SALVADOR-BA
        if (codigoMunicipio.equalsIgnoreCase("292740")) {
            br.com.basepro.fenixsped.abrasf.salvadorba.ConsultaSituacaoNfse service = new br.com.basepro.fenixsped.abrasf.salvadorba.ConsultaSituacaoNfseLocator();
            br.com.basepro.fenixsped.abrasf.salvadorba.IConsultaSituacaoNfse servico = service.getBasicHttpBinding_IConsultaSituacaoNfse(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.consultarSituacaoNfse(getConteudoXML()));

            //SE FOR RECIFE-PE
        } else if (codigoMunicipio.equalsIgnoreCase("261160")) {
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.recifepe.NfseStub servico = new br.com.basepro.fenixsped.abrasf.recifepe.NfseStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.CancelarNfseEnvio envio = new br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.CancelarNfseEnvio();
            envio.setMensagemXML(getConteudoXML());
            br.com.basepro.fenixsped.abrasf.recifepe.NfseStub.CancelarNfseResposta resposta = servico.cancelarNfse(envio);
            setRetorno(resposta.getCancelarNfseResposta());

            //SE FOR CUIABA
        } else if (codigoMunicipio.equalsIgnoreCase("510340")) {
            setCertificaServidor(true);
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub servico = new br.com.basepro.fenixsped.abrasf.issnet.ServicosStub(getCaminhoWebService());
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.CancelarNfse envio = new br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.CancelarNfse();
            envio.setXml(getConteudoXML());
            br.com.basepro.fenixsped.abrasf.issnet.ServicosStub.CancelarNfseResponse resposta = servico.cancelarNfse(envio);
            setRetorno(resposta.getCancelarNfseResult());

	    
        } else {
            //PARA MANTER COMPATIBILIDADE FOI MANTIDO PADRÃO SALVADOR
            br.com.basepro.fenixsped.abrasf.salvadorba.ConsultaSituacaoNfse service = new br.com.basepro.fenixsped.abrasf.salvadorba.ConsultaSituacaoNfseLocator();
            br.com.basepro.fenixsped.abrasf.salvadorba.IConsultaSituacaoNfse servico = service.getBasicHttpBinding_IConsultaSituacaoNfse(new URL(getCaminhoWebService()));
            // Executa buscando retorno
            setRetorno(servico.consultarSituacaoNfse(getConteudoXML()));

        }
        // Salva o retorno

        salvaRetorno(getCaminhoXMLRetorno());

    }
}
