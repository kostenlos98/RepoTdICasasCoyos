package interfaces;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTextArea;

public interface IVentanaCanales {
	void addActionListener(ActionListener actionListener);
	JTextArea getTextAreaResultados();
	JTextArea getTextAreaProbs();
	JTextArea getTextAreaMatrizCanal();
	JTextArea getTextAreaN();
	JTextArea getTextAreaM();
	void lanzarCartelError(String err);
}
