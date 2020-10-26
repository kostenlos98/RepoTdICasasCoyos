package interfaces;

import java.awt.event.ActionListener;

import presentacion.VDialogNuevoArchivo;
import presentacion.VentanaCalcPCod;
import presentacion.VentanaPCod;

public interface IVentanaPPalPCod {
	void addActionListener(ActionListener actionListener);
	VentanaCalcPCod getVentanaCalcPCod();
	VentanaPCod getVentanaPCod();
	VDialogNuevoArchivo getDialogNArch();
	void lanzarCartelError(String err);
}
