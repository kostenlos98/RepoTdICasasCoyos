package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import interfaces.IVentanaPrincipal;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPPal implements IVentanaPrincipal {

	private JFrame frame;
	
	private VentanaSimulacion ventanaSimulacion = new VentanaSimulacion();
	private VentanaCalculos ventanaCalculos = new VentanaCalculos();
	private VDialogNuevoArchivo ventanaDialogNArch = new VDialogNuevoArchivo();
	private VentanaMarkov ventanaMarkov = new VentanaMarkov();

	/**
	 * Create the application.
	 */
	public VentanaPPal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 807, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(10, 10, 773, 63);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(10, 83, 62, 413);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(82, 83, 686, 413);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(10, 10, 515, 39);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JTextPane txtpnSubtitulo = new JTextPane();
		txtpnSubtitulo.setEditable(false);
		txtpnSubtitulo.setForeground(new Color(255, 255, 255));
		txtpnSubtitulo.setBackground(new Color(0, 0, 0));
		txtpnSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnSubtitulo.setText("Bienvenido! Seleccione una opcion");
		txtpnSubtitulo.setBounds(10, 10, 424, 29);
		panel_3.add(txtpnSubtitulo);
		
		JPanel panelSimulacion = new JPanel();
		panelSimulacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ventanaSimulacion.getFrame().setVisible(true);
			}
		});
		panelSimulacion.setBackground(Color.DARK_GRAY);
		panelSimulacion.setBounds(10, 70, 666, 102);
		panel_2.add(panelSimulacion);
		panelSimulacion.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 10, 119, 71);
		panelSimulacion.add(panel_4);
		panel_4.setBorder(new LineBorder(UIManager.getColor("MenuItem.selectionBackground"), 4));
		panel_4.setBackground(UIManager.getColor("MenuItem.disabledForeground"));
		panel_4.setLayout(null);
		
		JTextPane txtpnSimulacion = new JTextPane();
		txtpnSimulacion.setEditable(false);
		txtpnSimulacion.setForeground(UIManager.getColor("MenuItem.background"));
		txtpnSimulacion.setBackground(UIManager.getColor("MenuItem.disabledForeground"));
		txtpnSimulacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnSimulacion.setText("SIMULACION");
		txtpnSimulacion.setBounds(10, 22, 99, 26);
		panel_4.add(txtpnSimulacion);
		
		JTextPane txtpnDescSim = new JTextPane();
		txtpnDescSim.setEditable(false);
		txtpnDescSim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescSim.setText("Generar una secuencia de N s\u00EDmbolos de una fuente seg\u00FAn una distribuci\u00F3n \r\nde probabilidad.");
		txtpnDescSim.setBackground(Color.DARK_GRAY);
		txtpnDescSim.setForeground(new Color(255, 255, 255));
		txtpnDescSim.setBounds(129, 31, 537, 50);
		panelSimulacion.add(txtpnDescSim);
		
		JPanel panelCalc = new JPanel();
		panelCalc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ventanaCalculos.getFrame().setVisible(true);
			}
		});
		panelCalc.setBackground(Color.DARK_GRAY);
		panelCalc.setLayout(null);
		panelCalc.setBounds(10, 182, 666, 107);
		panel_2.add(panelCalc);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBounds(0, 10, 119, 75);
		panelCalc.add(panel_4_1);
		panel_4_1.setLayout(null);
		panel_4_1.setBorder(new LineBorder(UIManager.getColor("MenuItem.selectionBackground"), 4));
		panel_4_1.setBackground(SystemColor.textInactiveText);
		
		JTextPane txtpnCalculos = new JTextPane();
		txtpnCalculos.setEditable(false);
		txtpnCalculos.setText("CALCULOS");
		txtpnCalculos.setForeground(SystemColor.menu);
		txtpnCalculos.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnCalculos.setBackground(SystemColor.textInactiveText);
		txtpnCalculos.setBounds(10, 29, 82, 26);
		panel_4_1.add(txtpnCalculos);
		
		JTextPane txtpnDescCalc = new JTextPane();
		txtpnDescCalc.setEditable(false);
		txtpnDescCalc.setText("Calcular la cantidad de informaci\u00F3n y la entrop\u00EDa.");
		txtpnDescCalc.setForeground(Color.WHITE);
		txtpnDescCalc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescCalc.setBackground(Color.DARK_GRAY);
		txtpnDescCalc.setBounds(129, 24, 537, 50);
		panelCalc.add(txtpnDescCalc);
		
		JPanel panelMarkov = new JPanel();
		panelMarkov.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ventanaMarkov.getFrame().setVisible(true);
			}
		});
		panelMarkov.setLayout(null);
		panelMarkov.setBackground(Color.DARK_GRAY);
		panelMarkov.setBounds(10, 299, 666, 107);
		panel_2.add(panelMarkov);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setLayout(null);
		panel_4_1_1.setBorder(new LineBorder(UIManager.getColor("MenuItem.selectionBackground"), 4));
		panel_4_1_1.setBackground(SystemColor.textInactiveText);
		panel_4_1_1.setBounds(0, 10, 119, 75);
		panelMarkov.add(panel_4_1_1);
		
		JTextPane txtpnMarkov = new JTextPane();
		txtpnMarkov.setText("MARKOV\r\n");
		txtpnMarkov.setForeground(SystemColor.menu);
		txtpnMarkov.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnMarkov.setEditable(false);
		txtpnMarkov.setBackground(SystemColor.textInactiveText);
		txtpnMarkov.setBounds(10, 29, 82, 26);
		panel_4_1_1.add(txtpnMarkov);
		
		JTextPane txtpnGenerarFuentesDel = new JTextPane();
		txtpnGenerarFuentesDel.setText("Generar fuentes del tipo de Markov a partir de una matriz de\r\ntransici\u00F3n de estados.");
		txtpnGenerarFuentesDel.setForeground(Color.WHITE);
		txtpnGenerarFuentesDel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnGenerarFuentesDel.setEditable(false);
		txtpnGenerarFuentesDel.setBackground(Color.DARK_GRAY);
		txtpnGenerarFuentesDel.setBounds(129, 24, 537, 50);
		panelMarkov.add(txtpnGenerarFuentesDel);
	}

	@Override
    public void addActionListener(ActionListener actionListener) {
        this.ventanaSimulacion.getBtnNuevo().addActionListener(actionListener);
        this.ventanaSimulacion.getBtnSimular().addActionListener(actionListener);
        this.ventanaCalculos.getBtnCalcular().addActionListener(actionListener);
        this.ventanaMarkov.getBtnNuevo().addActionListener(actionListener);
        this.ventanaMarkov.getBtnGenerar().addActionListener(actionListener);
        this.ventanaDialogNArch.getBtnDialogOK().addActionListener(actionListener);
    }

	public JFrame getFrame() {
		return frame;
	}

	public VentanaMarkov getVentanaMarkov() {
		return ventanaMarkov;
	}

	public VentanaSimulacion getVentanaSimulacion() {
		return ventanaSimulacion;
	}
	
	public VentanaCalculos getVentanaCalculos() {
		return ventanaCalculos;
	}
	
	public VDialogNuevoArchivo getDialogNArch() {
		return ventanaDialogNArch;
	}
	
    public void lanzarCartelError(String err) {
        JOptionPane.showMessageDialog(null, err);
    }
}
