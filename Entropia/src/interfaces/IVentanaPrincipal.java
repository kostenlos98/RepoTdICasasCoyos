package interfaces;

import java.awt.event.ActionListener;

import presentacion.VDialogNuevoArchivo;
import presentacion.VentanaCalculos;
import presentacion.VentanaMarkov;
import presentacion.VentanaSimulacion;

public interface IVentanaPrincipal {
	void addActionListener(ActionListener actionListener);
	VentanaSimulacion getVentanaSimulacion();
	VDialogNuevoArchivo getDialogNArch();
	void lanzarCartelError(String err);
	VentanaCalculos getVentanaCalculos();
	VentanaMarkov getVentanaMarkov();
}
