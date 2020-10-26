package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchs {
	private static GestorArchs _instancia;
	
	private GestorArchs() {
		super();
	}
	
	public static GestorArchs get_instancia() {
		if(_instancia == null)
			_instancia = new GestorArchs();
		return _instancia;
	}
	
	public void nuevoArchivo(String nombre) {
        try {
            String ruta = "./datos/" + nombre + ".txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void actualizarArchivo(String nombre,String datos)
	{
		try {
			String ruta = ".\\datos\\" + nombre;
			FileWriter fileWriter = new FileWriter(ruta.trim());
			fileWriter.write(datos);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> listarArchivos() {
		ArrayList<String> listaArch = new ArrayList<String>();
		File carpeta = new File("./datos");
		File[] archivos = carpeta.listFiles();
		if (archivos == null || archivos.length == 0) {
			listaArch.add("La carpeta datos esta vacia!");
		}
		else {
		    for (int i=0; i< archivos.length; i++) {
		    	if(!archivos[i].isDirectory()) {
		    		listaArch.add(archivos[i].getName()+"\n");
		    	}
		    }
		}
		return listaArch;
	}
	
	public String getTextoArch(String nombre) {
		String todo = "";
		try(BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    todo = sb.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return todo;
	}
}
