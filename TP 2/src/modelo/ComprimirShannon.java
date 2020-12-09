package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ComprimirShannon {
	
	LinkedHashMap<Character, Double> freq;
	double redundancia;
	double tasaCompresion;
	
	public String generarMensaje(String mensaje, Simbolo probSimbolos[]) {
		LinkedHashMap<Character, Double> freq = new LinkedHashMap<>();
		bubbleSort(probSimbolos);
		for(int i=0;i<probSimbolos.length;i++)
		{
			freq.put(probSimbolos[i].simbolo.charAt(0), probSimbolos[i].probabilidad);
			
		}
		HashMap<Character, String> codigo = comprimirShannon(freq);
		
		String mensajeComprimido = comprimirMensaje(codigo, mensaje);
		calculos(codigo, freq, mensaje, mensajeComprimido);
		return mensajeComprimido;
	}

	private void calculos(HashMap<Character, String> codigo, LinkedHashMap<Character, Double> freq, String mensaje,
			String mensajeComprimido) {
		double entropia = entropia(freq);
		double longitudMedia = longitudMedia(codigo, freq);
		redundancia = entropia / longitudMedia;
		double longitudOriginal = mensaje.length()*8;
		double longitudComprimido =mensajeComprimido.length();
		tasaCompresion = longitudOriginal/longitudComprimido;
	}

	private double entropia(HashMap<Character, Double> freq) {
		double aux = 0;
		for (double prob : freq.values()) {
			aux += prob * Math.log(1 / prob) / Math.log(2);
		}
		return aux;
	}

	private double longitudMedia(HashMap<Character, String> codigo, LinkedHashMap<Character, Double> freq) {
		double aux = 0;
		for (char c : codigo.keySet()) {
			aux += freq.get(c).doubleValue() * codigo.get(c).length();
		}
		return aux;
	}

	private HashMap<Character, String> comprimirShannon(LinkedHashMap<Character, Double> freq) {

		HashMap<Character, String> result = new HashMap<Character, String>();
		List<Character> charList = new ArrayList<Character>();
		this.freq = freq;
		Iterator entries = freq.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<Character, Double> entry = (Map.Entry) entries.next();
			charList.add(entry.getKey());
		}

		generarShannon(result, charList, true);
		return result;
	}

	private String comprimirMensaje(HashMap<Character, String> codigo, String mensaje) {
		String mensajeComprimido = "";
		for (int i = 0; i < mensaje.length(); i++) {
			mensajeComprimido += codigo.get(mensaje.charAt(i));
		}
		return mensajeComprimido;

	}

	private void generarShannon(HashMap<Character, String> resultado, List<Character> charList, boolean flag) {
		String bit = "";
		if (!resultado.isEmpty()) {
			if (flag) {
				bit = "0";
			} else {
				bit = "1";
			}
		}

		for (Character c : charList) {
			String s;
			if (resultado.get(c) == null) {
				s = "";
			} else {
				s = resultado.get(c);
			}
			resultado.put(c, s + bit);
		}

		if (charList.size() >= 2) {
			int separador = separador(charList);
			List<Character> listaArriba = charList.subList(0, separador);
			generarShannon(resultado, listaArriba, true);
			List<Character> listaAbajo = charList.subList(separador, charList.size());
			generarShannon(resultado, listaAbajo, false);
		}
	}

	private int separador(List<Character> charList) {
		int tamaño = charList.size();
		double totalArriba, totalAbajo,diferencia,minimo=100;
		int aux=0;
		for (int i = 1; i < tamaño; i++) {
			totalArriba = sumaSublista(charList, 0, i);
			totalAbajo = sumaSublista(charList, i, charList.size());
			diferencia = totalArriba-totalAbajo;
			if(Math.abs(diferencia)<minimo)
			{
				minimo=Math.abs(diferencia);
				aux=i;
			}
		}
		return aux;
	}

	private double sumaSublista(List<Character> subLista, int inf, int sup) {
		double acum = 0;
		for (int i = inf; i < sup; i++) {
			double aux = freq.get(subLista.get(i)).floatValue();
			acum += aux;
		}
		return acum;
	}

	private void bubbleSort(Simbolo[] arr) {
		int n = arr.length;
		Simbolo temp;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1].probabilidad < arr[j].probabilidad) {
					// swap elements
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}

			}
		}

	}

	public double getRedundancia() {
		return redundancia;
	}

	public double getTasaCompresion() {
		return tasaCompresion;
	}
}

