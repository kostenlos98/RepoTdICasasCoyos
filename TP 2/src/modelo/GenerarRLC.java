package modelo;

public class GenerarRLC {
	
	public String generarRLC(String texto)
	{
		char ant= texto.charAt(0),act=0;
		int i=1,k=1;
		String textoComprimido="";
		while(i < texto.length())
		{
			act= texto.charAt(i);
			if(act==ant)
			{
				k++;
			}
			else 
			{
				textoComprimido+= k+""+ant+"";
				k=1;
			}
			ant=act;
			i++;
			
		}
		textoComprimido+= k+""+ant+"";
		return textoComprimido;
	}
	
	public double tasaCompresion(String textoOriginal,String textoComprimido)
	{
		return textoOriginal.length()/textoComprimido.length();
	}

}
