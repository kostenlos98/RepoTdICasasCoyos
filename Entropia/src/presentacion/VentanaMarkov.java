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

public class VentanaMarkov {

	private JFrame frame;
	private JTextField textFieldN = new JTextField();;
	private JButton btnGenerar = new JButton("GENERAR\r\n");
	private JButton btnNuevo = new JButton("Crear archivo");
	private JTextArea textAreaMatrix = new JTextArea();
	private DefaultListModel<String> listaModeloArchivos = new DefaultListModel<String>();
	private JList listArchivos = new JList();
	private JTextField textFieldVectEst;
	private JTextField textFieldEntrp;
	
	/**
	 * Create the application.
	 */
	public VentanaMarkov() {
		initialize();
		this.btnGenerar.setActionCommand("GENERAR");
		this.btnNuevo.setActionCommand("CREAR ARCH");
		
		listArchivos.setModel(listaModeloArchivos);
		listArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArchivos.setBounds(23, 123, 392, 91);
		frame.getContentPane().add(listArchivos);
		
		JTextPane txtpnEntropia = new JTextPane();
		txtpnEntropia.setText("Entropia");
		txtpnEntropia.setForeground(Color.WHITE);
		txtpnEntropia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnEntropia.setBackground(Color.BLACK);
		txtpnEntropia.setBounds(368, 404, 138, 19);
		frame.getContentPane().add(txtpnEntropia);
		
		JTextPane txtpnVectorEstacionario = new JTextPane();
		txtpnVectorEstacionario.setText("Vector estacionario");
		txtpnVectorEstacionario.setForeground(Color.WHITE);
		txtpnVectorEstacionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnVectorEstacionario.setBackground(Color.BLACK);
		txtpnVectorEstacionario.setBounds(23, 476, 286, 19);
		frame.getContentPane().add(txtpnVectorEstacionario);
		
		textFieldVectEst = new JTextField();
		textFieldVectEst.setEditable(false);
		textFieldVectEst.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldVectEst.setColumns(10);
		textFieldVectEst.setBounds(23, 517, 300, 30);
		frame.getContentPane().add(textFieldVectEst);
		
		textFieldEntrp = new JTextField();
		textFieldEntrp.setEditable(false);
		textFieldEntrp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEntrp.setColumns(10);
		textFieldEntrp.setBounds(366, 445, 96, 30);
		frame.getContentPane().add(textFieldEntrp);
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
		txtpnTitulo.setText("MARKOV");
		txtpnTitulo.setBounds(10, 10, 132, 39);
		panel.add(txtpnTitulo);
		
		JTextPane txtpnArchivosDeDatos = new JTextPane();
		txtpnArchivosDeDatos.setBackground(Color.BLACK);
		txtpnArchivosDeDatos.setForeground(Color.WHITE);
		txtpnArchivosDeDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnArchivosDeDatos.setText("Archivos de datos disponibles");
		txtpnArchivosDeDatos.setBounds(23, 94, 203, 19);
		frame.getContentPane().add(txtpnArchivosDeDatos);
		
		JTextPane txtpnMatrizTrans = new JTextPane();
		txtpnMatrizTrans.setText("Matriz de transicion de estados");
		txtpnMatrizTrans.setForeground(Color.WHITE);
		txtpnMatrizTrans.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnMatrizTrans.setBackground(Color.BLACK);
		txtpnMatrizTrans.setBounds(23, 226, 203, 19);
		frame.getContentPane().add(txtpnMatrizTrans);
		
		
		textAreaMatrix.setBackground(new Color(255, 255, 255));
		textAreaMatrix.setBounds(23, 255, 483, 139);
		frame.getContentPane().add(textAreaMatrix);
		
		JTextPane txtpnNcantidadDe = new JTextPane();
		txtpnNcantidadDe.setText("N (cantidad de filas de la matriz)");
		txtpnNcantidadDe.setForeground(Color.WHITE);
		txtpnNcantidadDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnNcantidadDe.setBackground(Color.BLACK);
		txtpnNcantidadDe.setBounds(23, 404, 286, 19);
		frame.getContentPane().add(txtpnNcantidadDe);
		
		textFieldN = new JTextField();
		textFieldN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldN.setBounds(23, 434, 96, 30);
		frame.getContentPane().add(textFieldN);
		textFieldN.setColumns(10);
		
		btnGenerar.setActionCommand("GENERAR");
		btnGenerar.setForeground(new Color(255, 0, 102));
		btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnGenerar.setBounds(432, 532, 132, 30);
		frame.getContentPane().add(btnGenerar);
		
		btnNuevo.setActionCommand("CREAR ARCH");
		btnNuevo.setForeground(new Color(0, 0, 0));
		btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNuevo.setBounds(437, 125, 132, 30);
		frame.getContentPane().add(btnNuevo);
	}

	public JFrame getFrame() {
		return frame;
	}
	
    public JButton getBtnGenerar() {
		return btnGenerar;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public JTextField getTextFieldN() {
		return textFieldN;
	}

	public JTextArea getTextAreaMatrix() {
		return textAreaMatrix;
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

	public JTextField getTextFieldVectEst() {
		return textFieldVectEst;
	}

	public JTextField getTextFieldEntrp() {
		return textFieldEntrp;
	}
	
	
}
