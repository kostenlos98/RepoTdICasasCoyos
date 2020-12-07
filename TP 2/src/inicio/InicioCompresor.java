package inicio;

import java.awt.EventQueue;

import controlador.ControladorCompresor;
import presentacion.VentanaCompresor;

public class InicioCompresor {

	public static void main(String[] args) {
		VentanaCompresor window = new VentanaCompresor();
		ControladorCompresor controlador = new ControladorCompresor(window);
		window.getFrame().setVisible(true); 
	}

}
