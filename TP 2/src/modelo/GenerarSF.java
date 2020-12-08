package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import modelo.GenerarHuffman.Codificacion;

import java.util.PriorityQueue;

public class GenerarSF {
    private HashMap<Character,String> tablaCod = new HashMap<Character,String>();
    
    public GenerarSF() 
    {
        super();
    }
    
	public double tasaCompresion(String mensajeOriginal,String mensajeComprimido)
	{
		double longitudOriginal = mensajeOriginal.length()*8;
		double longitudComprimido = mensajeComprimido.length();
		double TC=longitudOriginal/longitudComprimido;
		return TC;
	}
	
	
    public void generarSF(Simbolo[] tablaArray)
    {
        HashMap<Character,Double> tabla = new HashMap<Character,Double>();
        int i = 0;
        while(i < tablaArray.length) {
        	tabla.put(tablaArray[i].simbolo.charAt(0), tablaArray[i].probabilidad);
        	i++;
        }
        PriorityQueue<NodoShannonFano> tablaProb = new PriorityQueue<NodoShannonFano>(tabla.size(),new ComparadorSF());
        
        Iterator<Entry<Character,Double>> it2 = tabla.entrySet().iterator();
        ArrayList<NodoShannonFano> tablaOrd = new ArrayList<NodoShannonFano>();
        
        while(it2.hasNext())
        {
            Entry prox = it2.next();
            tablaProb.add(new NodoShannonFano((Character)prox.getKey(),(Double)prox.getValue()));
        }
        while(!tablaProb.isEmpty()){
            tablaOrd.add(tablaProb.poll());
        }
        
        comprimirSF(tablaOrd,"");
    }
    
    private void comprimirSF(ArrayList<NodoShannonFano> tablaProb, String s){
        ArrayList<NodoShannonFano> tablaSup = new ArrayList<NodoShannonFano>(),tablaInf = new ArrayList<NodoShannonFano>();
        int i = 0;
        double difAct,difAnt,sumaSup=0,sumaInf=0;
        NodoShannonFano aux;
        
        if(tablaProb.size()== 1){
            tablaCod.put(tablaProb.get(0).getCar(), s);
        }
        else{
            if(tablaProb.size()==2){
                tablaCod.put(tablaProb.get(0).getCar(), s+"0");
                tablaCod.put(tablaProb.get(1).getCar(), s+"1");
            }
            else{
                int div = (int) Math.ceil(tablaProb.size()/2.0);
                i=div;
                for(int j=0; j<div; j++){
                    sumaSup+=tablaProb.get(j).getData();
                }
                for(int j=div; j<tablaProb.size(); j++){
                    sumaInf+=tablaProb.get(j).getData();
                }
                difAnt=Math.abs(sumaSup-sumaInf);
                sumaSup-=tablaProb.get(i-1).getData();
                sumaInf+=tablaProb.get(i-1).getData();
                difAct=Math.abs(sumaSup-sumaInf);
                i--;
                while(i>0 && difAct<=difAnt){
                    difAnt = difAct;
                    sumaSup-=tablaProb.get(i-1).getData();
                    sumaInf+=tablaProb.get(i-1).getData();
                    difAct=Math.abs(sumaSup-sumaInf);
                    i--;
                }
                int j = tablaProb.size();
                j--;
                int k=i+1;
                while(j>=k){
                    tablaInf.add(tablaProb.get(k));
                    k++;
                }
                k=0;
                while(i>=k){
                    tablaSup.add(tablaProb.get(k));
                    k++;
                }
                this.comprimirSF(tablaSup, s+"0");
                this.comprimirSF(tablaInf, s+"1");
            }
        }
    }
    
    public String generarComprimido(String texto, HashMap<Character,String> tablaCod)
    {
		int tamañoTexto = texto.length();
		String mensajeComprimido="";
		Iterator it;
		for(int i=0;i<tamañoTexto;i++)
		{
			it = tablaCod.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry pair = (Map.Entry)it.next();
				if(((Character) texto.charAt(i)) == ((Character) pair.getKey()))
				{
					mensajeComprimido+= pair.getValue();
				}
			}
		}
		return mensajeComprimido;
    }
	
    
    public HashMap<Character, String> getTablaCod()
    {
        return tablaCod;
    }

	
}
