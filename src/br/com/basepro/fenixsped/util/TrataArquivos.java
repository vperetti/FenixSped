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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinicius
 */
public class TrataArquivos {
    public static void tiraFormatacao(String caminhoXml){
        FileWriter fw = null;
        try {
            StringBuffer bufferXml = new StringBuffer();
            BufferedReader reader = new BufferedReader(new FileReader(caminhoXml));
            String line = reader.readLine();
            int nLinha = 1;
            while (line != null) {
                bufferXml.append(line);
                nLinha++;
                line = reader.readLine();
            }
            fw = new FileWriter(caminhoXml);
            fw.write(bufferXml.substring(0));
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(TrataArquivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(TrataArquivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
