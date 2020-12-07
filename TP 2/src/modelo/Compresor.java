package modelo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Compresor {
	private GestorArchs gestorArchs;
	private String textoSeleccionado;
	private String nombreArchivoSeleccionado;
	private ArrayList<Simbolo> probabilidadesTextoSeleccionado;
	
	
	public Compresor() {
		super();
		this.probabilidadesTextoSeleccionado = new ArrayList<Simbolo>();
	}


	public ArrayList<Simbolo> calcularProbabilidadesTexto(){
		 ArrayList<Simbolo> retorno = new ArrayList<Simbolo>();
		
		return retorno;
	}
	
	
	public void cargarTextoSeleccionado() throws FileNotFoundException {
		this.setTextoSeleccionado(this.gestorArchs.get_instancia().getTextoArch(nombreArchivoSeleccionado));
	}

	public Double calcularLongitudMedia() {
		return 0.0;
	}
	
	public Double calcularRedundancia() {
		return 0.0;
	}
	
	public Double calcularRendimiento() {
		return 0.0;
	}
	
	public Double calcularTasaCompresion() {
		return 0.0;
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
