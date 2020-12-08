package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import modelo.GenerarHuffman.Codificacion;

public class Compresor {
	private GestorArchs gestorArchs;
	private String textoSeleccionado;
	private String nombreArchivoSeleccionado;
	private Simbolo[] probabilidadesTextoSeleccionado;
	
	
	public Compresor() {
		super();
	}


	public Simbolo[] calcularProbabilidadesTexto(){
		HashMap<String,Integer> hashmapCants = new HashMap<String,Integer>();
		ArrayList<Simbolo> retorno = new ArrayList<Simbolo>();
		int i = 0;
		int cantidadTotalSimbolos = 0;
    	String simboloActual;
    	textoSeleccionado = textoSeleccionado.replaceAll("\\r\\n", "");
    	while(i < textoSeleccionado.length()) {
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
              System.out.println("simbolo: "+simboloAct.simbolo+" prob: "+simboloAct.probabilidad);
              retorno.add(simboloAct);
        }
		return retorno.toArray(new Simbolo[retorno.size()]);
	}
	
	/*
	public double redundanciaSF() {
		GenerarRLC generador = new GenerarRLC();	
	}
	*/
	
	public double redundanciaH() {
		GenerarHuffman generador = new GenerarHuffman();
		return generador.redundancia(probabilidadesTextoSeleccionado);
	}
		
	public double compresionHuffman() {
		String retorno = "";
		GenerarHuffman generador = new GenerarHuffman();
		retorno = generador.textoComprimido(textoSeleccionado, probabilidadesTextoSeleccionado);
		gestorArchs.get_instancia().nuevoArchivo("HUFFMAN");
		gestorArchs.get_instancia().actualizarArchivo("HUFFMAN.txt", retorno);
		return generador.tasaCompresion(this.textoSeleccionado, retorno);
	}
	
	
	/*
	public void compresionSF() {
        GenerarSF sf = new GenerarSF();
        sf.generarSF(probabilidadesTextoSeleccionado);
        HashMap<Character,String> tablaCod = sf.getTablaCod();
        this.generarComprimido(this.nombreArchivoSeleccionado,tablaCod);
	}
	*/
	
	
	public double compresionRLC() {
		String retorno = "";
		GenerarRLC generador = new GenerarRLC();
		retorno = generador.generarRLC(this.getTextoSeleccionado());
		System.out.println("test "+nombreArchivoSeleccionado);
		gestorArchs.get_instancia().nuevoArchivo("RLC");
		gestorArchs.get_instancia().actualizarArchivo("RLC.txt", retorno);
		double lengthOriginal = getTextoSeleccionado().length();
		double lengthComprimido = retorno.length();
		double TC = lengthOriginal/lengthComprimido;
		return TC;
	}
	
	
    private void generarComprimido(String nombreArch, HashMap<Character,String> tablaCod)
    {
        BitOutputStream bos;
        BufferedReader lector;
        String linea = null;
        int i;
        bos = new BitOutputStream();
        try
        {
            lector = new BufferedReader(new InputStreamReader(new FileInputStream(new File(".\\datos\\" + nombreArch)),"UTF8"));
            linea = lector.readLine();
            while (linea != null)
            {
                for (i = 0; i < linea.length(); i++)
                    bos.grabarBits(tablaCod.get(linea.charAt(i)));
                bos.grabarBits(tablaCod.get('\n'));
                linea = lector.readLine();   
            }
            bos.grabarBits(tablaCod.get((char)3)); /* codifica como ultimo char al ETX */
            lector.close();
            bos.cerrar();
            FileOutputStream output = new FileOutputStream(new File(".\\datos\\" + nombreArch +"-RLC"),true);
            output.write(bos.getCodificacion());
            output.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
	

	public void cargarTextoSeleccionado() throws FileNotFoundException {
		this.setTextoSeleccionado(this.gestorArchs.get_instancia().getTextoArch(nombreArchivoSeleccionado));
		this.setProbabilidadesTextoSeleccionado(calcularProbabilidadesTexto());
	}

	
	
	
	
	public Simbolo[] getProbabilidadesTextoSeleccionado() {
		return probabilidadesTextoSeleccionado;
	}


	public void setProbabilidadesTextoSeleccionado(Simbolo[] probabilidadesTextoSeleccionado) {
		this.probabilidadesTextoSeleccionado = probabilidadesTextoSeleccionado;
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


	public GestorArchs getGestorArchs() {
		return gestorArchs;
	}
	
	
}
