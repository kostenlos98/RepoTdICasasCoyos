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
import modelo.CanalModelo;
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
    	if(vista.getTextAreaMatrizCanal().getText() == "" || vista.getTextAreaProbs().getText() == "") 
    		vista.lanzarCartelError("Debe rellenar todos los campos requeridos!");
    	else {
    		CanalModelo canal = new CanalModelo();
    		StringBuilder resultado = new StringBuilder();
    		resultado.append("*** Equivocacion ***" + '\n');
    		
    		resultado.append("*** Informacion mutua ***" + '\n');
    		
    		resultado.append("*** Propiedades de la informacion mutua ***" + '\n');
    		
    	}
    }
       
	@Override
    public void actionPerformed(ActionEvent arg) {
        String comando = arg.getActionCommand();
		if(comando.equalsIgnoreCase("SIMULAR")){
			realizarSimulacion();
    	}
    }

	


	
}
