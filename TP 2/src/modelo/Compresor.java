package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import modelo.GenerarHuffman.Codificacion;

public class Compresor {
	private String textoSeleccionado;
	private String nombreArchivoSeleccionado;
	private Simbolo[] probabilidadesTextoSeleccionado;
	public double redudanciaSF;
	public double compresionSF;
	HashMap<Character, String> codigoAlfSF = new HashMap<Character, String>();
	HashMap<Character, String> codigoAlfHF = new HashMap<Character, String>();
	
	public Compresor() {
		super();
	}


	public Simbolo[] calcularProbabilidadesTexto(){
		HashMap<String,Integer> hashmapCants = new HashMap<String,Integer>();
		ArrayList<Simbolo> retorno = new ArrayList<Simbolo>();
		int i = 0;
		int cantidadTotalSimbolos = 0;
    	String simboloActual;
    	textoSeleccionado = textoSeleccionado.replaceAll("\\r", "");
    	while(i < textoSeleccionado.length()-1) {
    		simboloActual = String.valueOf(textoSeleccionado.charAt(i));
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
        Simbolo simboloAct;
        for (Map.Entry<String, Integer> entry : hashmapCants.entrySet()) {
              prob_act = (double) entry.getValue() / cantidadTotalSimbolos;
              simboloAct = new Simbolo(prob_act, entry.getKey());
              retorno.add(simboloAct);
        }
		return retorno.toArray(new Simbolo[retorno.size()]);
	}
	

	public double redundanciaH() {
		GenerarHuffman generador = new GenerarHuffman();
		return generador.redundancia(probabilidadesTextoSeleccionado);
	}
		
	public double compresionHuffman() {
		String retorno = "";
		GenerarHuffman generador = new GenerarHuffman();
		retorno = generador.textoComprimido(textoSeleccionado, probabilidadesTextoSeleccionado);
		String nombreArch = "HF-"+ this.nombreArchivoSeleccionado;
		nombreArch = nombreArch.substring(0, nombreArch.lastIndexOf('.'));
		GestorArchs.get_instancia().nuevoArchivo(nombreArch);
		GestorArchs.get_instancia().actualizarArchivo(nombreArch+ ".txt", retorno);
		this.codigoAlfHF = generador.getCodigoAlf();
		return generador.tasaCompresion(this.textoSeleccionado, retorno);
	}
	
	

	public void compresionSF() {
		ComprimirShannon sf = new ComprimirShannon();
		String textoComprimido = sf.generarMensaje(textoSeleccionado, probabilidadesTextoSeleccionado);
		String nombreArch = "SF-"+ this.nombreArchivoSeleccionado;
		nombreArch = nombreArch.substring(0, nombreArch.lastIndexOf('.'));
		GestorArchs.get_instancia().nuevoArchivo(nombreArch);
		GestorArchs.get_instancia().actualizarArchivo(nombreArch+ ".txt", textoComprimido);
		redudanciaSF = sf.getRedundancia();
		this.codigoAlfSF = sf.getCodigoAlf();
		compresionSF=sf.getTasaCompresion();
	}

	
	
	public double compresionRLC() {
		String retorno = "";
		GenerarRLC generador = new GenerarRLC();
		retorno = generador.generarRLC(this.getTextoSeleccionado());
		String nombreArch = "RLC-"+ this.nombreArchivoSeleccionado;
		nombreArch = nombreArch.substring(0, nombreArch.lastIndexOf('.'));
		GestorArchs.get_instancia().nuevoArchivo(nombreArch);
		GestorArchs.get_instancia().actualizarArchivo(nombreArch + ".txt", retorno);
		double lengthOriginal = getTextoSeleccionado().length();
		double lengthComprimido = retorno.length();
		double TC = lengthOriginal/lengthComprimido;
		return TC;
	}
	

	public void cargarTextoSeleccionado() throws FileNotFoundException {
		this.setTextoSeleccionado(GestorArchs.get_instancia().getTextoArch(nombreArchivoSeleccionado));
		this.setProbabilidadesTextoSeleccionado(calcularProbabilidadesTexto());
	}

	
	public Simbolo[] getProbabilidadesTextoSeleccionado() {
		return probabilidadesTextoSeleccionado;
	}
	
	public String getProbabilidadesFormato() {
		StringBuilder resultado = new StringBuilder();
		Simbolo simbAct;
		DecimalFormat numberFormat = new DecimalFormat("0.0000000");
		
		resultado.append("-------------------------------------"+'\n');
		resultado.append("Simbolo     |    Probabilidad   "+'\n');
		resultado.append("-------------------------------------"+'\n');
		for(int i=0;i < this.probabilidadesTextoSeleccionado.length ; i++) {
			simbAct = this.probabilidadesTextoSeleccionado[i];
			resultado.append(simbAct.simbolo + "    " + numberFormat.format(simbAct.probabilidad)+'\n'); //AGREGAR CASO CUANDO EL CHAR ES \n
		}
		resultado.append("-------------------------------------"+'\n');
		return resultado.toString();
	}

	public void setProbabilidadesTextoSeleccionado(Simbolo[] probabilidadesTextoSeleccionado) {
		this.probabilidadesTextoSeleccionado = probabilidadesTextoSeleccionado;
	}

	public String tablaCodAlfabeto(HashMap<Character, String> codigoAlfabeto) {
		StringBuilder resultado = new StringBuilder();
		Simbolo simbAct;
		resultado.append("-------------------------------------"+'\n');
		resultado.append("Codificaciones obtenidas             "+'\n');
		resultado.append("-------------------------------------"+'\n');
	    Iterator it = codigoAlfabeto.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	       	resultado.append(pair.getKey() + "    " + pair.getValue()+'\n');  //AGREGAR CASO CUANDO EL CHAR ES \n
	        
	    }
		resultado.append("-------------------------------------"+'\n');
		return resultado.toString();
	}
	
	public String getTextoSeleccionado() {
		return textoSeleccionado;
	}


	public void setTextoSeleccionado(String textoSeleccionado) {
		this.textoSeleccionado = textoSeleccionado;
	}


	public String getNombreArchivoSeleccionado() {
		return nombreArchivoSeleccionado;
	}


	public void setNombreArchivoSeleccionado(String nombreArchivoSeleccionado) {
		this.nombreArchivoSeleccionado = nombreArchivoSeleccionado;
	}


	public HashMap<Character, String> getCodigoAlfSF() {
		return codigoAlfSF;
	}


	public HashMap<Character, String> getCodigoAlfHF() {
		return codigoAlfHF;
	}

	
}
