package modelo;

public class NodoShannonFano {
    private double data;
    private char car;
    
    public NodoShannonFano(char car,double prob) {
        this.data=prob;
        this.car=car;
    }

    public double getData() {
        return data;
    }

    public char getCar() {
        return car;
    }
}
