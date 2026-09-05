# Pacote de esquemas XML fiscais (FenixSped/schemas)

Publicado inteiro em `/basepro/executaveis/schemas` nos servidores Fenix (`tools/deploy.sh`).
Toda mensagem gerada pelo Fenix/SAV (xHarbour) e pelo FenixSped (Java) deve validar contra este
pacote: `tools/validar.sh --todos` roda sobre `exemplos/` e precisa passar antes de qualquer deploy.

Os links `current` apontam para a versao em producao. Trocar de versao = trocar o link + republicar.

## Conteudo e origem

| Pasta | Origem | Publicado em | SHA-256 do zip original |
|---|---|---|---|
| `nfe/PL_010f_v1.04/` | SEFAZ, "Pacote de Liberacao 010f - NT 2025.002 v1.50 e NT 2026.007 v1.00" (portal NF-e, Esquemas XML, conteudo=8ITFuBLltXs=) | 2026 | b8589490a58a09a9... |
| `nfe/PL_010f_v1.04/enviNFe_v4.00.xsd`, `procNFe_v4.00.xsd` | copiados do PL_009k (NT 2023.001 v1.20): o PL_010f nao traz o envelope de envio; eles so incluem `leiauteNFe_v4.00.xsd` e usam o tipo `TEnviNFe` do leiaute novo | 2023 | 80688b920523307e... |
| `nfe/eventos/PL_010d_v1.03/` | SEFAZ, "PL 010d v1.03 - CNPJ Alfanumerico - NT 2026.004": Evento, consulta situacao, inutilizacao, consulta cadastro | 2026 | 45ceefe4dfbbfec9... |
| `nfe/eventos/Eventos_RTC/` | SEFAZ, "Schema dos eventos da NT 2025.002 v1.40 - RTC" | 2026 | a4c57ce95b225cd8... |
| `nfse/snns/v1.00-20260209/` | gov.br/nfse, Documentacao Atual (Producao), `nfse-esquemas_xsd-v1-01-20260209.zip`, pasta `Schemas/1.00` (ja inclui o dominio da NT-007 em tpRetPisCofins e o CNC) | 2026-02-09 | e7935cbd9470527c... |
| `nfse/snns/v1.01-20260209/` | mesmo zip, pasta `Schemas/1.01` (leiaute com IBS/CBS para 2027) | 2026-02-09 | e7935cbd9470527c... |
| `nfse/abrasf/` | ABRASF nfse.xsd 2.0x em uso (identico ao que estava no servidor, md5 9bbb46c9...) | - | - |
| `nfse/dsf/` | DSF (Campo Grande) ReqEnvioLoteRPS e demais (identicos ao servidor) | - | - |

`nfe/current -> PL_010f_v1.04` e `nfse/snns/current -> v1.00-20260209`.

## Ajustes locais (documentados no proprio XSD com o comentario `AJUSTE LOCAL FenixSped`)

- `nfse/snns/v1.01-20260209/tiposSimples_v1.01.xsd`, tipo `TSSerieDPS`: o padrao oficial
  `^0{0,4}\d{1,5}$` usa ancoras, que nao existem em regex de XML Schema; o libxml2 (xmllint) trata
  `^` e `$` como literais e rejeita qualquer serie. Trocado por `0{0,4}\d{1,5}`. A pasta 1.00 do
  mesmo zip ja veio corrigida pela SEFIN.

## Exemplos de regressao (`exemplos/`)

XMLs reais anonimizados (CNPJ, nomes, enderecos, contatos e assinatura substituidos) com
`tools/anonimiza.py <original> <destino>`, gerados pelo Fenix. Servem para detectar quebra do gerador ou do esquema.

| Arquivo | Origem |
|---|---|
| `nfe-enviNFe-fw-52446.xml` | NF-e 4.00 com IBS/CBS emitida pela FW Maquinas em 04/09/2026 (errorlog fwfnx) |
| `nfse-snns-dps-dourados-1194.xml` | DPS de Dourados/MS autorizada no Emissor Nacional em 04/09/2026 (CHG-1130) |
| `nfse-snns-pedregevento-cancelamento.xml` | pedido de registro do evento e101101 montado sobre a chave do exemplo acima |
| `nfse-abrasf-ConsultarNfseRpsEnvio.xml` | exemplo oficial ABRASF |

Achado ao validar: o `Id` de `infPedReg` e `PRE` + chave (50) + tipo do evento (6) = 59 caracteres,
sem o `nPedRegEvento`. O manual descreve o campo com o sequencial, mas o XSD (`TSIdPedRegEvt`,
`PRE[0-9]{56}`) nao o aceita. O sequencial entra so no `Id` do evento gerado (`EVT` + 59).

## Validacao no servidor

Em producao quem valida e o `FenixSped.jar` (Xerces). O `xmllint` dos servidores CentOS 7 (libxml 2.9.1) rejeita
`uCom`/`uTrib` da NF-e por defeito nos padroes Latin-1 do leiaute; por isso `deploy.sh` confere os exemplos no
servidor com o jar, nao com o xmllint. Localmente (libxml atual) o `validar.sh` funciona para tudo.

Publicado em producao (nors.basepro.net) em 05/09/2026: a pasta original `schemas` era de root (2018) e foi
renomeada para `schemas-old-2018-root`; a nova e do usuario compilador, com o conteudo antigo copiado dentro.

## Como atualizar

1. Baixar o zip oficial, conferir o SHA-256 e registrar aqui.
2. Descompactar em pasta nova com a versao/data no nome; nunca sobrescrever a anterior.
3. Reaplicar os ajustes locais se o defeito persistir; registrar acima.
4. `tools/validar.sh --todos`; se um exemplo falhar, decidir se e o esquema ou o gerador.
5. Apontar o link `current`, commitar, `tools/deploy.sh compilador@<host>` em QA e depois em producao.

## Quem consome

- SAV (xHarbour): validacao em segundo plano na emissao (`savnf_es_`) e, a partir do CHG-1130,
  funcao unica de resolucao de caminho por tipo de documento.
- FenixSped (Java): `XsdSchemaValidator` (`FenixSped validar <xml> <xsd>`), que resolve os includes
  a partir do caminho do XSD informado, portanto funciona atraves dos links `current`.
