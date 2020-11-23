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
	
	
	public ArrayList<Codificacion> generarHuffman(Simbolo[] simbolos)
	{
		Stack<Nodo> pila = new Stack<Nodo>();
		for(int i=0;i<simbolos.length;i++)
		{
			Nodo nodo = new Nodo(simbolos[i]);
			pila.push(nodo);
		}
		ordenarPila(pila);
		int tama�o=pila.size();
		while(tama�o>1)
		{
			Nodo nodo1,nodo2;
			nodo1=pila.pop();
			nodo2=pila.pop();
			Nodo nodoNuevo = new Nodo(new Simbolo(nodo1.valor.probabilidad+nodo2.valor.probabilidad, ""));
			nodoNuevo.izq = nodo1;
			nodoNuevo.der = nodo2;
			pila.push(nodoNuevo);
			ordenarPila(pila);
			tama�o=pila.size();
		}
		ArrayList<Codificacion> codificacion = new ArrayList<Codificacion>();
		generarCodigosArbol(pila.pop(), "",codificacion);
		return codificacion;
		
	}
	
	public String comprimirHuffman(ArrayList<Codificacion> codificacion,String texto)
	{
		int tama�oTexto = texto.length();
		String mensajeComprimido="";
		Iterator<Codificacion> iterator;
		for(int i=0;i<tama�oTexto;i++)
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
		int tama�o = pila.size();
		Nodo[] nodos = new Nodo[tama�o];
		for(int i=0;i<tama�o;i++)
		{
			nodos[i]=pila.pop();
		}
		bubbleSort(nodos);
		for(int i=tama�o-1;i>=0;i--)
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
				System.out.println(nodo.valor.simbolo+" "+codigo);
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
