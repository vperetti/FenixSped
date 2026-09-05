#!/bin/bash
# Valida um XML fiscal contra o pacote de esquemas deste diretorio (schemas/).
# Escolhe o XSD pelo elemento raiz do XML. Uso:
#   validar.sh <arquivo.xml> [<arquivo2.xml> ...]
#   validar.sh --todos            valida todos os exemplos em schemas/exemplos
# Saida: OK/FALHA por arquivo; codigo de retorno 1 se algum falhar.
# Requer xmllint (libxml2). Pode rodar local ou no servidor (/basepro/executaveis/schemas).

set -u
BASE="$(cd "$(dirname "$0")/.." && pwd)"
NFE="$BASE/nfe/current"
NFE_EVT="$BASE/nfe/eventos"
SNNS="$BASE/nfse/snns/current"
ABRASF="$BASE/nfse/abrasf"
DSF="$BASE/nfse/dsf"

raiz() {  # nome do elemento raiz sem prefixo/namespace
   sed -n '1,5p' "$1" | tr -d '\n' | sed 's/<?xml[^>]*?>//; s/<!--.*-->//' | grep -o '<[A-Za-z_:][A-Za-z0-9_:.-]*' | head -1 | sed 's/^<//; s/.*://'
}

xsd_para() {
   case "$1" in
      enviNFe)              echo "$NFE/enviNFe_v4.00.xsd" ;;
      NFe)                  echo "$NFE/nfe_v4.00.xsd" ;;
      nfeProc)              echo "$NFE/procNFe_v4.00.xsd" ;;
      envEvento)            echo "$NFE_EVT/PL_010d_v1.03/Evento/envEvento_v1.00.xsd" ;;
      consSitNFe)           echo "$NFE_EVT/PL_010d_v1.03/NFe/consSitNFe_v4.00.xsd" ;;
      inutNFe)              echo "$NFE_EVT/PL_010d_v1.03/NFe/inutNFe_v4.00.xsd" ;;
      DPS)                  echo "$SNNS/DPS_v1.00.xsd" ;;
      NFSe)                 echo "$SNNS/NFSe_v1.00.xsd" ;;
      pedRegEvento)         echo "$SNNS/pedRegEvento_v1.00.xsd" ;;
      evento)               echo "$SNNS/evento_v1.00.xsd" ;;
      EnviarLoteRpsEnvio|CancelarNfseEnvio|ConsultarNfseEnvio|ConsultarLoteRpsEnvio|ConsultarNfseRpsEnvio|GerarNfseEnvio|EnviarLoteRpsSincronoEnvio)
                            echo "$ABRASF/nfse.xsd.xml" ;;
      ReqEnvioLoteRPS)      echo "$DSF/ReqEnvioLoteRPS.xsd" ;;
      ReqCancelamentoNFSe)  echo "$DSF/ReqCancelamentoNFSe.xsd" ;;
      ReqConsultaLote)      echo "$DSF/ReqConsultaLote.xsd" ;;
      ReqConsultaNotas)     echo "$DSF/ReqConsultaNotas.xsd" ;;
      *)                    echo "" ;;
   esac
}

valida() {
   local xml="$1" r xsd
   r="$(raiz "$xml")"
   xsd="$(xsd_para "$r")"
   if [ -z "$xsd" ]; then echo "SEM XSD   $xml (raiz '$r')"; return 2; fi
   # a versao "current" do snns pode ser 1.01: ajusta o nome do arquivo
   if [ ! -f "$xsd" ] && [ -n "$(ls "$SNNS"/*_v1.01.xsd 2>/dev/null)" ]; then xsd="${xsd/_v1.00/_v1.01}"; fi
   if [ ! -f "$xsd" ]; then echo "XSD AUSENTE $xsd para $xml"; return 2; fi
   if out=$(xmllint --noout --schema "$xsd" "$xml" 2>&1); then
      echo "OK        $xml  [$r -> ${xsd#$BASE/}]"; return 0
   else
      echo "FALHA     $xml  [$r -> ${xsd#$BASE/}]"; echo "$out" | grep -v "fails to validate" | sed 's/^/          /'; return 1
   fi
}

rc=0
if [ "${1:-}" == "--todos" ]; then
   set -- "$BASE"/exemplos/*.xml
fi
[ $# -eq 0 ] && { echo "uso: $0 <xml> [xml...] | --todos"; exit 2; }
for f in "$@"; do valida "$f" || rc=1; done
exit $rc
