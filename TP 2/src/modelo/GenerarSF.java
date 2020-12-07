package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class GenerarSF {
    private HashMap<Character,String> tablaCod = new HashMap<Character,String>();
    
    public GenerarSF() 
    {
        super();
    }
    
    public void generarSF(ArrayList<Simbolo> tablaArray)
    {
        
        HashMap<Character,Double> tabla = new HashMap<Character,Double>();
        Iterator<Simbolo> it = tablaArray.iterator();
        while(it.hasNext()) {
        	tabla.put(it.next().simbolo.charAt(0), it.next().probabilidad);
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

    public HashMap<Character, String> getTablaCod()
    {
        return tablaCod;
    }
}
