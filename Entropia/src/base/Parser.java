package base;

import java.util.HashMap;

import interfaces.IParser;

public class Parser implements IParser{
	
	private HashMap<String, Double> hashMapActual;
	private static Parser _instancia;
	
	private Parser() {
		super();
	}
	
	public void parsearTexto(String texto) {
		hashMapActual = new HashMap<String, Double>();
		String[] lineas, tokens_act;
		String linea_act;
		lineas = texto.split("\n");
		for(int i=0; i<lineas.length; i++) {
			linea_act = lineas[i];
			tokens_act = linea_act.split(" ");
			hashMapActual.put(tokens_act[0], Double.valueOf(tokens_act[1]));
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
	
	
	
}
