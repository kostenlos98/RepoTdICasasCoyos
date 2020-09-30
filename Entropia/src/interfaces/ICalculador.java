package interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import base.Estado;
import excepciones.CantidadIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;

public interface ICalculador {
	double calcularProb();
	ArrayList<Double> calcularCantidadInfo(HashMap<String, Double> probs);
	double calcularEntropia(HashMap<String, Double> probs);
	void hacerSimulacion(String rutaArch, ArrayList<Estado> estados, int N) throws CantidadIncorrectaException, SumaIncorrectaException, ValorIncorrectoException;
	void nuevoArchivo(String nombre);
	ArrayList<String> listarArchivos();
}
