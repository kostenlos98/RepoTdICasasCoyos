package inicio;

import java.awt.EventQueue;

import controlador.ControladorCanales;
import presentacion.VentanaCanales;

public class InicioCanalesComunicacion {

	public static void main(String[] args) {
		VentanaCanales window = new VentanaCanales();
		ControladorCanales controlador = new ControladorCanales(window);
		window.getFrame().setVisible(true); 
	}

}
