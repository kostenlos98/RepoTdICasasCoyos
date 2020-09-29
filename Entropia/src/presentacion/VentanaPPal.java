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
	private VDialogNuevoArchivo ventanaDialogNArch = new VDialogNuevoArchivo();
	

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
		panelSimulacion.setBounds(10, 70, 666, 122);
		panel_2.add(panelSimulacion);
		panelSimulacion.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 10, 119, 102);
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
		txtpnSimulacion.setBounds(10, 36, 99, 26);
		panel_4.add(txtpnSimulacion);
		
		JTextPane txtpnDescSim = new JTextPane();
		txtpnDescSim.setEditable(false);
		txtpnDescSim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescSim.setText("Generar una secuencia de N s\u00EDmbolos de una fuente seg\u00FAn una distribuci\u00F3n \r\nde probabilidad.");
		txtpnDescSim.setBackground(Color.DARK_GRAY);
		txtpnDescSim.setForeground(new Color(255, 255, 255));
		txtpnDescSim.setBounds(129, 43, 537, 50);
		panelSimulacion.add(txtpnDescSim);
		
		JPanel panelCalc = new JPanel();
		panelCalc.setBackground(Color.DARK_GRAY);
		panelCalc.setLayout(null);
		panelCalc.setBounds(10, 229, 666, 122);
		panel_2.add(panelCalc);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBounds(0, 10, 119, 102);
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
		txtpnCalculos.setBounds(10, 41, 82, 26);
		panel_4_1.add(txtpnCalculos);
		
		JTextPane txtpnDescCalc = new JTextPane();
		txtpnDescCalc.setEditable(false);
		txtpnDescCalc.setText("Calcular la cantidad de informaci\u00F3n y la entrop\u00EDa.");
		txtpnDescCalc.setForeground(Color.WHITE);
		txtpnDescCalc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescCalc.setBackground(Color.DARK_GRAY);
		txtpnDescCalc.setBounds(129, 41, 537, 50);
		panelCalc.add(txtpnDescCalc);
	}

	@Override
    public void addActionListener(ActionListener actionListener) {
        this.ventanaSimulacion.getBtnNuevo().addActionListener(actionListener);
        this.ventanaSimulacion.getBtnSimular().addActionListener(actionListener);
        this.ventanaDialogNArch.getBtnDialogOK().addActionListener(actionListener);
    }

	public JFrame getFrame() {
		return frame;
	}

	public VentanaSimulacion getVentanaSimulacion() {
		return ventanaSimulacion;
	}

	public VDialogNuevoArchivo getDialogNArch() {
		return ventanaDialogNArch;
	}
	
    public void lanzarCartelError(String err) {
        JOptionPane.showMessageDialog(null, err);
    }
}
