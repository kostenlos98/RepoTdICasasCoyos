package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import interfaces.IVentanaCanales;
import interfaces.IVentanaCompresor;
import modelo.CanalModelo;
import modelo.Compresor;
import modelo.GestorArchs;

public class ControladorCanales implements ActionListener{
	
	private IVentanaCanales vista;
	
    public ControladorCanales(IVentanaCanales vista) {
		super();
		this.vista = vista;
		this.vista.addActionListener(this); //aca ya se agregan los action listener de todas las ventanas
	}
	
    public void realizarCalculos() {
    	if(vista.getTextAreaMatrizCanal().getText() == "" || vista.getTextAreaProbs().getText() == "") 
    		vista.lanzarCartelError("Debe rellenar todos los campos requeridos!");
    	else {
    		CanalModelo canal = new CanalModelo();
    		
    		StringBuilder resultado = new StringBuilder();
    		double[][] matrizCanal = null;
    		double[] probPriori = null;
    		
    		int N = Integer.parseInt(vista.getTextAreaN().getText());
    		int M = Integer.parseInt(vista.getTextAreaM().getText());
    		
    		try {
				matrizCanal = parsearMatriz(vista.getTextAreaMatrizCanal().getText(),N,M);
			} catch (Exception e) {
				vista.lanzarCartelError("ERROR parseando matriz");
				e.printStackTrace();
			}
    		
    		try {
    			probPriori = parsearProbsPriori(vista.getTextAreaProbs().getText(),N);
			} catch (Exception e) {
				vista.lanzarCartelError("ERROR parseando probabilidades a priori");
				e.printStackTrace();
			}
    		
    		double[] probSalida = canal.calcularProbSalida(matrizCanal, probPriori, N, M);
    		double[][] probPoste  = canal.calcularProbPoste(matrizCanal, probPriori, probSalida, N, M);
    		double[] entropiaPoste = canal.entropiaPoste(probPoste, N, M) ;
    		double entropiaPriori = canal.entropiaPriori(probPriori, N);
    		double equivocacion = canal.equivocacionCanal(probSalida, entropiaPoste);
    		double infoMutua = canal.informacionMutua(equivocacion, entropiaPriori);
    		double entropiaSalida = canal.entropiaSalida(probSalida, M);
    		DecimalFormat numberFormat = new DecimalFormat("0.0000");
		
    		resultado.append("*** Calculos intermedios ***" + '\n');
    		resultado.append("Probabilidades de salida" + '\n');
    		resultado.append(Arrays.toString(probSalida));
    		resultado.append('\n');
    		resultado.append('\n');
    		resultado.append("Probabilidades a posteriori" + '\n');
    		resultado.append(Arrays.deepToString(probPoste));
    		resultado.append('\n');
    		resultado.append('\n');
    		resultado.append("Entropia a posteriori" + '\n');
    		resultado.append(Arrays.toString(entropiaPoste));
    		resultado.append('\n');
    		resultado.append('\n');
    		resultado.append("Entropia a priori" + '\n');
    		resultado.append(numberFormat.format(entropiaPriori));
    		resultado.append('\n');
    		resultado.append('\n');
    		resultado.append("Entropia salida" + '\n');
    		resultado.append(numberFormat.format(entropiaSalida));
    		resultado.append('\n');
    		resultado.append('\n');
    		resultado.append("*** Equivocacion ***" + '\n');
    		resultado.append("H(A/B) = " + numberFormat.format(equivocacion));
    		resultado.append('\n');
    		resultado.append('\n');
    		resultado.append("*** Informacion mutua ***" + '\n');
    		resultado.append("I(A,B) = " + numberFormat.format(infoMutua) + '\n');
    		resultado.append('\n');
    		resultado.append('\n');
    		resultado.append("*** Propiedades de la informacion mutua ***" + '\n');
    		if (infoMutua == 0) {
    			resultado.append("I(A,B) = 0 ya que los símbolos de entrada y salida son estadisticamente independientes" + '\n');
    		}
    		else {
    			resultado.append("I(A,B) > 0 ya que los símbolos de entrada y salida son estadisticamente dependientes" + '\n');
    		}
    		resultado.append("H(A,B)=H(A)+H(B)-I(A,B) = "+ numberFormat.format((entropiaPriori + entropiaSalida - infoMutua) + '\n'));
    		
    		vista.getTextAreaResultados().setText(resultado.toString());
    		
    	}
    }
       
	@Override
    public void actionPerformed(ActionEvent arg) {
        String comando = arg.getActionCommand();
		if(comando.equalsIgnoreCase("SIMULAR")){
			realizarCalculos();
    	}
    }
	
	public double[][] parsearMatriz(String texto, int N, int M) throws Exception
	{
		String lineas[];
		String lineaAct[];
		double[][] matriz = new double[N][M];
		lineas = texto.split("\n");
		if(lineas.length!=N)
			throw new Exception("Matriz incorrecta! (N)");
		double dato;
		for(int i=0;i<N;i++)
		{
				
			lineaAct=lineas[i].split(" ");
			if(lineaAct.length!=M)
				throw new Exception("Matriz incorrecta! (M)");
			for(int j=0;j<M;j++)
			{
				dato = Double.parseDouble(lineaAct[j]);
				matriz[i][j]= dato;
			}
		}	
		return matriz;
	}
	
	public double[] parsearProbsPriori(String texto, int N) throws Exception
	{
		String elementos[];
		double[] probs = new double[N];
		elementos = texto.split(" ");
		if(elementos.length!=N)
			throw new Exception("Cantidad de probabilidades no consistente con matriz! (N)");
		double dato;
		for(int i=0;i<N;i++)
		{
			dato = Double.parseDouble(elementos[i]);
			probs[i]= dato;

		}	
		return probs;
	}

	


	
}
