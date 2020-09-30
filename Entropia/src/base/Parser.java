package base;

import java.awt.FontFormatException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import base.Estado;

import interfaces.IParser;

public class Parser implements IParser{
	
	private HashMap<String, Double> hashMapActual;
	private ArrayList<Estado> estados;
	private static Parser _instancia;
	
	private Parser() {
		super();
	}
	
	public void parsearTexto(String texto) throws NumberFormatException {
		hashMapActual = new HashMap<String, Double>();
		estados = new ArrayList<Estado>();
		String[] lineas, tokens_act;
		String linea_act;
		lineas = texto.split("\n");
		for(int i=0; i<lineas.length; i++) {
			linea_act = lineas[i];
			tokens_act = linea_act.split(" ");
			hashMapActual.put(tokens_act[0], Double.valueOf(tokens_act[1]));
			estados.add(new Estado(tokens_act[0],Double.valueOf(tokens_act[1])));
		}
		
	}
	
	public HashMap<String, Double> getHashMapActual() {
		return hashMapActual;
	}

	public void setHashMapActual(HashMap<String, Double> hashMapActual) {
		this.hashMapActual = hashMapActual;
	}

	public static Parser get_instancia() {
		if(_instancia == null)
			_instancia = new Parser();
		return _instancia;
	}

	public ArrayList<Estado> getEstados() {
		return estados;
	}
	
	
	
	
}
