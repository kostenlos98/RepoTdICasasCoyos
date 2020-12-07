package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map.Entry;

import interfaces.IVentanaCanales;
import interfaces.IVentanaCompresor;
import modelo.Compresor;
import modelo.GestorArchs;
import modelo.SimuladorCanales;

public class ControladorCanales implements ActionListener{
	
	private IVentanaCanales vista;
	private SimuladorCanales simulador = new SimuladorCanales();
	
    public ControladorCanales(IVentanaCanales vista) {
		super();
		this.vista = vista;
		this.vista.addActionListener(this); //aca ya se agregan los action listener de todas las ventanas
	}
	
    public void realizarSimulacion() {
    	//CHECKEAR LAS OTRAS COSAS 

    }
       
	@Override
    public void actionPerformed(ActionEvent arg) {
        String comando = arg.getActionCommand();
		if(comando.equalsIgnoreCase("SIMULAR")){
			realizarSimulacion();
    	}
    }

	


	
}
