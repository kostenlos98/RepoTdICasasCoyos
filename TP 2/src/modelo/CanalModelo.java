package modelo;

public class CanalModelo {
	
	double matrizCanal[][];
	double probPriori[];
	
	
	//N = filas M = columnas
	public double[] calcularProbSalida(double matrizCanal[][],double probPriori[],int N,int M)
	{
		double probSalida[] = new double[M];
		for(int j=0;j<M;j++)
		{
			probSalida[j]=0;
			for(int i=0;i<N;i++)
			{
				probSalida[j]+=matrizCanal[i][j]*probPriori[i];
			}
		}
		return probSalida;
	}
	
	public double[][] calcularProbPoste(double matrizCanal[][],double probPriori[],double probSalida[],int N,int M)
	{
		double probPoste[][] = new double[N][M];
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				probPoste[i][j]=(matrizCanal[i][j]*probPriori[i])/probSalida[j];
			}
		}
		return probPoste;
	}
	
	public double[] entropiaPoste(double probPoste[][],int N,int M)
	{
		double entropiaPoste[] = new double [M];
		for(int j=0;j<M;j++)
		{
			entropiaPoste[j]=0;
			for(int i =0;i<N;i++)
			{
				if(probPoste[i][j]!=0)
				{
					entropiaPoste[j]+=probPoste[i][j]*Math.log(1/probPoste[i][j])/Math.log(2);
				}
				
			}
		}
		return entropiaPoste;
	}
	
	public double equivocacionCanal(double entropiaPoste)
	{
		double equivocacion=0;
		
		return equivocacion;
	}

}
