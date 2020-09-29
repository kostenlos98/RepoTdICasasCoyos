package inicio;

import java.awt.EventQueue;

import negocio.Controlador;
import presentacion.VentanaPPal;

public class Inicio {

	public static void main(String[] args) {
		VentanaPPal window = new VentanaPPal();
		Controlador controlador = new Controlador(window);
		window.getFrame().setVisible(true); 
	}

}
