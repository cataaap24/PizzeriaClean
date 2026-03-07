package model;

public class Bebida extends Producto {
    private int volumenMI;

    public Bebida(int id, String nombre, int volumenMI) {
        super(id, nombre);
        this.volumenMI = volumenMI;
    }

    public int getVolumenMI() { return this.volumenMI; }

    @Override
    public double calcularValorBase() {
        return 0;
    }
}
