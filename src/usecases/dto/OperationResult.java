package usecases.dto;

/* Se puede modificar para que en vez de ResumenPedido se pueda obtener cualquier objeto que
tenga información relevante y sea requerida por la UI mediante la declaración 'Object data'*/

public class OperationResult {
    private final boolean success;
    private final String message;
    private final ResumenPedido resumen;

    //Sobrecarga para obtener el resumen y hacer que imprimirTicket pueda acceder a su información
    private OperationResult(boolean success, String message, ResumenPedido resumen) {
        this.success = success;
        this.message = message;
        this.resumen = resumen;
    }
  
    private OperationResult(boolean success, String message) {
        this(success, message, null);
    }

    public static OperationResult ok(String message) {
        return new OperationResult(true, message);
    }

    public static OperationResult ok(String message, ResumenPedido resumen) {
        return new OperationResult(true, message, resumen);
    }

    public static OperationResult fail(String message) {
        return new OperationResult(false, message);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public ResumenPedido getResumen() { return resumen; }

    @Override
    public String toString() { return message; }
}