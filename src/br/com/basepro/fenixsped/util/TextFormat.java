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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vinicius
 */
public class TextFormat {
	public Date stringToData(String data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date base = null;
		try {
			base = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return base;
	}

	public Date stringToData_DiaMesAno(String data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date base = null;
		try {
			if ( (data == null) || (data.isEmpty()) || (data == "") )
			{
			  base = null;
			}
			else
			{
			  base = format.parse(data);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return base;
	}

	public Integer stringToInteger(String valor ) throws ParseException{
		Integer iValor  = 0;
		if ( (valor == null) || (valor.isEmpty()) || (valor == "") )
		{
		  iValor =  0;
		}
		else
		{
		  iValor =  Integer.parseInt(valor);
		}
		return iValor;
	}

	public Date stringToDataHora(String data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date base = null;
		try {
			base = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return base;
	}

	public String dateToStringXML(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String base = null;
		base = df.format(data);
		return base;
	}

	public Date StringXMLToDateHora(String data) {
		SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String base = "";
		Date retorno = null;
		try {
			base = out.format(in.parse(data));
			retorno = stringToDataHora( base );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return retorno;
	}



	public String dateToString(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String base = null;
		base = format.format(data);
		return base;
	}

        public static String fillWith(String line, String charac, int size, int direction){
        if (line == null || line.trim() == "" ) {line = "";}
        while (line.contains("  ")) {line = line.replaceAll("  "," ").trim();}
        //line = line.replaceAll("[./-]","");
        StringBuffer sb = new StringBuffer(line);
        if (direction==1){ //a Esquerda
            for (int i=sb.length() ; i<size ; i++){
                sb.insert(0,charac);
            }
        } else if (direction==2) {//a Direita
            for (int i=sb.length() ; i<size ; i++){
                sb.append(charac);
            }
        }
        return sb.toString();
    }

}
