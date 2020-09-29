package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import interfaces.ICalculador;

public class Calculador implements ICalculador {

	public Calculador() {
		super();
	}
	
	public double calcularProb() {
		return 0.0;
	}
	
	public double calcularCantidadInfo() {
		return 0.0;
	}
	
	public double calcularEntropia() {
		return 0.0;
	}
	
	public void hacerSimulacion(String rutaArch, HashMap<String, Double> probs, int N) {

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
		    		listaArch.add(archivos[i]+"\n");
		    	}
		    }
		}
		return listaArch;
	}
	
	
}
