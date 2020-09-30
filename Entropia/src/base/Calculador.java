package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import com.sun.jmx.snmp.Enumerated;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
	
	public void hacerSimulacion(String rutaArch, ArrayList<Estado> estados, int N) throws CantidadIncorrectaException
	,SumaIncorrectaException,ValorIncorrectoException{
		
		if(N<0)
			throw new CantidadIncorrectaException();
		double suma=0;
		Iterator<Estado> iterator = estados.iterator();
		while(iterator.hasNext())
		{
			double prob = iterator.next().getProb();
			if(prob < 0 || prob > 1)
				throw new ValorIncorrectoException();
			suma+=prob;
		}
		if(suma!=1)
			throw new SumaIncorrectaException();
		//Termina la verificacion
		bubbleSort(estados);
		ArrayList<Estado> FDA = generarFDA(estados);
		iterator = FDA.iterator();
		for(int i=0;i<N;i++)
		{
			System.out.println(generarSimbolo(FDA));
		}
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

	static void bubbleSort(ArrayList<Estado> estados) {  
        double n = estados.size();  
        Estado temp;  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(estados.get(j-1).getProb() > estados.get(j).getProb()){  
                                 temp = estados.get(j-1);  
                                 estados.set(j-1,estados.get(j));  
                                 estados.set(j,temp);
                         }  
                          
                 }  
         } 
	}
	
	
	
	public ArrayList<Estado> generarFDA(ArrayList<Estado> estados)
	{
		ArrayList<Estado> FDA = new ArrayList<Estado>();
		Iterator<Estado> iterator = estados.iterator();
		double suma = 0;
		while(iterator.hasNext())
		{
			Estado estado = iterator.next();
			FDA.add(new Estado(estado.getSimbolo(),estado.getProb()+suma));
			suma+=estado.getProb(); 
			
		}
		return FDA;
		
	}
	
	public String generarSimbolo(ArrayList<Estado> FDA)
	{
		double random = Math.random();
		Iterator<Estado> iterator = FDA.iterator();
		Estado estado = iterator.next();
		while(random>estado.getProb())
		{
			estado = iterator.next();
		}
		return estado.getSimbolo();
	}
	 
	
}
