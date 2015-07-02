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

package br.com.basepro.fenixsped.ndsf;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 *
 * @author vinicius
 */
public class CallWebService {
	public static String callWebService(String url, String servico, String xml) {
		String local = url;
		Call call;
		try {
			call = (Call) new Service().createCall();

			call.setTargetEndpointAddress(local);
			call.setOperationName(servico);
			Object[] param = new Object[] { xml };
			String ret = (String) call.invoke(param);
			return ret;
		} catch (ServiceException e) {
			e.printStackTrace();
			return "";

		} catch (RemoteException e) {
			e.printStackTrace();
			return "";
		}
	}
}
