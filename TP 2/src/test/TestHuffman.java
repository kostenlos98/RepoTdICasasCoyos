package test;

import modelo.GenerarHuffman;
import modelo.Simbolo;

public class TestHuffman {

	public static void main(String[] args) {
		Simbolo simbolos[] = new Simbolo[6];
		simbolos[0] =new Simbolo(0.05, "a");
		simbolos[1] =new Simbolo(0.09, "b");
		simbolos[2] =new Simbolo(0.12, "c");
		simbolos[3] =new Simbolo(0.13, "d");
		simbolos[4] =new Simbolo(0.16, "e");
		simbolos[5] =new Simbolo(0.45, "f");
		GenerarHuffman generarHuffman = new GenerarHuffman();
		

		System.out.println(generarHuffman.textoComprimido("baba", simbolos));
		System.out.println(generarHuffman.redundancia(simbolos));

	}

}
