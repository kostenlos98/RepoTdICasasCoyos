package interfaces;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTextArea;

public interface IVentanaCompresor {
	void addActionListener(ActionListener actionListener);
	void lanzarCartelError(String err);
	void refrescarLista(ArrayList<String> listarArchivos);
	JList getListArchivos();
	JTextArea getTextAreaResultados();
	String getNombreSeleccionado();
}
