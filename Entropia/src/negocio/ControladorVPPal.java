package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map.Entry;

import base.Calculador;
import base.GestorArchs;
import base.Parser;
import excepciones.CantidadIncorrectaException;
import excepciones.MatrizIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;
import interfaces.IVentanaPrincipal;

public class ControladorVPPal implements ActionListener{
	
	private IVentanaPrincipal vista;
	private Calculador calculador = new Calculador();
	
    public ControladorVPPal(IVentanaPrincipal vista) {
		super();
		this.vista = vista;
		this.vista.addActionListener(this); //aca ya se agregan los action listener de todas las ventanas
		this.vista.getVentanaSimulacion().refrescarLista(GestorArchs.get_instancia().listarArchivos());
		this.vista.getVentanaCalculos().refrescarLista(GestorArchs.get_instancia().listarArchivos());
		this.vista.getVentanaMarkov().refrescarLista(GestorArchs.get_instancia().listarArchivos());
		
	}
    
    public void generarMarkov() {
		if(this.vista.getVentanaMarkov().getListArchivos().isSelectionEmpty())
		{
			this.vista.lanzarCartelError("Primero seleccione un archivo");
			return;
		}
		try {
			Parser.get_instancia().parsearMatriz(this.vista.getVentanaMarkov().getTextAreaMatrix().getText(),
					Integer.valueOf(vista.getVentanaMarkov().getTextFieldN().getText()));
	        NumberFormat formatter = new DecimalFormat("#0.000");   
	        double[][] matTrans = Parser.get_instancia().getMatriz();
	        double[] vecEstacionario = this.calculador.generaVectorEstacionario(matTrans);
	        StringBuilder str = new StringBuilder();
	        for (int i = 0; i < vecEstacionario.length ; i++)
	            str.append(formatter.format(vecEstacionario[i]) + "   ");
	        this.vista.getVentanaMarkov().getTextFieldVectEst().setText(str.toString());
	        this.vista.getVentanaMarkov().getTextFieldEntrp().setText(String.valueOf(formatter.format(this.calculador.entropiaEstacionario(matTrans, 
	        		vecEstacionario, 
	        		Integer.valueOf(vista.getVentanaMarkov().getTextFieldN().getText())))));
	        
		} catch (NumberFormatException e) {
			this.vista.lanzarCartelError("Error de formato");
		} catch (MatrizIncorrectaException e) {
			this.vista.lanzarCartelError("Ingrese una matriz con N columnas y filas");
		}
		
	}
	
    public void realizarCalculos() {
    	this.vista.getVentanaCalculos().getTextAreaCantInfo().setText("");
    	if(this.vista.getVentanaCalculos().getListArchivos().isSelectionEmpty())
		{
			this.vista.lanzarCartelError("Primero seleccione un archivo!");
			return; 
		}
    	else {
        	try {
    			this.calculador.hacerCalculos((String) this.vista.getVentanaCalculos().getNombreSeleccionado());
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    			this.vista.lanzarCartelError("Error al hacer los calculos");
    		}
        	for (Entry<String, Double> estado:this.calculador.getCantInfoAct().entrySet())
        		this.vista.getVentanaCalculos().getTextAreaCantInfo().append("I("+estado.getKey()+") = "+estado.getValue()+"\n");
        		
        	this.vista.getVentanaCalculos().getTextFieldEntropia().setText(String.valueOf(this.calculador.getEntropiaAct()));
    	}
    }
     
    public void realizarSimulacion() {
    	if(this.vista.getVentanaSimulacion().getListArchivos().isSelectionEmpty())
		{
			this.vista.lanzarCartelError("Primero seleccione un archivo");
			return; 
		}
    	try {
			Parser.get_instancia().parsearTexto(this.vista.getVentanaSimulacion().getTextAreaDistribucionProb().getText());
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			this.vista.lanzarCartelError("Error de formato de texto");
			return; 
		}
        try {
        	if(!this.vista.getVentanaSimulacion().getTextFieldN().getText().equals(""))
        	{
        		this.calculador.hacerSimulacion((String) this.vista.getVentanaSimulacion().getNombreSeleccionado(),
						Parser.get_instancia().getHashMapActual(),
						Integer.valueOf(this.vista.getVentanaSimulacion().getTextFieldN().getText()));
        		
						this.vista.lanzarCartelError("Simulacion realizada exitosamente!");
			}
        	else {
        		this.vista.lanzarCartelError("Ingrese una cantidad de simbolos");
			}
			
		} catch (CantidadIncorrectaException  | NumberFormatException e) {
			this.vista.lanzarCartelError("Cantidad de simbolos incorrecta");
		} catch (SumaIncorrectaException e) {
			this.vista.lanzarCartelError("La suma de las probabilidades no es 1");
		}
        catch (ValorIncorrectoException e) {
			this.vista.lanzarCartelError("Los valores de probalidades son incorrectos");
		} 
        
    }
    
	@Override
    public void actionPerformed(ActionEvent arg) {
        String comando = arg.getActionCommand();
        if(comando.equalsIgnoreCase("SIMULAR")){
        	realizarSimulacion();
        }else
        if(comando.equalsIgnoreCase("CREAR ARCH")){
            this.vista.getDialogNArch().setVisible(true);
            this.vista.getVentanaSimulacion().refrescarLista(GestorArchs.get_instancia().listarArchivos());
            this.vista.getVentanaMarkov().refrescarLista(GestorArchs.get_instancia().listarArchivos());
        }else
	    if(comando.equalsIgnoreCase("OK CREAR ARCH")){
	    	GestorArchs.get_instancia().nuevoArchivo(this.vista.getDialogNArch().getTfNombreNArch().getText());
	        this.vista.getDialogNArch().setVisible(false);
	        this.vista.getVentanaSimulacion().refrescarLista(GestorArchs.get_instancia().listarArchivos());
	        this.vista.getVentanaCalculos().refrescarLista(GestorArchs.get_instancia().listarArchivos());
	        this.vista.getVentanaMarkov().refrescarLista(GestorArchs.get_instancia().listarArchivos());
	    }else
    	if(comando.equalsIgnoreCase("CALCULAR")){
        	realizarCalculos();
    	}else
    	if(comando.equalsIgnoreCase("GENERAR")){
        	generarMarkov();
        }
    }


	
}
