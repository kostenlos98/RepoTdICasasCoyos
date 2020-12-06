package test;

import modelo.CanalModelo;

public class TestCanal {

	public static void main(String[] args) {
		int N =3, M=4;
		double matrizCanal[][] = new double[N][M];
		double probPoste[][] = new double[N][M];
		double probPriori[] = new double[N];
		double probSalida[] = new double [M];
		double entropiaPoste[] = new double [M];
		
		/*matrizCanal[0][0] = 0.4; matrizCanal[0][1]=0.4; matrizCanal[0][2]=0.2;
		matrizCanal[1][0] = 0.3; matrizCanal[1][1]=0.2; matrizCanal[1][2]=0.5;
		matrizCanal[2][0] = 0.3; matrizCanal[2][1]=0.4; matrizCanal[2][2]=0.3;
		
		probPriori[0] =0.3;
		probPriori[1]=0.3;
		probPriori[2]=0.4;
		
		*/
		
		/*matrizCanal[0][0] = 0.666; matrizCanal[0][1]=0.333; 
		matrizCanal[1][0] = 0.1; matrizCanal[1][1]=0.9; 
		
		probPriori[0] =0.75;
		probPriori[1]=0.25;
		*/
		
		matrizCanal[0][0] = 0.25; matrizCanal[0][1]=0.25; matrizCanal[0][2]=0.25; matrizCanal[0][3]=0.25;
		matrizCanal[1][0] = 0.25; matrizCanal[1][1]=0.25; matrizCanal[1][2]=0; matrizCanal[1][3]=0.5;
		matrizCanal[2][0] = 0.5; matrizCanal[2][1]=0; matrizCanal[2][2]=0.5; matrizCanal[2][3]=0;
		
		probPriori[0] =0.25;
		probPriori[1]=0.25;
		probPriori[2]=0.5;
		
		CanalModelo modelo = new CanalModelo();
		
		probSalida = modelo.calcularProbSalida(matrizCanal, probPriori, N, M);
		
		System.out.println("Probablidades de salida");
		for(int i=0;i<N;i++)
		{
			System.out.println(probSalida[i]);
		}
		
		System.out.println();
		
		probPoste = modelo.calcularProbPoste(matrizCanal, probPriori, probSalida, N, M);
		
		System.out.println("Probablidades a posteriori");
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				System.out.print(probPoste[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		entropiaPoste = modelo.entropiaPoste(probPoste, N, M);
		
		System.out.println("Entropia a posteriori");
		for(int i=0;i<M;i++)
		{
			System.out.println(entropiaPoste[i]);
		}
		

	}

}
