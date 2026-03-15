package entities;

public class DomainException extends RuntimeException {
    public DomainException(String mensaje) {
        super(mensaje);
    }
}