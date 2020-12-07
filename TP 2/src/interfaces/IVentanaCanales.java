package interfaces;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;

public interface IVentanaCanales {
	void addActionListener(ActionListener actionListener);
	void lanzarCartelError(String err);
}
