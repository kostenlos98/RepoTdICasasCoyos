package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map.Entry;

import base.Calculador;
import base.CalculosCodigos;
import base.GestorArchs;
import base.Parser;
import excepciones.CantidadIncorrectaException;
import excepciones.MatrizIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;
import interfaces.IVentanaPPalPCod;
import interfaces.IVentanaPrincipal;

public class ControladorVPCod implements ActionListener{
	
	private IVentanaPPalPCod vista;
	private CalculosCodigos calcCod = new CalculosCodigos();
	
    public ControladorVPCod(IVentanaPPalPCod vista) {
		super();
		this.vista = vista;
		this.vista.addActionListener(this); //aca ya se agregan los action listener de todas las ventanas
		this.vista.getVentanaCalcPCod().refrescarLista(GestorArchs.get_instancia().listarArchivos());
		this.vista.getVentanaPCod().refrescarLista(GestorArchs.get_instancia().listarArchivos());

		
	}
	
    public Double[] parseStringColumn(String array[]) {
    	Double retorno[] = new Double[array.length];
    	for(int i=0; i<array.length; i++) {
    		retorno[i] = Double.parseDouble(array[i]);
    	}
    	return retorno;
    }
    
    public void realizarCalculosPCod() {
    	if(this.vista.getVentanaCalcPCod().getListArchivos().isSelectionEmpty())
		{
			this.vista.lanzarCartelError("Primero seleccione un archivo!");
			return; 
		}
    	else {
        	try {
    			String nombreArch = (String) this.vista.getVentanaCalcPCod().getNombreSeleccionado();
    			String textoArch = GestorArchs.get_instancia().getTextoArch(nombreArch);
    			String[][] matrizArch = Parser.get_instancia().obtenerMatrizPCod(textoArch);
				String simbolos[] = matrizArch[0];
				String codigos[] = matrizArch[1];
				Double probs[] = parseStringColumn(matrizArch[2]);
				
				Double entropia = calcCod.calcularEntropia(probs);
				Double longitudMedia = calcCod.calcularLongitudMedia(probs,codigos);
				boolean isKraft = calcCod.cumpleKraft(codigos);
				boolean isCompacto = calcCod.esCodigoCompacto(probs, codigos);
				
				this.vista.getVentanaCalcPCod().getTextFieldEntropia().setText(String.valueOf(entropia));
				this.vista.getVentanaCalcPCod().getTextFieldLongMedia().setText(String.valueOf(longitudMedia));
				this.vista.getVentanaCalcPCod().getTextFieldCumpleKraft().setText(String.valueOf(isKraft));
				this.vista.getVentanaCalcPCod().getTextFieldCodComp().setText(String.valueOf(isCompacto));
    		} catch (Exception e) {
    			e.printStackTrace();
    			this.vista.lanzarCartelError("Error al hacer los calculos");
    		}    		
        	
    	}
    }
    
    
    public void generarPCod() {
    	if(this.vista.getVentanaPCod().getListArchivos().isSelectionEmpty())
		{
			this.vista.lanzarCartelError("Primero seleccione un archivo");
			return; 
		}
    	try {
			Parser.get_instancia().parsearTexto(this.vista.getVentanaPCod().getTextAreaDistribucionProb().getText());
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			this.vista.lanzarCartelError("Error de formato de texto");
			return; 
		}
        try 
        {
        	Double[] resParseo = (Double[]) Parser.get_instancia().getHashMapActual().values().toArray();
			this.calcCod.generarCodigo((String) this.vista.getVentanaPCod().getNombreSeleccionado(),
										resParseo);
			this.vista.lanzarCartelError("El codigo ha sido generado exitosamente!");
		} catch (Exception e) {
			this.vista.lanzarCartelError("Error!");
		} 
    }
    
	@Override
    public void actionPerformed(ActionEvent arg) {
        String comando = arg.getActionCommand();
        if(comando.equalsIgnoreCase("CREAR ARCH")){
            this.vista.getDialogNArch().setVisible(true);
    		this.vista.getVentanaCalcPCod().refrescarLista(GestorArchs.get_instancia().listarArchivos());
    		this.vista.getVentanaPCod().refrescarLista(GestorArchs.get_instancia().listarArchivos());
        }else
	    if(comando.equalsIgnoreCase("OK CREAR ARCH")){
	    	GestorArchs.get_instancia().nuevoArchivo(this.vista.getDialogNArch().getTfNombreNArch().getText());
	        this.vista.getDialogNArch().setVisible(false);
    		this.vista.getVentanaCalcPCod().refrescarLista(GestorArchs.get_instancia().listarArchivos());
    		this.vista.getVentanaPCod().refrescarLista(GestorArchs.get_instancia().listarArchivos());
	    }else
    	if(comando.equalsIgnoreCase("GENERAR2")){
        	generarPCod();
    	}else
		if(comando.equalsIgnoreCase("CALCULAR2")){
        	realizarCalculosPCod();
    	}
    }


	
}
