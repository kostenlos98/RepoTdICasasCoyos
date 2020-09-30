package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import excepciones.CantidadIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;
import interfaces.ICalculador;

public class Calculador implements ICalculador {

	public Calculador() {
		super();
	}
	
	public double calcularProb() {
		return 0.0;
	}
	
	public ArrayList<Double> calcularCantidadInfo(HashMap<String, Double> probs) {
		ArrayList<Double> info = new ArrayList<Double>();
		//Tambien se podria usar un Iterator
		for(String i: probs.keySet())
		{
			Double prob = probs.get(i);
			info.add(-Math.log(prob)/Math.log(2));
		}
		return info;
	}
	
	public double calcularEntropia(HashMap<String, Double> probs) {
		double entropia = 0;
		for(String i: probs.keySet())
		{
			Double prob = probs.get(i);
			entropia+= prob*(-Math.log(prob)/Math.log(2));
		}
		return entropia;
	}
	
	public void hacerSimulacion(String rutaArch, HashMap<String, Double> probs, int N) throws CantidadIncorrectaException
	,SumaIncorrectaException,ValorIncorrectoException{
		
		if(probs.size()!=N)
			throw new CantidadIncorrectaException();
		double suma=0;
		for(String i: probs.keySet())
		{
			double prob = probs.get(i);
			if(prob < 0 || prob > 1)
				throw new ValorIncorrectoException();
			suma+=prob;
		}
		if(suma!=1)
			throw new SumaIncorrectaException();
		
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
	
	
}
