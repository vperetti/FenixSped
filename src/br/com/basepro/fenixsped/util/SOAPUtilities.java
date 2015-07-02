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
package br.com.basepro.fenixsped.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.axis2.context.MessageContext;

/**
 * Utilitários SOAP
 * @author Vinicius Peretti
 */
public class SOAPUtilities {
/**
	 * Coloca a mensagem SOAP no output padrão
	 * @param stub Stub cuja última mensagem será mostrada.
	 */
	public static void SoapMessageOutput(org.apache.axis2.client.Stub stub) {
		HashMap msgContexts = stub._getServiceClient().getLastOperationContext().getMessageContexts();
		Set<String> keySet = msgContexts.keySet();
		Iterator<String> keySetIterator = keySet.iterator();
		while (keySetIterator.hasNext()) {
			System.out.println("------------------------------------------------");
			System.out.println("Verificando mensagens SOAP");
			String key = keySetIterator.next();
			System.out.println("key: " + key + "  ");
			System.out.println("--------------<mensagem remetida>----------------");
			//System.out.println("tostring:" + msgContexts.get(key).toString());
			//System.out.println("classname:" + msgContexts.get(key).getClass().getName());

			MessageContext msgContext = (MessageContext) msgContexts.get(key);
			//System.out.println("messagecontext.tostring:" + msgContext.toString());
			System.out.println("messagecontext.getenvelope.tostring:");
			System.out.println(msgContext.getEnvelope().toString());
			//java -jar /basepro/executaveis/FenixSpedt3.2.jar nfeInutilizacao /basepro/sav0001/gm/nf_e/certificado/5014070379546500106555001000004276190870165-ped-inu.xml /basepro/sav0001/gm/nf_e/5014070379546500106555001000004276190870165-inu.xml /basepro/executaveis/tecnoeste20140815.pfx 5659 https://nfe.fazenda.ms.gov.br/producao/services2/NfeInutilizacao2  


		}
	}
}
