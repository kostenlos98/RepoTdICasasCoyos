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

public class VentanaCalculos {

	private JFrame frame;
	private JTextField textFieldEntropia = new JTextField();
	private JTextArea textAreaCantInfo = new JTextArea();
	private JButton btnCalcular = new JButton("CALCULAR");
	private DefaultListModel<String> listaModeloArchivos = new DefaultListModel<String>();
	private JList listArchivos = new JList();
	
	/**
	 * Create the application.
	 */
	public VentanaCalculos() {
		initialize();
		this.btnCalcular.setActionCommand("CALCULAR");
		
		listArchivos.setModel(listaModeloArchivos);
		listArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArchivos.setBounds(23, 123, 240, 91);
		frame.getContentPane().add(listArchivos);
		
		JTextPane txtpnCantTitle = new JTextPane();
		txtpnCantTitle.setText("Cantidad de informacion");
		txtpnCantTitle.setForeground(Color.WHITE);
		txtpnCantTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnCantTitle.setBackground(Color.BLACK);
		txtpnCantTitle.setBounds(236, 294, 203, 19);
		frame.getContentPane().add(txtpnCantTitle);
		textAreaCantInfo.setEditable(false);
		
		textAreaCantInfo.setBounds(237, 334, 203, 143);
		frame.getContentPane().add(textAreaCantInfo);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 496, 524);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 10, 482, 59);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnTitulo = new JTextPane();
		txtpnTitulo.setForeground(Color.WHITE);
		txtpnTitulo.setBackground(Color.BLUE);
		txtpnTitulo.setEditable(false);
		txtpnTitulo.setFont(new Font("Nirmala UI", Font.BOLD, 18));
		txtpnTitulo.setText("CALCULOS");
		txtpnTitulo.setBounds(10, 10, 132, 39);
		panel.add(txtpnTitulo);
		
		JTextPane txtpnArchivosDeDatos = new JTextPane();
		txtpnArchivosDeDatos.setBackground(Color.BLACK);
		txtpnArchivosDeDatos.setForeground(Color.WHITE);
		txtpnArchivosDeDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnArchivosDeDatos.setText("Archivos de datos disponibles");
		txtpnArchivosDeDatos.setBounds(23, 94, 203, 19);
		frame.getContentPane().add(txtpnArchivosDeDatos);
		
		JTextPane txtpnResultadosTitle = new JTextPane();
		txtpnResultadosTitle.setText("Resultados:");
		txtpnResultadosTitle.setForeground(Color.WHITE);
		txtpnResultadosTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnResultadosTitle.setBackground(Color.BLACK);
		txtpnResultadosTitle.setBounds(23, 252, 203, 19);
		frame.getContentPane().add(txtpnResultadosTitle);
		
		JTextPane txtpnEntTitle = new JTextPane();
		txtpnEntTitle.setText("Entropia");
		txtpnEntTitle.setForeground(Color.WHITE);
		txtpnEntTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnEntTitle.setBackground(Color.BLACK);
		txtpnEntTitle.setBounds(23, 294, 203, 19);
		frame.getContentPane().add(txtpnEntTitle);
		textFieldEntropia.setEditable(false);
		
		textFieldEntropia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEntropia.setBounds(23, 334, 96, 30);
		frame.getContentPane().add(textFieldEntropia);
		textFieldEntropia.setColumns(10);
		
		btnCalcular.setActionCommand("CALCULAR");
		btnCalcular.setForeground(new Color(255, 0, 102));
		btnCalcular.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnCalcular.setBounds(309, 149, 132, 30);
		frame.getContentPane().add(btnCalcular);
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

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextFieldEntropia() {
		return textFieldEntropia;
	}

	public JTextArea getTextAreaCantInfo() {
		return textAreaCantInfo;
	}

	public JButton getBtnCalcular() {
		return btnCalcular;
	}
	
	
}
