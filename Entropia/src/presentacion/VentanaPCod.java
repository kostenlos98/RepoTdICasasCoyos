package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPCod {

	private JFrame frame;
	private JTextField textFieldN = new JTextField();;
	private JButton btnGeneracion = new JButton("GENERAR");
	private JButton btnNuevo = new JButton("Crear archivo");
	private JTextArea textAreaDistribucionProb = new JTextArea();
	private DefaultListModel<String> listaModeloArchivos = new DefaultListModel<String>();
	private JList listArchivos = new JList();
	
	/**
	 * Create the application.
	 */
	public VentanaPCod() {
		initialize();
		this.btnGeneracion.setActionCommand("GENERAR2");
		this.btnNuevo.setActionCommand("CREAR ARCH");
		
		listArchivos.setModel(listaModeloArchivos);
		listArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArchivos.setBounds(23, 123, 392, 91);
		frame.getContentPane().add(listArchivos);
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
		txtpnTitulo.setText("GENERACION DE CODIFICACIONES");
		txtpnTitulo.setBounds(10, 10, 343, 39);
		panel.add(txtpnTitulo);
		
		JTextPane txtpnArchivosDeDatos = new JTextPane();
		txtpnArchivosDeDatos.setBackground(Color.BLACK);
		txtpnArchivosDeDatos.setForeground(Color.WHITE);
		txtpnArchivosDeDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnArchivosDeDatos.setText("Archivos de datos disponibles");
		txtpnArchivosDeDatos.setBounds(23, 94, 203, 19);
		frame.getContentPane().add(txtpnArchivosDeDatos);
		
		JTextPane txtpnDistribucionDeProbabilidad = new JTextPane();
		txtpnDistribucionDeProbabilidad.setText("Distribucion de probabilidad");
		txtpnDistribucionDeProbabilidad.setForeground(Color.WHITE);
		txtpnDistribucionDeProbabilidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnDistribucionDeProbabilidad.setBackground(Color.BLACK);
		txtpnDistribucionDeProbabilidad.setBounds(23, 226, 203, 19);
		frame.getContentPane().add(txtpnDistribucionDeProbabilidad);
		
		
		textAreaDistribucionProb.setBackground(new Color(255, 255, 255));
		textAreaDistribucionProb.setBounds(23, 255, 547, 202);
		frame.getContentPane().add(textAreaDistribucionProb);
		
		btnGeneracion.setActionCommand("GENERAR2");
		btnGeneracion.setForeground(new Color(255, 0, 102));
		btnGeneracion.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnGeneracion.setBounds(362, 481, 132, 30);
		frame.getContentPane().add(btnGeneracion);
		
		btnNuevo.setActionCommand("CREAR ARCH");
		btnNuevo.setForeground(new Color(0, 0, 0));
		btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNuevo.setBounds(437, 125, 132, 30);
		frame.getContentPane().add(btnNuevo);
	}

	public JFrame getFrame() {
		return frame;
	}
	
    public JButton getBtnGeneracion() {
		return btnGeneracion;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public JTextField getTextFieldN() {
		return textFieldN;
	}

	public JTextArea getTextAreaDistribucionProb() {
		return textAreaDistribucionProb;
	}
	
	public void refrescarLista(ArrayList<String> listaArch) {
		this.getListaModeloArchivos().clear();
		Iterator it = listaArch.iterator();
		while(it.hasNext()) {
			this.getListaModeloArchivos().addElement((String) it.next());
		}
		
	}

	public DefaultListModel getListaModeloArchivos() {
		return listaModeloArchivos;
	}

	public void setListaModeloArchivos(DefaultListModel listaModeloArchivos) {
		this.listaModeloArchivos = listaModeloArchivos;
	}

	public JList getListArchivos() {
		return listArchivos;
	}

	public void setListArchivos(JList listArchivos) {
		this.listArchivos = listArchivos;
	}
	
	public String getNombreSeleccionado()
	{
		return (String) this.listArchivos.getSelectedValue().toString();
	}
	
}
