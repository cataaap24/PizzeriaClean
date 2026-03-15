package entities;

public abstract class Producto  {
    private final String id;
    private String nombre;
    protected boolean disponible = true;

    protected Producto(String id, String nombre) {
        this.id = id; 
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new DomainException("El nombre del producto no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    
    public abstract String getTipo();
    public abstract double calcularValorBase();

    public boolean estaDisponible() { return disponible; }
    
    @Override
    public String toString() {
        return "[" + getTipo() + "] ID = " + this.id
                + ", Nombre = '" + this.nombre + "'"
                + ", Disponible = " + this.disponible;
    }
}

