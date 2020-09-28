package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class VentanaSimulacion {

	private JFrame frame;
	private JTextField textFieldN;

	/**
	 * Create the application.
	 */
	public VentanaSimulacion() {
		initialize();
		
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 598, 585);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 10, 580, 59);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnTitulo = new JTextPane();
		txtpnTitulo.setForeground(Color.WHITE);
		txtpnTitulo.setBackground(Color.BLUE);
		txtpnTitulo.setEditable(false);
		txtpnTitulo.setFont(new Font("Nirmala UI", Font.BOLD, 18));
		txtpnTitulo.setText("SIMULACION");
		txtpnTitulo.setBounds(10, 10, 132, 39);
		panel.add(txtpnTitulo);
		
		JTextPane txtpnArchivosDeDatos = new JTextPane();
		txtpnArchivosDeDatos.setBackground(Color.BLACK);
		txtpnArchivosDeDatos.setForeground(Color.WHITE);
		txtpnArchivosDeDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnArchivosDeDatos.setText("Archivos de datos disponibles");
		txtpnArchivosDeDatos.setBounds(23, 94, 203, 19);
		frame.getContentPane().add(txtpnArchivosDeDatos);
		
		JTextArea textAreaArchivos = new JTextArea();
		textAreaArchivos.setBackground(new Color(255, 255, 255));
		textAreaArchivos.setBounds(23, 123, 404, 76);
		frame.getContentPane().add(textAreaArchivos);
		
		JTextPane txtpnDistribucionDeProbabilidad = new JTextPane();
		txtpnDistribucionDeProbabilidad.setText("Distribucion de probabilidad");
		txtpnDistribucionDeProbabilidad.setForeground(Color.WHITE);
		txtpnDistribucionDeProbabilidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnDistribucionDeProbabilidad.setBackground(Color.BLACK);
		txtpnDistribucionDeProbabilidad.setBounds(23, 226, 203, 19);
		frame.getContentPane().add(txtpnDistribucionDeProbabilidad);
		
		JTextArea textAreaDistribucionProb = new JTextArea();
		textAreaDistribucionProb.setBackground(new Color(255, 255, 255));
		textAreaDistribucionProb.setBounds(23, 255, 547, 156);
		frame.getContentPane().add(textAreaDistribucionProb);
		
		JTextPane txtpnNcantidadDe = new JTextPane();
		txtpnNcantidadDe.setText("N (cantidad de simbolos)");
		txtpnNcantidadDe.setForeground(Color.WHITE);
		txtpnNcantidadDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnNcantidadDe.setBackground(Color.BLACK);
		txtpnNcantidadDe.setBounds(23, 454, 203, 19);
		frame.getContentPane().add(txtpnNcantidadDe);
		
		textFieldN = new JTextField();
		textFieldN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldN.setBounds(23, 483, 96, 30);
		frame.getContentPane().add(textFieldN);
		textFieldN.setColumns(10);
		
		JButton btnSimular = new JButton("SIMULAR\r\n");
		btnSimular.setForeground(new Color(255, 0, 102));
		btnSimular.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSimular.setBounds(362, 481, 132, 30);
		frame.getContentPane().add(btnSimular);
		
		JButton btnNuevo = new JButton("Crear archivo");
		btnNuevo.setForeground(new Color(0, 0, 0));
		btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNuevo.setBounds(437, 125, 132, 30);
		frame.getContentPane().add(btnNuevo);
	}

	public JFrame getFrame() {
		return frame;
	}
}
