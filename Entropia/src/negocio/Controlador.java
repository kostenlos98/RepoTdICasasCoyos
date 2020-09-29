package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import base.Calculador;
import base.Parser;
import excepciones.CantidadIncorrectaException;
import excepciones.SumaIncorrectaException;
import excepciones.ValorIncorrectoException;
import interfaces.IVentanaPrincipal;

public class Controlador implements ActionListener{
	
	private IVentanaPrincipal vista;
	private Calculador calculador = new Calculador();
	
    public Controlador(IVentanaPrincipal vista) {
		super();
		this.vista = vista;
		this.vista.addActionListener(this); //aca ya se agregan los action listener de todas las ventanas
		this.vista.getVentanaSimulacion().refrescarLista(calculador.listarArchivos());
	}


    public void realizarSimulacion() {
    	if(this.vista.getVentanaSimulacion().getListArchivos().isSelectionEmpty())
		{
			this.vista.lanzarCartelError("Primero seleccione un archivo");
			return; //No se si esta bien hacer return directamente
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
        		this.calculador.hacerSimulacion((String) this.vista.getVentanaSimulacion().getListArchivos().getSelectedValue(),
						Parser.get_instancia().getHashMapActual(),
						Integer.valueOf(this.vista.getVentanaSimulacion().getTextFieldN().getText()));
        		
						//TODO Guardar el Hashmap de alguna forma
        		
						this.vista.lanzarCartelError("Simulacion realizada exitosamente!");
						//Actualizar archivo
						calculador.actualizarArchivo(this.vista.getVentanaSimulacion().getNombreSeleccionado(),
						this.vista.getVentanaSimulacion().getTextAreaDistribucionProb().getText());
			}
        	else {
        		this.vista.lanzarCartelError("Ingrese una cantidad de simbolos");
			}
			
		} catch (CantidadIncorrectaException e) {
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
            this.vista.getVentanaSimulacion().refrescarLista(calculador.listarArchivos());
        }else
	    if(comando.equalsIgnoreCase("OK CREAR ARCH")){
	        this.calculador.nuevoArchivo(this.vista.getDialogNArch().getTfNombreNArch().getText());
	        this.vista.getDialogNArch().setVisible(false);
	        this.vista.getVentanaSimulacion().refrescarLista(calculador.listarArchivos());
	    }
        
    }
	
}
