package com.businessMock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.model.Respuesta;

import dto.ParamsEntradaDTO;

/**
 *	Mocks para emular consulta a la DB. 
 */
public class ReservasBOMock {

	private static final Logger LOGGER = Logger.getLogger("ReservasBOMock");
	private static final String rutaArchivo = new File("archivoDB.txt").getAbsolutePath();
	
	List<ParamsEntradaDTO> ListaReservaciones = new ArrayList<ParamsEntradaDTO>(); 
	//Lista que contendra una copia del archivoDB para los loops

	public Respuesta isButacaOcupadaMock(ParamsEntradaDTO paramsEntrDTO) {		
		Respuesta resp = null;
		if (paramsEntrDTO != null && !"".equals(paramsEntrDTO.getButaca())) {
			resp = new Respuesta();
			resp.setValido(true);
			ListaReservaciones = getListaReservacionesFromFile();
			for (ParamsEntradaDTO i : ListaReservaciones) { //reemplazo del switch
			    if (i.getButaca().equals(paramsEntrDTO.getButaca())) { 
			    	resp.setValido(false);
			    	continue;
			    }
			}
			if(resp.isValido()){
				setReservacionToFile(paramsEntrDTO);
			}
		}
		return resp;
	}	
	
	public List<ParamsEntradaDTO> getListaReservacionesFromFile() {
		List<ParamsEntradaDTO> lista = new ArrayList<ParamsEntradaDTO>();
		BufferedReader b = null;
		try {
			File f = new File(rutaArchivo);
			if(!f.exists()) {
			    f.createNewFile();
			    System.out.println("ArchivoDB faltante, fue Creado en: "+rutaArchivo);				
				return lista;
			}
            b = new BufferedReader(new FileReader(f));
            String readLine = b.readLine();
            System.out.println("Reading file using Buffered Reader");
            
            while (readLine != null) {
            	 String[] datos = readLine.split("&");
            		 ParamsEntradaDTO aux = new ParamsEntradaDTO (datos[0],datos[1],Integer.parseInt(datos[2]), Boolean.parseBoolean(datos[3]),datos[4],datos[5]);
            		 lista.add(aux);
            	readLine = b.readLine();
            }           

        } catch (IOException e) {
            e.printStackTrace();
        }		
		
		finally {
			try {
				b.close();
			} catch (java.lang.NullPointerException | IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return lista;
	}
	
	public void setReservacionToFile(ParamsEntradaDTO j) {
		ListaReservaciones.add(j);
		BufferedWriter b = null;
		try {	
			File f = new File(rutaArchivo);
	        b = new BufferedWriter(new FileWriter(f));
	        System.out.println("Escribiendo archivo usando Buffered Writer...");
	        
	        for (Iterator<ParamsEntradaDTO> i = ListaReservaciones.iterator(); i.hasNext();) {
	       	    b.write(i.next().getObjetoFormateado());
	       	    b.newLine();
	        }
	       	System.out.println("Archivo Generado con Exito");
	       	System.out.println("Ubicacion del archivo: "+rutaArchivo);
	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }		
		finally {
			try {
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return;
	}
}
