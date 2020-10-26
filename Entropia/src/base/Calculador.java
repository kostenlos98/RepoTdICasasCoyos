package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        Scanner input = new Scanner(new File(".\\datos\\" + nombreArch.trim()));
        probsAct= new HashMap<String, Double>();
        cantInfoAct = new HashMap<String, Double>();
        int cantidadTotalSimbolos = 0;
        entropiaAct=0;
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
		GestorArchs.get_instancia().actualizarArchivo(nombreArch, str.toString());
	}
	
	public String establecerSimbolo(String[] claves, Double[] valores, double randActual) {
		int i = 0;
		while((i< valores.length) && (randActual > valores[i])) {
			i++;
		}
		if(i ==  valores.length)
			return claves[(i-1)];
		else
			return claves[i];
			              
	}
	  
    public double entropiaEstacionario(double[][] matTrans, double[] estacionario, int cantSimbolos)
    {
        double totalCol, entropia=0;
        int i,j;
        
        for(j=0; j<cantSimbolos;j++)
        {
            totalCol=0;
            for(i=0;i<cantSimbolos;i++)
            {
                if(matTrans[i][j]!=0)
                {
                    totalCol+=matTrans[i][j]*(Math.log(1/matTrans[i][j])/Math.log(2));
                }
            }
            entropia += estacionario[j]*totalCol;
        }
        return entropia;
    }
    
       public double[][] matAmpliada(double[][] matTrans)
       {
           double[][] matAmp = new double[matTrans.length][matTrans[0].length+1];
           
           for (int i = 0 ; i < matTrans.length; i++)
           {
               for (int j = 0; j < matTrans[0].length; j++)
               {
                   matAmp[i][j] = matTrans[i][j];
                   if (i == j)
                   {
                       matAmp[i][j] -= 1;
                   }
               }
           }
           for (int j = 0; j < matAmp[0].length ; j++)
           {
               matAmp[matAmp.length-1][j]=1;
               //matAmp[0][j]=1;
           }
           
           return matAmp;
       }
       
    public double[] sistDiagonal(double[][] matAmp)// genera matriz de soluciones Xi = Bi / Aii, varios sist. a la vez
    {
        double[] vec = new double[matAmp.length];
        int i;
    
        for(i=0 ; i < matAmp.length ; i++)
            vec[i] = matAmp[i][matAmp[0].length-1] / matAmp[i][i];
        return vec;
    }
    
    public int pivoteoParcial(double[][] matAmp, int t)
    {
        int errorCL = 0, j, cantFilas = matAmp.length, i, filaPV, cantCol = matAmp[0].length;
        double[] vecAux = new double[cantCol];
        
        filaPV = t;
        for (i = t + 1; i < cantFilas; i++) /* busco fila pivot */
            if (Math.abs(matAmp[i][t]) > Math.abs(matAmp[filaPV][t]))
                filaPV = i;    
        if (Math.abs(matAmp[filaPV][t]) == 0)
            errorCL = 1;
        else
        {
            if (filaPV != t) /* intercambio filas */
            {
               for (j = 0; j < cantCol; j++)
                    vecAux[j] = matAmp[t][j];
               for (j = 0; j < cantCol; j++)
                    matAmp[t][j] = matAmp[filaPV][j];
               for (j = 0; j < cantCol; j++)
                    matAmp[filaPV][j] = vecAux[j]; 
            }
        }
        
        return errorCL;
    }
    
    public void gaussJordan(double[][] matAmp)
    {
        int i, j, t = 0, errorCL = 0, cantFilas = matAmp.length, cantCol = matAmp[0].length;
        
        while ((t < cantFilas) && (errorCL != 1))
        {
            errorCL = this.pivoteoParcial(matAmp, t);
            if (errorCL != 1)
            {
                for (i = 0; i <= (t - 1) ; i++) /* Triangulo Superior */
                {
                    for (j = t + 1; j < cantCol; j++)
                        matAmp[i][j] = matAmp[i][j] - matAmp[t][j] * matAmp[i][t] / matAmp[t][t];
                    matAmp[i][t] = 0.0;
                }
                for (i = t + 1; i < cantFilas ; i++) /* Triangulo Inferior */
                {
                    for (j = t + 1; j < cantCol; j++)   
                        matAmp[i][j] = matAmp[i][j] - matAmp[t][j] * matAmp[i][t] / matAmp[t][t];
                    matAmp[i][t] = 0.0;
                }
                t++;
            }
            else
                System.out.println("Error: Pivot = 0.0. Sist. Indeterminado: ecuaciones son comb. lineal.");
        }
    }
    
    public double[] generaVectorEstacionario(double[][] matTrans)
    {
        double[][] matAmp = this.matAmpliada(matTrans);
        this.gaussJordan(matAmp);
        return this.sistDiagonal(matAmp);
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
