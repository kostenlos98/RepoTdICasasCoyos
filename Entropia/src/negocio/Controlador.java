package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import base.Calculador;
import base.Parser;
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
    	Parser.get_instancia().parsearTexto(this.vista.getVentanaSimulacion().getTextAreaDistribucionProb().getText());
        this.calculador.hacerSimulacion((String) this.vista.getVentanaSimulacion().getListArchivos().getSelectedValue(),
        								Parser.get_instancia().getHashMapActual(),
        								Integer.valueOf(this.vista.getVentanaSimulacion().getTextFieldN().getText()));
        this.vista.lanzarCartelError("Simulacion realizada exitosamente!");
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
