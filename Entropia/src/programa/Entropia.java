package programa;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class Entropia {

	public static void main(String[] args) {
		
		File archivo = new File("datos.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(archivo);
			int i =1;
			double entropia=0;
			while(scanner.hasNextLine())
			{
				String dato = scanner.nextLine();
				//Calculo de la informacion de cada estado y su entropia
				Double prob = Double.parseDouble(dato);
				Double informacion = Math.log(1/prob)/Math.log(2); //Hay que hacer esto porque Java no tiene log base 2
				System.out.println("I(s"+(i++)+") ="+informacion);
				entropia+=informacion*prob;
			}
			System.out.println("H(S) ="+entropia);
			
		} catch (FileNotFoundException e) {
			System.out.println("No existe el archivo");
			e.printStackTrace();
		}
		

	}

	

}

