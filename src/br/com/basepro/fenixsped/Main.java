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
 * Utilitario de para assinatura de documentos e comunicacao com webservers
 *
 */
package br.com.basepro.fenixsped;

import br.com.basepro.fenixsped.assinaturaXml.AssinaDocumentos;
import br.com.basepro.fenixsped.util.TrataArquivos;
import br.com.basepro.fenixsped.ws.CadConsultaCadastro;
import br.com.basepro.fenixsped.ws.NfeCancelamento;
import br.com.basepro.fenixsped.ws.NfeCancelamento2;
import br.com.basepro.fenixsped.ws.NfeConsulta;
import br.com.basepro.fenixsped.ws.NfeConsulta2;
import br.com.basepro.fenixsped.ws.NfeInutilizacao;
import br.com.basepro.fenixsped.ws.NfeInutilizacao2;
import br.com.basepro.fenixsped.ws.NfeRecepcao;
import br.com.basepro.fenixsped.ws.NfeRecepcao2;
import br.com.basepro.fenixsped.ws.NfeRetRecepcao;
import br.com.basepro.fenixsped.ws.NfeRetRecepcao2;
import br.com.basepro.fenixsped.ws.NfeStatusServico;
import br.com.basepro.fenixsped.ws.NfeStatusServico2;
import br.com.basepro.fenixsped.util.XsdSchemaValidator;
import br.com.basepro.fenixsped.ws.RecepcaoEvento;
import br.com.basepro.fenixsped.ws.NfeDownloadNF;
import br.com.basepro.fenixsped.ws.NfeRecepcao3;
import br.com.basepro.fenixsped.ws.NfeRetRecepcao3;

/**
 *
 * @author vinicius
 */
public class Main {

    static String cAjuda = "" +
            "\n FenixSped" +
            "\n =========" +
            "\n Utilitario de para assinatura de documentos e comunicacao com webservers." +
            "\n" +
            "\n FenixSped <comando> <opcao1>...<opcaoN>" +
            "\n" +
            "\n Comando: assinar" +
            "\n ======== =======" +
            "\n Ex.:" +
            "\n FenixSped assinar <caminhoXml> <caminhoCertificado> <senha> [caminhoXmlNovo]" +
            "\n" +
            "\n onde:" +
            "\n     caminhoXml = Caminho para o arquivo XML a ser assinado" +
            "\n" +
            "\n     caminhoCertificado = Caminho para o certificado digital a ser usado na " +
            "\n assinatura." +
            "\n" +
            "\n     senha = Senha do Certificado Digital" +
            "\n" +
            "\n     caminhoXmlNovo = Para preservar o arquivo original sem assinatura, " +
            "\n informar caminho que contera copia do xml assinado" +
            "\n" +
            "\n" +
            "\n Comando: nfeRecepcao" +
            "\n ======== ===========" +
            "\n Ex.:" +
            "\n FenixSped nfeRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n Comando: nfeRetRecepcao" +
            "\n ======== ==============" +
            "\n Ex.:" +
            "\n FenixSped nfeRetRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n Comando: nfeCancelamento" +
            "\n ======== ===============" +
            "\n Ex.:" +
            "\n FenixSped nfeCancelamento <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n Comando: nfeInutilizacao" +
            "\n ======== ===============" +
            "\n Ex.:" +
            "\n FenixSped nfeInutilizacao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n Comando: nfeConsulta" +
            "\n ======== ===========" +
            "\n Ex.:" +
            "\n FenixSped nfeConsulta <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n Comando: nfeStatusServico" +
            "\n ======== ================" +
            "\n Ex.:" +
            "\n FenixSped nfeStatusServico <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
	    "\n Comando: nfeEvento" +
            "\n ======== =========" +
            "\n Ex.:" +
            "\n FenixSped nfeEvento <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n Comando: cadConsultaCadastro" +
            "\n ======== ===================" +
            "\n Ex.:" +
            "\n FenixSped cadConsultaCadastro <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
	    "\n Comando: nfeDownloadNF" +
            "\n ======== =============" +
            "\n Ex.:" +
            "\n FenixSped nfeDownloadNF <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoUF> <caminhoXMLprocNFe>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoUF = Código da Unidade da Federação do Destinatário" +
            "\n" +
            "\n     caminhoXMLprocNFe = Caminho onde salvar o xml já separando o procNFe contendo somente a nota fiscal" +
            "\n" +
            "\n" +
            "\n NFSE - NOTA FISCAL DE SERVIÇOS ELETRONICA"+
            "\n ====   ==== ====== == ======== ==========" +
            "\n" +
            "\n NDSF - IMPLEMENTAÇÃO DO PADRÃO DSF www.dsfnet.com.br"+
            "\n ====   ============= == ====== === =================" +
            "\n" +
            "\n Comando: ndsf_assinar" +
            "\n ======== ============" +
            "\n Ex.:" +
            "\n FenixSped ndsf_assinar <caminhoXml> <caminhoCertificado> <senha> [caminhoXmlNovo]" +
            "\n" +
            "\n onde:" +
            "\n     caminhoXml = Caminho para o arquivo XML a ser assinado" +
            "\n" +
            "\n     caminhoCertificado = Caminho para o certificado digital a ser usado na " +
            "\n assinatura." +
            "\n" +
            "\n     senha = Senha do Certificado Digital" +
            "\n" +
            "\n     caminhoXmlNovo = Para preservar o arquivo original sem assinatura, " +
            "\n informar caminho que contera copia do xml assinado" +
            "\n" +
            "\n" +
            "\n Comando: ndsf_nfeRecepcao" +
            "\n ======== ================" +
            "\n Ex.:" +
            "\n FenixSped ndsf_nfeRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: ndsf_nfeRetRecepcao" +
            "\n ======== ===================" +
            "\n Ex.:" +
            "\n FenixSped ndsf_nfeRetRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: ndsf_nfeCancelamento" +
            "\n ======== ====================" +
            "\n Ex.:" +
            "\n FenixSped ndsf_nfeCancelamento <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: ndsf_nfeInutilizacao" +
            "\n ======== ====================" +
            "\n Ex.:" +
            "\n FenixSped ndsf_nfeInutilizacao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: ndsf_nfeConsulta" +
            "\n ======== ================" +
            "\n Ex.:" +
            "\n FenixSped ndsf_nfeConsulta <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: ndsf_nfeStatusServico" +
            "\n ======== =====================" +
            "\n Ex.:" +
            "\n FenixSped ndsf_nfeStatusServico <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: ndsf_cadConsultaCadastro" +
            "\n ======== ========================" +
            "\n Ex.:" +
            "\n FenixSped ndsf_cadConsultaCadastro <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n" +
            "\n ABRASF - IMPLEMENTAÇÃO DO PADRÃO ABRASF"+
            "\n ======   ============= == ====== ======" +
            "\n" +
            "\n Comando: abrasf_assinar" +
            "\n ======== ============" +
            "\n Ex.:" +
            "\n FenixSped abrasf_assinar <caminhoXml> <caminhoCertificado> <senha> [caminhoXmlNovo]" +
            "\n" +
            "\n onde:" +
            "\n     caminhoXml = Caminho para o arquivo XML a ser assinado" +
            "\n" +
            "\n     caminhoCertificado = Caminho para o certificado digital a ser usado na " +
            "\n assinatura." +
            "\n" +
            "\n     senha = Senha do Certificado Digital" +
            "\n" +
            "\n     caminhoXmlNovo = Para preservar o arquivo original sem assinatura, " +
            "\n informar caminho que contera copia do xml assinado" +
            "\n" +
            "\n" +
            "\n Comando: abrasf_nfeRecepcao" +
            "\n ======== ================" +
            "\n Ex.:" +
            "\n FenixSped abrasf_nfeRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: abrasf_nfeRetRecepcao" +
            "\n ======== ===================" +
            "\n Ex.:" +
            "\n FenixSped abrasf_nfeRetRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: abrasf_nfeCancelamento" +
            "\n ======== ====================" +
            "\n Ex.:" +
            "\n FenixSped abrasf_nfeCancelamento <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: abrasf_nfeInutilizacao" +
            "\n ======== ====================" +
            "\n Ex.:" +
            "\n FenixSped abrasf_nfeInutilizacao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: abrasf_nfeConsulta" +
            "\n ======== ================" +
            "\n Ex.:" +
            "\n FenixSped abrasf_nfeConsulta <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: abrasf_nfeStatusServico" +
            "\n ======== =====================" +
            "\n Ex.:" +
            "\n FenixSped abrasf_nfeStatusServico <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
            "\n" +
            "\n Comando: abrasf_cadConsultaCadastro" +
            "\n ======== ========================" +
            "\n Ex.:" +
            "\n FenixSped abrasf_cadConsultaCadastro <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>" +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
            "\n" +
            "\n     codigoMunicipio = Código do Município" +
	    "\n" +
            "\n" +
	    "\n GNRE - GUIA NACIONAL DE RECOLHIMENTO - SEFAZ-PE"+
            "\n ====   ==== ======== == ============   ========" +
            "\n" +
            "\n" +
            "\n Comando: GnreLoteRecepcao" +
            "\n ======== ================" +
            "\n Ex.:" +
            "\n FenixSped GnreLoteRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> " +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
	    "\n" +
            "\n" +
            "\n Comando: GnreResultadoLote" +
            "\n ======== =================" +
            "\n Ex.:" +
            "\n FenixSped GnreLoteRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> " +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
	    "\n" +
            "\n" +
	    "\n Comando: GnreConfigUF" +
            "\n ======== ============" +
            "\n Ex.:" +
            "\n FenixSped GnreLoteRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> " +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser enviado" +
            "\n" +
            "\n     caminhoXMLRetorno = Caminho onde o retorno será salvo" +
            "\n" +
            "\n     caminhoCertificado = Caminho do certificado para autenticar acesso ao webserver" +
            "\n" +
            "\n     senhaCertificado = Senha do certificado" +
            "\n" +
            "\n     caminhoWebService = Url do serviço" +
	    "\n" +
            "\n" +
	    "\n UTILITARIOS"+
            "\n ===========" +
	    "\n" +
            "\n" +
	    "\n Comando: validar" +
            "\n ======== =======" +
            "\n Ex.:" +
            "\n FenixSped validar <caminhoXML> <caminhoXdsSchema> " +
            "\n" +
            "\n Onde: " +
            "\n     caminhoXML = Arquivo com conteudo a ser validado" +
            "\n" +
            "\n     caminhoXdsSchema = Caminho do Schema de validação" +
            "\n" +
            "\n";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (
                (args.length == 0) ||
                (args[0].equalsIgnoreCase("help")) ||
                (args.length < 3) ||
                (
                 ((!args[0].equalsIgnoreCase("assinar"))&&(args.length < 6))
                 &&
		 ((!args[0].equalsIgnoreCase("validar"))&&(args.length < 6))
                 &&
                 ((!args[0].equalsIgnoreCase("ndsf_assinar"))&&(args.length < 6))
                 &&
                 ((!args[0].equalsIgnoreCase("abrasf_assinar"))&&(args.length < 6))
                )
           )
        {
            System.out.println(cAjuda);
            System.exit(1);
        }

        if (args[0].equalsIgnoreCase("assinar")) {
            try {
                if (args.length == 4) {
                    AssinaDocumentos.main(new String[]{args[1], args[2], args[3]});
                } else {
                    AssinaDocumentos.main(new String[]{args[1], args[2], args[3], args[4]});
                }
            } catch (Exception ex) {
                System.out.println("Erro assinando documento");
                System.exit(1);
            }
        }

        if (args[0].equalsIgnoreCase("ndsf_assinar")) {
            try {
                if (args.length == 4) {
                    br.com.basepro.fenixsped.ndsf.Assinatura.main(new String[]{args[1], args[2], args[3]});
                } else {
                    br.com.basepro.fenixsped.ndsf.Assinatura.main(new String[]{args[1], args[2], args[3], args[4]});
                }
            } catch (Exception ex) {
                System.out.println("Erro assinando documento");
		ex.printStackTrace();
                System.exit(1);
            }
        }

        if (args[0].equalsIgnoreCase("abrasf_assinar")) {
            try {
                if (args.length == 4) {
                    br.com.basepro.fenixsped.abrasf.AssinaDocumentos.main(new String[]{args[1], args[2], args[3]});
                } else {
                    br.com.basepro.fenixsped.abrasf.AssinaDocumentos.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
                }
            } catch (Exception ex) {
                System.out.println("Erro assinando documento");
		ex.printStackTrace();
                System.exit(1);
            }
        }
	
	if (args[0].equalsIgnoreCase("validar")) {
            try {
                if (args.length == 3) {
                    XsdSchemaValidator.main(new String[]{args[1], args[2]});
                } 
            } catch (Exception ex) {
                System.out.println("Erro validando documento");
                System.exit(1);
            }
        }
	
	
        try {
            if (args[0].equalsIgnoreCase("nfeRecepcao11")) {
                NfeRecepcao.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeRecepcao20")) {
                NfeRecepcao2.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
	    if (args[0].equalsIgnoreCase("nfeRecepcao")) {
                NfeRecepcao3.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeRetRecepcao11")) {
                NfeRetRecepcao.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeRetRecepcao20")) {
                TrataArquivos.tiraFormatacao(args[1]);
                NfeRetRecepcao2.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeRetRecepcao")) {
                TrataArquivos.tiraFormatacao(args[1]);
                NfeRetRecepcao3.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeCancelamento11")) {
                NfeCancelamento.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeCancelamento")) {
		TrataArquivos.tiraFormatacao(args[1]);
                RecepcaoEvento.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeInutilizacao11")) {
                NfeInutilizacao.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeInutilizacao")) {
                NfeInutilizacao2.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeConsulta11")) {
                NfeConsulta.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeConsulta")) {
                TrataArquivos.tiraFormatacao(args[1]);
                NfeConsulta2.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeStatusServico11")) {
                NfeStatusServico.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("nfeStatusServico")) {
                TrataArquivos.tiraFormatacao(args[1]);
                NfeStatusServico2.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
	    if (args[0].equalsIgnoreCase("nfeEvento")) {
                TrataArquivos.tiraFormatacao(args[1]);
                RecepcaoEvento.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
            if (args[0].equalsIgnoreCase("cadConsultaCadastro")) {
                CadConsultaCadastro.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
	    if (args[0].equalsIgnoreCase("nfeDownloadNF")) {
		if (args.length < 6) {
			System.out.println("Para o serviço nfeDownloadNF deve informar os 6 paramentros:" +
				"\n caminhoXML " +
				"\n caminhoXMLRetorno " +
				"\n caminhoCertificado " +
				"\n senhaCertificado " +
				"\n caminhoWebService" +
				"\n CodigoUF ");
			System.exit(1);
		}
                TrataArquivos.tiraFormatacao(args[1]);
                NfeDownloadNF.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6], args[7]});
            }
            if (args[0].equalsIgnoreCase("ndsf_nfeRecepcao")) {
                br.com.basepro.fenixsped.ndsf.NfeRecepcao.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("ndsf_nfeRetRecepcao")) {
                br.com.basepro.fenixsped.ndsf.NfeRetRecepcao.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("ndsf_nfeCancelamento")) {
                br.com.basepro.fenixsped.ndsf.NfeCancelamento.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("ndsf_nfeInutilizacao")) {
                br.com.basepro.fenixsped.ndsf.NfeInutilizacao.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("ndsf_nfeConsulta")) {
                br.com.basepro.fenixsped.ndsf.NfeConsulta.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("ndsf_nfeStatusServico")) {
                br.com.basepro.fenixsped.ndsf.NfeStatusServico.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("ndsf_cadConsultaCadastro")) {
                br.com.basepro.fenixsped.ndsf.CadConsultaCadastro.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }


            //"\n FenixSped abrasf_nfeStatusServico <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>" +

            if (args[0].equalsIgnoreCase("abrasf_nfeRecepcao")) {
                br.com.basepro.fenixsped.abrasf.NfeRecepcao.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("abrasf_nfeRetRecepcao")) {
                br.com.basepro.fenixsped.abrasf.NfeRetRecepcao.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("abrasf_nfeCancelamento")) {
                br.com.basepro.fenixsped.abrasf.NfeCancelamento.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("abrasf_nfeInutilizacao")) {
                br.com.basepro.fenixsped.abrasf.NfeInutilizacao.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("abrasf_nfeConsulta")) {
                br.com.basepro.fenixsped.abrasf.NfeConsulta.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }
            if (args[0].equalsIgnoreCase("abrasf_nfeStatusServico")) {
                br.com.basepro.fenixsped.abrasf.NfeStatusServico.main(new String[]{args[1], args[2], args[3], args[4], args[5], args[6]});
            }


	    if (args[0].equalsIgnoreCase("GnreLoteRecepcao")) {
                br.com.basepro.fenixsped.gnre.pe.GnreLoteRecepcao.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
	    
	    if (args[0].equalsIgnoreCase("GnreResultadoLote")) {
                br.com.basepro.fenixsped.gnre.pe.GnreResultadoLote.main(new String[]{args[1], args[2], args[3], args[4], args[5]});
            }
	
	    
	    

        } catch (Exception ex) {
            System.out.println("Erro enviando documento");
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getMessage());
            if (ex  instanceof java.rmi.RemoteException) {
             ((java.rmi.RemoteException)ex).getMessage();
            }
            System.exit(2);
        }
    }
}
