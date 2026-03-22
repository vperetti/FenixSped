# FenixSped

> Integracao com Webservices do Projeto SPED, NFSe e GNRE
>
> Integration with Brazilian SPED, NFSe and GNRE Webservices

Utilitario Java e shell scripts para assinatura digital de documentos XML e comunicacao com webservices fiscais do governo brasileiro.

**Versao:** 3.11-6 | **Licenca:** GNU GPL v3.0 | **Autor:** Vinicius Peretti

---

## English Summary

FenixSped is a Java library and CLI tool for integrating with Brazilian government tax webservices. It handles XML digital signing and SOAP/REST communication for:

- **NFe** (Electronic Invoice) - versions 1.1 through 4.0
- **NFSe** (Electronic Service Invoice) - ABRASF, DSF, and SNNS standards
- **GNRE** (National Tax Collection Guide)

It provides a dual architecture: Java/SOAP (via Axis2) for NFe/GNRE/ABRASF operations, and shell/curl for SNNS (National Service Invoice) operations using REST/JSON. Designed for CLI usage and integration with legacy ERP systems (xHarbour, shell scripts).

**Requirements:** Java 6+, digital certificate A1 (PFX/P12). For SNNS operations: curl, openssl, xmllint, jq.

---

## Caracteristicas

- **NFe 1.1 a 4.0** - Emissao, consulta, cancelamento, inutilizacao, eventos, download
- **NFSe SNNS** - Nota Nacional de Servicos via REST/JSON (curl direto, sem Java)
- **NFSe ABRASF** - Padrao ABRASF via SOAP (Salvador, LEM, Recife, ISS.NET, Sinop)
- **NFSe DSF/NDSF** - Padrao DSF via SOAP
- **GNRE** - Guia Nacional de Recolhimento via SOAP (SEFAZ-PE)
- **Assinatura digital XML** - JSR-105 XML Digital Signature
- **Validacao XSD** - Validacao de XML contra schemas
- **Multi-estado** - Stubs SOAP por estado: BA, PR, RS, MT, MS, PE
- **Roteamento automatico** - Despacho por municipio para NFSe (Betha, Coplan, SEFIN)
- **Interface CLI** - Integracao facil com sistemas legados via linha de comando
- **Uso como biblioteca** - Pode ser usado programaticamente em projetos Java

---

## Arquitetura

```
Aplicacao (xHarbour / Shell / Java)
          |
          v
+--------------------+
| FenixSped (script) |  <-- Dispatcher principal
+--------------------+
    |           |
    |           +---> Comando snns_* ?
    |                     |
    |                     v
    |              +-------------+
    |              | FenixSnns   |  Shell/curl
    |              +-------------+  REST/JSON (SEFIN)
    |                     |         SOAP (Betha, Coplan)
    |                     |
    |                     +---> openssl (PFX->PEM)
    |                     +---> curl (HTTP/TLS)
    |                     +---> xmllint, jq, gzip
    |
    +---> Outros comandos
              |
              v
       +--------------+
       | FenixSped.jar|  Java/Axis2
       +--------------+  SOAP 1.1
              |
              +---> NFe (v1.1 - v4.0)
              +---> ABRASF (NFSe municipal)
              +---> NDSF (NFSe DSF)
              +---> GNRE (recolhimento)
              +---> Assinatura XML
              +---> Validacao XSD
```

---

## Requisitos

| Componente | Versao | Para |
|------------|--------|------|
| Java JRE | 6+ | Todas as operacoes via JAR |
| openssl | qualquer | Conversao PFX->PEM (SNNS) |
| curl | qualquer | Comunicacao REST/SOAP (SNNS) |
| xmllint | qualquer | Parse XML (SNNS) |
| jq | qualquer | Parse JSON (SNNS) |
| Certificado digital A1 | PFX/P12 | Autenticacao nos webservices |

---

## Instalacao

### Pacote binario (recomendado)

1. Baixar `FenixSped-4-2026a.tar.bz2` da secao [Releases](../../releases)
2. Extrair: `tar xjf FenixSped-4-2026a.tar.bz2`
3. Copiar a estrutura para o destino:

```bash
# Copiar JAR e libs
cp FenixSped/FenixSped.jar /basepro/executaveis/
cp -r FenixSped/lib/ /basepro/executaveis/

# Copiar scripts (ja vem na estrutura correta em root/)
cp -r FenixSped/root/basepro/executaveis/* /basepro/executaveis/
chmod +x /basepro/executaveis/FenixSped /basepro/executaveis/FenixSnns /basepro/executaveis/FenixSpedFuncoes

# Criar link simbolico no PATH - a partir dele o FenixSped
# encontra automaticamente FenixSnns, FenixSpedFuncoes e FenixSped.jar
# no mesmo diretorio (/basepro/executaveis/)
ln -sf /basepro/executaveis/FenixSped /usr/local/bin/FenixSped
```

### Estrutura de deploy

O pacote inclui a pasta `root/` com a arvore de diretorios onde os scripts devem ser instalados:

```
root/
  basepro/
    executaveis/
      FenixSped              # Script dispatcher (criar link simbolico no /usr/local/bin/)
      FenixSnns              # Funcoes SNNS (curl)
      FenixSpedFuncoes       # Funcoes auxiliares
      fenixBuscaXmlNfeSefaz  # Download NFe da SEFAZ
  bin/
    FenixSped                # Variante com interface dialog

/basepro/                    # Destino final apos instalacao
  executaveis/
    FenixSped           # Script dispatcher
    FenixSnns           # Funcoes SNNS (curl)
    FenixSpedFuncoes    # Funcoes auxiliares
    FenixSped.jar       # Biblioteca Java
    lib/                # Dependencias (68 JARs)
    FenixSped.log       # Log de operacoes
  logs/
    FenixSnns.log       # Log de operacoes SNNS
  exportacoes/
    nf_e/               # XMLs de NFe
  certificados/
    empresa.pfx         # Certificado digital

/usr/local/bin/
  FenixSped -> /basepro/executaveis/FenixSped  # Link simbolico
```

> **Dica:** Com o link simbolico em `/usr/local/bin/`, basta chamar `FenixSped` de qualquer lugar. O script usa caminhos relativos ao seu diretorio para encontrar FenixSnns, FenixSpedFuncoes e FenixSped.jar.

---

## Uso - Linha de Comando

Executar sem argumentos para ver ajuda:
```bash
java -jar FenixSped.jar
```

### NFe - Nota Fiscal Eletronica (via Java/SOAP)

#### Assinar XML

```bash
FenixSped assinar <caminhoXml> <caminhoCertificado> <senha> [caminhoXmlNovo]
```

- `caminhoXml` - Arquivo XML a ser assinado
- `caminhoCertificado` - Certificado digital (PFX/P12)
- `senha` - Senha do certificado
- `caminhoXmlNovo` - (Opcional) Caminho para salvar copia assinada, preservando original

#### Operacoes NFe (formato padrao - 5 parametros)

```bash
FenixSped <comando> <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>
```

| Comando | Descricao | Sufixos de versao |
|---------|-----------|-------------------|
| `nfeRecepcao` | Envio de lote | 11, 20, 31 |
| `nfeRetRecepcao` | Consulta retorno do lote | 11, 20, 31 |
| `nfeCancelamento` | Cancelamento | 11, 1 (evento) |
| `nfeInutilizacao` | Inutilizacao de numeracao | 11, 2 |
| `nfeConsulta` | Consulta por protocolo | 11, 2 |
| `nfeStatusServico` | Status do servico | 11, 20, 31 |
| `nfeEvento` | Recepcao de eventos | 2 |
| `cadConsultaCadastro` | Consulta cadastral | - |
| `nfeConsultaDest` | Consulta destinatario | - |
| `nfeDistribuicaoDfe` | Distribuicao DFe | - |

> Sem sufixo = versao 4.0 (padrao atual). Ex: `nfeRecepcao` = NFe 4.0, `nfeRecepcao31` = NFe 3.1

#### Download de NFe (7 parametros)

```bash
FenixSped nfeDownloadNF <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoUF> <caminhoXMLprocNFe>
```

### NFSe SNNS - Nota Nacional de Servicos (via curl/REST)

#### Envio de DPS

```bash
FenixSped snns_nfeRecepcao <xmlDPS> <xmlRetorno> <certificado> <senha> <urlWebService> [codigoMunicipio]
```

Fluxo interno:
1. Converte certificado PFX para PEM (cache automatico)
2. Comprime XML com gzip e codifica em base64
3. Envia via REST/JSON para SEFIN Nacional (ou SOAP para provedores especificos)
4. Salva resposta com backup timestamped

#### Download de DANFSE (PDF)

```bash
FenixSped snns_danfse <chaveAcesso> <caminhoSaidaPDF> <certificado> <senha> <urlWebService>
```

### NFSe ABRASF (via Java/SOAP)

Formato: 6 parametros (adiciona `codigoMunicipio` ao final)

```bash
FenixSped abrasf_<comando> <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>
```

Comandos: `abrasf_assinar`, `abrasf_nfeRecepcao`, `abrasf_nfeRetRecepcao`, `abrasf_nfeCancelamento`, `abrasf_nfeInutilizacao`, `abrasf_nfeConsulta`, `abrasf_nfeStatusServico`, `abrasf_cadConsultaCadastro`

Municipios suportados: Salvador/BA, LEM, Recife/PE, ISS.NET Online, Sinop/MT (v1-v4)

### NFSe DSF/NDSF (via Java/SOAP)

Mesmo formato do ABRASF. Implementacao do padrao DSF (dsfnet.com.br).

```bash
FenixSped ndsf_<comando> <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService> <codigoMunicipio>
```

Comandos: `ndsf_assinar`, `ndsf_nfeRecepcao`, `ndsf_nfeRetRecepcao`, `ndsf_nfeCancelamento`, `ndsf_nfeInutilizacao`, `ndsf_nfeConsulta`, `ndsf_nfeStatusServico`, `ndsf_cadConsultaCadastro`

### GNRE - Guia Nacional de Recolhimento (via Java/SOAP)

```bash
FenixSped GnreLoteRecepcao <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>
FenixSped GnreResultadoLote <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>
FenixSped GnreConfigUF <caminhoXML> <caminhoXMLRetorno> <caminhoCertificado> <senhaCertificado> <caminhoWebService>
```

### Utilitarios

#### Validacao XSD

```bash
FenixSped validar <caminhoXML> <caminhoXsdSchema>
```

---

## Uso como Biblioteca Java

Alem do CLI, o FenixSped pode ser usado como biblioteca em projetos Java:

```java
import br.com.basepro.fenixsped.ws.NfeRecepcao4;
import br.com.basepro.fenixsped.assinaturaXml.AssinaDocumentos;
```

A documentacao completa da API esta disponivel no Javadoc incluso no pacote de distribuicao.

---

## Scripts de Integracao

### FenixSped (dispatcher principal)
Script shell que recebe o comando, carrega funcoes auxiliares e despacha para FenixSnns (SNNS) ou FenixSped.jar (demais).

### FenixSnns (NFSE via curl)
Implementa comunicacao direta via curl para Nota Nacional de Servicos, sem necessidade do Java. Suporta:
- REST/JSON para SEFIN Nacional
- SOAP para provedores Betha e Coplan
- Conversao automatica PFX->PEM
- Compressao gzip + base64 do XML

### FenixSpedFuncoes (helpers)
Funcoes auxiliares: `MostraStatus()` (exibe status da operacao) e `MsgSt()` (mensagens de tela).

### fenixBuscaXmlNfeSefaz
Script utilitario para download de NFe da SEFAZ usando fluxo de confirmacao de eventos (Ciencia da Operacao + Download).

---

## Provedores Municipais NFSe

O FenixSnns faz roteamento automatico pelo codigo do municipio:

| Municipio | Codigo IBGE | Provedor | Protocolo | Endpoint |
|-----------|-------------|----------|-----------|----------|
| Nacional (padrao) | * | SEFIN | REST/JSON | sefin.nfse.gov.br |
| Dourados/MS | 500370 | Betha | SOAP | nota-eletronica.betha.cloud |
| Sinop/MT | 510790 | Coplan | SOAP | gp.srv.br |

---

## Compilacao

Requer Apache Ant e JDK 6+:

```bash
ant clean jar
```

O JAR gerado fica em `dist/FenixSped.jar`.

Projeto NetBeans - pode ser aberto diretamente na IDE.

---

## Download / Release

Pacote binario pronto para uso, disponivel na secao [Releases](../../releases):

**FenixSped-4-2026a.tar.bz2**

Conteudo:
- `FenixSped.jar` - Aplicacao compilada (NFe 4.0 + SNNS)
- `lib/` - 68 bibliotecas de dependencia
- `root/basepro/executaveis/` - Scripts de integracao (FenixSped, FenixSnns, FenixSpedFuncoes, fenixBuscaXmlNfeSefaz)
- `root/bin/FenixSped` - Variante com interface dialog
- `README.md` - Esta documentacao
- `ajuda.txt` - Referencia de comandos
- `leiame.txt` - Instrucoes basicas

---

## Licenca

GNU General Public License v3.0

Copyright (C) Vinicius Peretti

Este programa e software livre; voce pode redistribui-lo e/ou modifica-lo sob os termos da Licenca Publica Geral GNU conforme publicada pela Free Software Foundation; versao 3 da Licenca.

---

## Historico

- **2015** - Publicacao original no SourceForge (v3.11-6)
  - NFe 1.1 a 3.1, ABRASF, DSF, GNRE
  - Interface CLI e biblioteca Java
- **2018** - Suporte a NFe 4.0
  - Novos stubs: NfeRecepcao4, NfeConsulta4, NfeStatusServico4, NfeInutilizacao4, RecepcaoEvento4
  - Consulta destinatario e distribuicao DFe
- **2024+** - SNNS (Nota Nacional de Servicos)
  - Comunicacao REST/JSON via curl (sem Java)
  - Roteamento por municipio (Betha, Coplan, SEFIN Nacional)
  - Download de DANFSE em PDF

---

## Links

- SourceForge: https://sourceforge.net/projects/fenixsped/
