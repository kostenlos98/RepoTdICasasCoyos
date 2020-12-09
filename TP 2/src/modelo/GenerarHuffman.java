package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class GenerarHuffman {
	
	static class Nodo {    
	    Simbolo valor; 
	        Nodo izq, der; 
	          
	        Nodo(Simbolo valor){ 
	            this.valor = valor;
	            izq = null; 
	            der = null; 
	        }
	}
	
	static class Codificacion
	{
		char simbolo;
		double probabilidad;
		String codigo;
	}
	
	public double tasaCompresion(String mensajeOriginal,String mensajeComprimido)
	{
		double longitudOriginal = mensajeOriginal.length()*8;
		double longitudComprimido = mensajeComprimido.length();
		double TC=longitudOriginal/longitudComprimido;
		return TC;
	}
	
	public String textoComprimido(String mensaje,Simbolo probSimbolos[])
	{
		return comprimirHuffman(generarHuffman(probSimbolos),mensaje);
	}
	
	private double entropia(Simbolo[] simbolos)
	{
		ArrayList<Codificacion> codificacion = generarHuffman(simbolos);
		double acum =0;
		Iterator<Codificacion> iterator = codificacion.iterator();
		while(iterator.hasNext())
		{
			double probabilidad = iterator.next().probabilidad;
			acum+=probabilidad*Math.log(1/probabilidad)/Math.log(2);
		}
		return acum;
	}
	
	private double longitudMedia(Simbolo[] simbolos)
	{
		ArrayList<Codificacion> codificacion = generarHuffman(simbolos);
		double acum=0;
		Iterator<Codificacion> iterator = codificacion.iterator();
		while(iterator.hasNext())
		{
			Codificacion codificacionAux = iterator.next();
			double probabilidad = codificacionAux.probabilidad;
			double longitud = codificacionAux.codigo.length();
			acum+=probabilidad*longitud;
		}
		return acum;
	}
	
	public double redundancia(Simbolo[] simbolos)
	{
		double entropia = entropia(simbolos);
		double longitudMedia = longitudMedia(simbolos);
		return entropia(simbolos)/longitudMedia(simbolos);
	}
	
	
	public ArrayList<Codificacion> generarHuffman(Simbolo[] simbolos)
	{
		Stack<Nodo> pila = new Stack<Nodo>();
		for(int i=0;i<simbolos.length;i++)
		{
			Nodo nodo = new Nodo(simbolos[i]);
			pila.push(nodo);
		}
		ordenarPila(pila);
		int tamaño=pila.size();
		while(tamaño>1)
		{
			Nodo nodo1,nodo2;
			nodo1=pila.pop();
			nodo2=pila.pop();
			Nodo nodoNuevo = new Nodo(new Simbolo(nodo1.valor.probabilidad+nodo2.valor.probabilidad, ""));
			nodoNuevo.izq = nodo1;
			nodoNuevo.der = nodo2;
			pila.push(nodoNuevo);
			ordenarPila(pila);
			tamaño=pila.size();
		}
		ArrayList<Codificacion> codificacion = new ArrayList<Codificacion>();
		generarCodigosArbol(pila.pop(), "",codificacion);
		return codificacion;
		
	}
	
	public String comprimirHuffman(ArrayList<Codificacion> codificacion,String texto)
	{
		int tamañoTexto = texto.length();
		String mensajeComprimido="";
		Iterator<Codificacion> iterator;
		for(int i=0;i<tamañoTexto;i++)
		{
			iterator = codificacion.iterator();
			while(iterator.hasNext())
			{
				Codificacion aux = iterator.next();
				if(texto.charAt(i)==aux.simbolo)
				{
					mensajeComprimido+= aux.codigo;
				}
			}
		}
		return mensajeComprimido;
	}
	
	private Stack<Nodo> ordenarPila(Stack<Nodo> pila)
	{
		Stack<Nodo> nuevaPila = new Stack<Nodo>();
		int tamaño = pila.size();
		Nodo[] nodos = new Nodo[tamaño];
		for(int i=0;i<tamaño;i++)
		{
			nodos[i]=pila.pop();
		}
		bubbleSort(nodos);
		for(int i=tamaño-1;i>=0;i--)
		{
			pila.push(nodos[i]);
		}
		return nuevaPila;
	}
	
	
	private void bubbleSort(Nodo[] arr) {  
        int n = arr.length;  
        Nodo temp;  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(arr[j-1].valor.probabilidad > arr[j].valor.probabilidad){  
                                 //swap elements  
                                 temp = arr[j-1];  
                                 arr[j-1] = arr[j];
                                 arr[j] = temp;  
                         }  
                          
                 }  
         }
	}
	
	private void generarCodigosArbol(Nodo nodo,String codigo,ArrayList<Codificacion> codificacion)
	{
		if(nodo!=null)
		{
			if(!nodo.valor.simbolo.equals(""))
			{
				Codificacion aux = new Codificacion();
				aux.codigo=codigo;aux.probabilidad=nodo.valor.probabilidad;aux.simbolo=nodo.valor.simbolo.charAt(0);
				codificacion.add(aux);
				
			}
			else {
				generarCodigosArbol(nodo.izq, codigo+"0",codificacion);
				generarCodigosArbol(nodo.der, codigo+"1",codificacion);
			}
		}
	}
	

}
