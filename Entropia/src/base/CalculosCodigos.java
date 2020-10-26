package base;

public class CalculosCodigos {
	
	public boolean esCodigoInstantaneo(String codigos[])
	{
		String prefijo;
		for(int i = 0;i<codigos.length;i++)
		{
			prefijo="";
			for(int j = 0;j<codigos[i].length()-1;j++)
			{
				prefijo+=codigos[i].charAt(j);
				if(estaEnCodigo(prefijo, codigos))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean estaEnCodigo(String palabra, String codigos[])
	{
		for(int i = 0;i<codigos.length;i++)
		{
			if(codigos[i].equals(palabra))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean cumpleKraft(String codigos[])
	{
		double acum=0;
		for(int i = 0;i<codigos.length;i++)
		{
			acum+=Math.pow(2,-codigos[i].length());
		}
		if(acum<=1)
			return true;
		else
			return false;
	}
	
	public double calcularEntropia(Double probs[])
	{
		double acum=0;
		for(int i=0;i<probs.length;i++)
		{
			acum+=probs[i]*Math.log(1/probs[i])/Math.log(2);
		}
		return acum;
	}
	
	public double calcularLongitudMedia(Double probs[],String codigos[])
	{
		double acum = 0;
		for(int i=0;i<codigos.length;i++)
		{
			acum+=probs[i]*codigos[i].length();
		}
		return acum;
	}
	
	//????????
	public boolean esCodigoCompacto(Double probs[],String codigos[])
	{
		if(calcularEntropia(probs) == calcularLongitudMedia(probs, codigos))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public void generarCodigo(String nombreArch, Double probs[])
	{
		String codigos[] = new String[probs.length];
		int longitud = (int) Math.floor(Math.sqrt(probs.length)) +1;
		System.out.println(longitud);
		for(int i = 0;i<probs.length;i++)
		{
			codigos[i] = Integer.toBinaryString(i);
			while(codigos[i].length()!=longitud)
			{
				codigos[i] = '0' + codigos[i];
			}
			
		}
		StringBuilder str = new StringBuilder();
		for(int i=0; i<codigos.length; i++) {
			str.append("S"+(i+1)+" "+codigos[i]+" "+probs[i]);
			str.append("/n");
		}
		GestorArchs.get_instancia().actualizarArchivo(nombreArch, str.toString());

	}

}
