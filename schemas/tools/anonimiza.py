#!/usr/bin/env python3
"""Anonimiza um XML fiscal para uso como exemplo de regressao: troca CNPJ/CPF/nomes/contatos
e esvazia a assinatura, mantendo a estrutura (o que o XSD valida)."""
import re, sys
src, dst = sys.argv[1], sys.argv[2]
s = open(src, encoding='utf-8').read()
# CNPJs de 14 digitos (em tags, Ids e chaves) -> sequencia fixa por CNPJ encontrado
cnpjs = sorted(set(re.findall(r'<(?:CNPJ|CNPJAutor)>(\d{14})<', s)))
for i, c in enumerate(cnpjs, 1):
    s = s.replace(c, f"{"9"*8}{i:02d}0191")
cpfs = sorted(set(re.findall(r'<CPF>(\d{11})<', s)))
for i, c in enumerate(cpfs, 1):
    s = s.replace(c, f'{"9"*7}{i:02d}00')
for tag in ('xNome', 'xFant', 'xLgr', 'xCpl', 'xBairro', 'email', 'fone', 'IM', 'IE', 'xDescServ', 'infCpl', 'xMotivo', 'xDesc', 'xProd', 'xPed', 'CEP', 'nro'):
    s = re.sub(rf'<{tag}>([^<]*)</{tag}>', lambda m, t=tag: f'<{t}>' + {
        'xNome': 'EMPRESA EXEMPLO LTDA', 'xFant': 'EXEMPLO', 'xLgr': 'RUA EXEMPLO', 'xCpl': 'SALA 1',
        'xBairro': 'CENTRO', 'email': 'exemplo@exemplo.com.br', 'fone': '6700000000', 'IM': '123456',
        'IE': '123456789', 'xDescServ': 'SERVICO DE EXEMPLO PARA VALIDACAO DE ESQUEMA XML',
        'infCpl': 'INFORMACAO COMPLEMENTAR DE EXEMPLO', 'xMotivo': 'MOTIVO DE EXEMPLO PARA VALIDACAO',
        'xDesc': m.group(1), 'xProd': 'PRODUTO DE EXEMPLO', 'xPed': '1', 'CEP': '79000000', 'nro': '100'}[t] + f'</{t}>', s)
# assinatura: mantem estrutura, esvazia conteudo binario
s = re.sub(r'<DigestValue>[^<]*</DigestValue>', '<DigestValue>AAAA</DigestValue>', s)
s = re.sub(r'<SignatureValue>.*?</SignatureValue>', '<SignatureValue>AAAA</SignatureValue>', s, flags=re.S)
s = re.sub(r'<X509Certificate>.*?</X509Certificate>', '<X509Certificate>AAAA</X509Certificate>', s, flags=re.S)
open(dst, 'w', encoding='utf-8').write(s)
print('ok', dst, 'cnpjs:', len(cnpjs), 'cpfs:', len(cpfs))
