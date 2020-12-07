package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map.Entry;

import interfaces.IVentanaCompresor;
import modelo.Compresor;
import modelo.GestorArchs;

public class ControladorCompresor implements ActionListener{
	
	private IVentanaCompresor vista;
	private Compresor compresor = new Compresor();
	
    public ControladorCompresor(IVentanaCompresor vista) {
		super();
		this.vista = vista;
		this.vista.addActionListener(this); //aca ya se agregan los action listener de todas las ventanas
		this.vista.refrescarLista(GestorArchs.get_instancia().listarArchivos());
	}
	
    public void realizarCalculosCompresor() {
    	if(this.vista.getListArchivos().isSelectionEmpty())
		{
			this.vista.lanzarCartelError("Primero seleccione un archivo!");
			return; 
		}
    	else {
        	try {
    			
        		
        		
    		} catch (Exception e) {
    			e.printStackTrace();
    			this.vista.lanzarCartelError("Error al hacer los calculos");
    		}    		
        	
    	}
    }
       
	@Override
    public void actionPerformed(ActionEvent arg) {
        String comando = arg.getActionCommand();
		if(comando.equalsIgnoreCase("CALCULAR")){
			realizarCalculosCompresor();
    	}
    }

	public IVentanaCompresor getVista() {
		return vista;
	}

	public Compresor getCompresor() {
		return compresor;
	}
	
	


	
}
