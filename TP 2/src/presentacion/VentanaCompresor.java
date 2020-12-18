package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

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

public class VentanaCompresor implements IVentanaCompresor {

	private JFrame frame;
	private JButton btnCalcular = new JButton("CALCULAR");
	private JTextArea textAreaResultados = new JTextArea();
	private DefaultListModel<String> listaModeloArchivos = new DefaultListModel<String>();
	private JList listArchivos = new JList();
	
	/**
	 * Create the application.
	 */
	public VentanaCompresor() {
		initialize();
		this.btnCalcular.setActionCommand("CALCULAR");
		listArchivos.setModel(listaModeloArchivos);
		listArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArchivos.setBounds(23, 123, 392, 91);
		frame.getContentPane().add(listArchivos);
		
		JScrollPane scrollPane = new JScrollPane(textAreaResultados);
		scrollPane.setBounds(23, 278, 501, 225);
		frame.getContentPane().add(scrollPane);
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
		txtpnTitulo.setText("COMPRESOR");
		txtpnTitulo.setBounds(10, 10, 132, 39);
		panel.add(txtpnTitulo);
		
		JTextPane txtpnArchivosDeDatos = new JTextPane();
		txtpnArchivosDeDatos.setBackground(Color.BLACK);
		txtpnArchivosDeDatos.setForeground(Color.WHITE);
		txtpnArchivosDeDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnArchivosDeDatos.setText("Archivos de datos disponibles");
		txtpnArchivosDeDatos.setBounds(23, 94, 203, 19);
		frame.getContentPane().add(txtpnArchivosDeDatos);
		
		JTextPane txtpnResultados = new JTextPane();
		txtpnResultados.setEditable(false);
		txtpnResultados.setText("Resultados\r\n");
		txtpnResultados.setForeground(Color.WHITE);
		txtpnResultados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnResultados.setBackground(Color.BLACK);
		txtpnResultados.setBounds(23, 226, 371, 19);
		frame.getContentPane().add(txtpnResultados);
		textAreaResultados.setEditable(false);
		
		
		textAreaResultados.setBackground(new Color(255, 255, 255));
		textAreaResultados.setBounds(23, 278, 483, 227);
		frame.getContentPane().add(textAreaResultados);
		
		btnCalcular.setActionCommand("CALCULAR");
		btnCalcular.setForeground(new Color(255, 0, 102));
		btnCalcular.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnCalcular.setBounds(432, 532, 132, 30);
		frame.getContentPane().add(btnCalcular);
	}

	public JFrame getFrame() {
		return frame;
	}
	
    public JButton getBtnCalcular() {
		return btnCalcular;
	}

    
    
	public JTextArea getTextAreaResultados() {
		return textAreaResultados;
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
	
	@Override
    public void addActionListener(ActionListener actionListener) {
        this.getBtnCalcular().addActionListener(actionListener);
    }

	
	public String getNombreSeleccionado()
	{
		return (String) this.listArchivos.getSelectedValue().toString();
	}	
	
    public void lanzarCartelError(String err) {
        JOptionPane.showMessageDialog(null, err);
    }
}
