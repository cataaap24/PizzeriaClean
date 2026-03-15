package entities;

public class Cliente {
    private final String id;
    private String nombre;
    private String telefono;

    public Cliente(String id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public String getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public String getTelefono() { return this.telefono; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new DomainException("El nombre del cliente es obligatorio.");
        }
        this.nombre = nombre;
    }
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            this.telefono = "";
            return;
        }
        if (telefono.length() < 10) {
            throw new DomainException("Si registra un teléfono, debe tener al menos 10 dígitos.");
        }

        this.telefono = telefono;
    }
    @Override
    public String toString() {
        String tel = (telefono == null || telefono.isBlank()) ? "Sin teléfono registrado" : telefono;
        return "Cliente ID = " + id + " | Nombre = '" + nombre + "'" + " | Tel = " + tel;
    }
}