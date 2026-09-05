#!/bin/bash
# Publica o pacote de esquemas em um servidor Fenix (/basepro/executaveis/schemas).
# Uso: deploy.sh <usuario@host> [porta=333]
# - Antes de publicar, roda validar.sh --todos localmente; aborta se algum exemplo falhar.
# - Faz backup remoto (schemas-BKP-AAAAMMDD-HHMM.tar.gz) e envia com rsync preservando os links "current".
# - Nao apaga nada que ja exista no servidor fora das pastas deste pacote.
set -eu
DEST="${1:?uso: deploy.sh usuario@host [porta]}"
PORTA="${2:-333}"
BASE="$(cd "$(dirname "$0")/.." && pwd)"
REMOTO="/basepro/executaveis/schemas"

echo "== validando exemplos locais"
"$BASE/tools/validar.sh" --todos

echo "== backup remoto"
ssh -p "$PORTA" "$DEST" "cd /basepro/executaveis && tar czf schemas-BKP-\$(date +%Y%m%d-%H%M).tar.gz schemas && ls -la schemas-BKP-*.tar.gz | tail -1"

echo "== enviando"
rsync -av -e "ssh -p $PORTA" --exclude 'tools/deploy.sh' \
   "$BASE/nfe" "$BASE/nfse" "$BASE/exemplos" "$BASE/tools" "$BASE/MANIFEST.md" \
   "$DEST:$REMOTO/"

echo "== conferindo no servidor"
# Conferencia remota com o validador que o Fenix realmente usa (FenixSped.jar / Xerces). O xmllint dos servidores
# CentOS 7 (libxml 2.9.1) tem defeito com os padroes Latin-1 do leiaute da NF-e (uCom/uTrib) e da falso negativo.
ssh -p "$PORTA" "$DEST" "cd $REMOTO && ls -la nfe/current nfse/snns/current && for x in exemplos/*.xml; do
   r=\$(sed -n '1,5p' \$x | tr -d '\n' | sed 's/<?xml[^>]*?>//' | grep -o '<[A-Za-z_:][A-Za-z0-9_:.-]*' | head -1 | sed 's/^<//; s/.*://')
   case \$r in enviNFe) s=nfe/current/enviNFe_v4.00.xsd;; NFe) s=nfe/current/nfe_v4.00.xsd;; DPS) s=nfse/snns/current/DPS_v1.00.xsd;; pedRegEvento) s=nfse/snns/current/pedRegEvento_v1.00.xsd;; *) s=nfse/abrasf/nfse.xsd.xml;; esac
   printf '%-60s %s\n' \"\$x\" \"\$(java -jar /basepro/executaveis/FenixSped.jar validar \$PWD/\$x \$PWD/\$s 2>&1 | tail -1)\"
done"
