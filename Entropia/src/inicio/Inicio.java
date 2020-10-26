package inicio;

import java.awt.EventQueue;

import negocio.ControladorVPPal;
import presentacion.VentanaPPal;

public class Inicio {

	public static void main(String[] args) {
		VentanaPPal window = new VentanaPPal();
		ControladorVPPal controlador = new ControladorVPPal(window);
		window.getFrame().setVisible(true); 
	}

}
