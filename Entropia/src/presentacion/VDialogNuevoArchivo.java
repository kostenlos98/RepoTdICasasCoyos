package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;

public class VDialogNuevoArchivo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombreNArch;
	private JButton btnDialogOK = new JButton("OK\r\n");
	
	/**
	 * Create the dialog.
	 */
	public VDialogNuevoArchivo() {
		setBounds(100, 100, 424, 238);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnNombreDelNuevo = new JTextPane();
		txtpnNombreDelNuevo.setForeground(Color.WHITE);
		txtpnNombreDelNuevo.setBackground(Color.BLACK);
		txtpnNombreDelNuevo.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnNombreDelNuevo.setEditable(false);
		txtpnNombreDelNuevo.setText("Nombre del nuevo archivo:");
		txtpnNombreDelNuevo.setBounds(10, 41, 230, 32);
		contentPanel.add(txtpnNombreDelNuevo);
		
		tfNombreNArch = new JTextField();
		tfNombreNArch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNombreNArch.setBounds(10, 82, 337, 32);
		contentPanel.add(tfNombreNArch);
		tfNombreNArch.setColumns(10);
		
		btnDialogOK.setActionCommand("OK CREAR ARCH");
		btnDialogOK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDialogOK.setBounds(302, 154, 85, 21);
		contentPanel.add(btnDialogOK);
	}

	public JTextField getTfNombreNArch() {
		return tfNombreNArch;
	}

	public JButton getBtnDialogOK() {
		return btnDialogOK;
	}
	
	
}
