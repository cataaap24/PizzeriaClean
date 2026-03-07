package model;
import service.Reglas;

public class Bebida extends Producto {
    private int volumenMl;

    public Bebida(String nombre, int volumenMl) {
        super(nombre);
        this.volumenMl = volumenMl;
    }

    public int getVolumenMl() { return this.volumenMl; }
    
    @Override
    public String getTipo() { return "Bebida"; }

    @Override
    public double calcularValorBase() {
        return 0; // reglas
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Volumen = " + volumenMl + "ml)";
    }
}
