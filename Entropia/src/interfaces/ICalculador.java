package interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import excepciones.CantidadIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;

public interface ICalculador {
	double calcularProb();
	void hacerSimulacion(String rutaArch, HashMap<String, Double> probs, int N) throws CantidadIncorrectaException, SumaIncorrectaException, ValorIncorrectoException;
}
