package modelo;

import java.util.Comparator;

public class ComparadorSF  implements Comparator<NodoShannonFano>{
    public ComparadorSF() {
        super();
    }

    @Override
    public int compare(NodoShannonFano o1, NodoShannonFano o2) {
        int retorno=-1;
        if(o1.getData()<o2.getData())
        	retorno=1;
        else if (o1.getData()==o2.getData())
        	retorno=0;
        return retorno;   
    }
}
