package inicio;

import java.awt.EventQueue;

import negocio.ControladorVPCod;
import negocio.ControladorVPPal;
import presentacion.VentanaPPal;
import presentacion.VentanaPPalPCod;

public class InicioPCod {

	public static void main(String[] args) {
		VentanaPPalPCod window = new VentanaPPalPCod();
		ControladorVPCod controlador = new ControladorVPCod(window);
		window.getFrame().setVisible(true); 
	}

}
