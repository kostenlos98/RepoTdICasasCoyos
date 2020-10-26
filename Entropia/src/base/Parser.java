package base;

import java.util.HashMap;

import excepciones.MatrizIncorrectaException;
import interfaces.IParser;

public class Parser implements IParser{
	
	private HashMap<String, Double> hashMapActual;
	private double matriz[][];
	private static Parser _instancia;
	
	private Parser() {
		super();
	}
	
	public void parsearTexto(String texto) throws NumberFormatException {
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
	
	public void parsearMatriz(String texto, int N) throws NumberFormatException, MatrizIncorrectaException
	{
		String lineas[];
		String lineaAct[];
		this.matriz = new double[N][N];
		lineas = texto.split("\n");
		if(lineas.length!=N)
			throw new MatrizIncorrectaException();
		double dato;
		for(int i=0;i<N;i++)
		{
				
			lineaAct=lineas[i].split(" ");
			if(lineaAct.length!=N)
				throw new MatrizIncorrectaException();
			for(int j=0;j<N;j++)
			{
				dato = Double.parseDouble(lineaAct[j]);
				matriz[i][j]= dato;
			}
		}	
	}
	
	public String[][] obtenerMatrizPCod(String texto) {
		String lineas[];
		String lineaAct[];
		lineas = texto.split("\n");
		int N = lineas.length;
		String retorno[][] = new String[N][3];
		for(int i=0;i<N;i++)
		{
			lineaAct=lineas[i].split(" ");
			for(int j=0;j<3;j++)
			{
				retorno[i][j]= lineaAct[j];
			}
		}
		return retorno;
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

	public double[][] getMatriz() {
		return matriz;
	}
	
	
	
	
}
