package interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICalculador {
	double calcularProb();
	double calcularCantidadInfo();
	double calcularEntropia();
	void hacerSimulacion(String rutaArch, HashMap<String, Double> probs, int N);
	void nuevoArchivo(String nombre);
	ArrayList<String> listarArchivos();
}
