package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import excepciones.CantidadIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;
import interfaces.ICalculador;

public class Calculador implements ICalculador {
	
	private HashMap<String, Double> probsAct;
	private HashMap<String, Double> cantInfoAct;
	private double entropiaAct;
	
	public Calculador() {
		super();
	}
	
	public double calcularProb() {
		return 0.0;
	}
	
	public void hacerCalculos(String nombreArch) throws FileNotFoundException {
        Scanner input = new Scanner(new File(".\\datos\\" + nombreArch));
        probsAct= new HashMap<String, Double>();
        cantInfoAct = new HashMap<String, Double>();
        int cantidadTotalSimbolos = 0;
        HashMap<String,Integer> hashmapCants = new HashMap<String,Integer>();
        if(input.hasNextLine()) {
        	String line = input.nextLine();
        	int i = 0;
        	String simboloActual;
        	while(i < line.length()) {
        		simboloActual = String.valueOf(line.charAt(i));
                if(hashmapCants.containsKey(simboloActual)){
                	hashmapCants.put(simboloActual, hashmapCants.get(simboloActual)+1);
               }
               else{
            	   hashmapCants.put(simboloActual, 1);
               }
               i++;
        	}
            for (Map.Entry<String, Integer> entry : hashmapCants.entrySet()) {
                cantidadTotalSimbolos = cantidadTotalSimbolos + entry.getValue();
            }
            double prob_act = 0.0;
            for (Map.Entry<String, Integer> entry : hashmapCants.entrySet()) {
                  prob_act = (double) entry.getValue() / cantidadTotalSimbolos;
                  probsAct.put(entry.getKey(), prob_act);
                  cantInfoAct.put(entry.getKey(), Math.log(1/prob_act) / Math.log(2));
            }
            for (Map.Entry<String, Double> entry : probsAct.entrySet()) {
            	entropiaAct = entropiaAct + entry.getValue() * cantInfoAct.get(entry.getKey());
          }
            
        }
        input.close();

	}
	
	public void hacerSimulacion(String nombreArch, HashMap<String, Double> probs, int N) throws CantidadIncorrectaException
	,SumaIncorrectaException,ValorIncorrectoException{
		//validaciones
		if(N <= 0)
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
		
		//armo las estructuras necesarias
		String[] claves = probs.keySet().toArray(new String[0]);
		Double[] valores = probs.values().toArray(new Double[0]);
		for(int i=1; i< valores.length; i++) {
			valores[i] = valores[i]+valores[(i-1)]; 
		}
		
		//calculos
		double randActual;
		StringBuilder str = new StringBuilder(); 
		for(int i=0; i < N; i++) {
			randActual = Math.random();
			str.append(establecerSimbolo(claves,valores,randActual));
		}
		this.actualizarArchivo(nombreArch, str.toString());
	}
	
	public String establecerSimbolo(String[] claves, Double[] valores, double randActual) {
		int i = 0;
		while((i< valores.length) && (randActual < valores[i])) {
			i++;
		}
		if(i ==  valores.length)
			return claves[(i-1)];
		else
			return claves[i];
			              
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

	public HashMap<String, Double> getProbsAct() {
		return probsAct;
	}

	public HashMap<String, Double> getCantInfoAct() {
		return cantInfoAct;
	}

	public double getEntropiaAct() {
		return entropiaAct;
	}
	
	
}
