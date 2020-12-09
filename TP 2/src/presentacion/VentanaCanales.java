package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import interfaces.IVentanaCanales;
import interfaces.IVentanaCompresor;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaCanales implements IVentanaCanales {

	private JFrame frame;
	private JTextArea textAreaProbs = new JTextArea();
	private JTextArea textAreaMatrizCanal = new JTextArea();
	private JButton btnSimular = new JButton("CALCULAR");
	private JTextArea textAreaResultados = new JTextArea();
	private JTextArea textAreaN = new JTextArea();
	private JTextArea textAreaM = new JTextArea();
	private DefaultListModel<String> listaModeloArchivos = new DefaultListModel<String>();
	
	/**
	 * Create the application.
	 */
	public VentanaCanales() {
		initialize();
		this.btnSimular.setActionCommand("SIMULAR");
		
		JTextPane txtpnMatrizDelCanal = new JTextPane();
		txtpnMatrizDelCanal.setText("Matriz del canal");
		txtpnMatrizDelCanal.setForeground(Color.WHITE);
		txtpnMatrizDelCanal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnMatrizDelCanal.setBackground(Color.BLACK);
		txtpnMatrizDelCanal.setBounds(236, 94, 203, 19);
		frame.getContentPane().add(txtpnMatrizDelCanal);
		textAreaProbs.setBackground(Color.WHITE);
		textAreaProbs.setBounds(23, 123, 189, 91);
		frame.getContentPane().add(textAreaProbs);
		textAreaMatrizCanal.setBackground(Color.WHITE);
		textAreaMatrizCanal.setBounds(236, 123, 189, 91);
		frame.getContentPane().add(textAreaMatrizCanal);
		
		JTextPane txtpnN = new JTextPane();
		txtpnN.setText("N");
		txtpnN.setForeground(Color.WHITE);
		txtpnN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnN.setBackground(Color.BLACK);
		txtpnN.setBounds(446, 129, 23, 19);
		frame.getContentPane().add(txtpnN);
		
		JTextPane txtpnM = new JTextPane();
		txtpnM.setText("M");
		txtpnM.setForeground(Color.WHITE);
		txtpnM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnM.setBackground(Color.BLACK);
		txtpnM.setBounds(446, 169, 23, 19);
		frame.getContentPane().add(txtpnM);
		textAreaN.setBackground(Color.WHITE);
		textAreaN.setBounds(468, 129, 48, 30);
		frame.getContentPane().add(textAreaN);
		textAreaM.setBackground(Color.WHITE);
		textAreaM.setBounds(468, 169, 48, 30);
		frame.getContentPane().add(textAreaM);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 588, 621);
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
		txtpnTitulo.setText("CANALES DE COMUNICACION");
		txtpnTitulo.setBounds(10, 10, 425, 39);
		panel.add(txtpnTitulo);
		
		JTextPane txtpnArchivosDeDatos = new JTextPane();
		txtpnArchivosDeDatos.setBackground(Color.BLACK);
		txtpnArchivosDeDatos.setForeground(Color.WHITE);
		txtpnArchivosDeDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnArchivosDeDatos.setText("Probabilidades a priori");
		txtpnArchivosDeDatos.setBounds(23, 94, 203, 19);
		frame.getContentPane().add(txtpnArchivosDeDatos);
		
		JTextPane txtpnResultados = new JTextPane();
		txtpnResultados.setEditable(false);
		txtpnResultados.setText("Resultados\r\n");
		txtpnResultados.setForeground(Color.WHITE);
		txtpnResultados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnResultados.setBackground(Color.BLACK);
		txtpnResultados.setBounds(23, 238, 77, 19);
		frame.getContentPane().add(txtpnResultados);
		textAreaResultados.setEditable(false);
		
		
		textAreaResultados.setBackground(new Color(255, 255, 255));
		textAreaResultados.setBounds(23, 283, 483, 223);
		frame.getContentPane().add(textAreaResultados);
		
		btnSimular.setActionCommand("CALCULAR");
		btnSimular.setForeground(new Color(255, 0, 102));
		btnSimular.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSimular.setBounds(432, 532, 132, 30);
		frame.getContentPane().add(btnSimular);
	}

	public JFrame getFrame() {
		return frame;
	}
	
    public JButton getBtnSimular() {
		return btnSimular;
	}

    @Override
    public void addActionListener(ActionListener actionListener) {
        this.getBtnSimular().addActionListener(actionListener);
    }

  

    public JTextArea getTextAreaResultados() {
		return textAreaResultados;
	}

	public void lanzarCartelError(String err) {
        JOptionPane.showMessageDialog(null, err);
    }

	public JTextArea getTextAreaProbs() {
		return textAreaProbs;
	}

	public JTextArea getTextAreaMatrizCanal() {
		return textAreaMatrizCanal;
	}

	public JTextArea getTextAreaN() {
		return textAreaN;
	}

	public JTextArea getTextAreaM() {
		return textAreaM;
	}
	
	
	
	
}
