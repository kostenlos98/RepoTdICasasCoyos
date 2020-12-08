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
        		compresor.setNombreArchivoSeleccionado(vista.getNombreSeleccionado());
        		compresor.cargarTextoSeleccionado();
        		double TC_H = compresor.compresionHuffman();
        		 compresor.compresionSF();
        		double TC_RLC = compresor.compresionRLC();
        		StringBuilder resultado = new StringBuilder();
        		resultado.append("*** ARCHIVOS GENERADOS EN CARPETA ORIGEN ***\n");
        		resultado.append("*** Tasas de compresion ***" + '\n');
        		resultado.append("TC RLC: "+ +TC_RLC+'\n');
        		resultado.append("TC SF: "+ compresor.compresionSF +'\n');
        		resultado.append("TC Huffman: "+ TC_H + '\n');
        		resultado.append("*** Redundancias y rendimientos  ***" + '\n');
        		
           		resultado.append("Redundancia SF: "+ compresor.redudanciaSF+'\n');
        		resultado.append("Rendimiento SF: "+ (1-compresor.redudanciaSF)+'\n');
        		
        		double redH = compresor.redundanciaH();
           		resultado.append("Redundancia Huffman: "+ redH + '\n');
        		resultado.append("Rendimiento Huffman: "+ (1.0 - redH) + '\n');
        		vista.getTextAreaResultados().setText(resultado.toString());
        		
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
