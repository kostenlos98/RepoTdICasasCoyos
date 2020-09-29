package interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import excepciones.CantidadIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;

public interface ICalculador {
	double calcularProb();
	ArrayList<Double> calcularCantidadInfo(HashMap<String, Double> probs);
	double calcularEntropia(HashMap<String, Double> probs);
	void hacerSimulacion(String rutaArch, HashMap<String, Double> probs, int N) throws CantidadIncorrectaException, SumaIncorrectaException, ValorIncorrectoException;
	void nuevoArchivo(String nombre);
	ArrayList<String> listarArchivos();
}
