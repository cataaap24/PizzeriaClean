package usecases.dto;
import entities.Pedido;

public class ResumenPedido {
    private final Pedido order;
    private final double valorBase;
    private final double valorTotal;
    private final double descuento;
    private final double subtotal;

    public ResumenPedido(Pedido order, double valorBase, double subtotal, double valorTotal, double descuento) {
        this.order = order;
        this.valorBase = valorBase;
        this.valorTotal = valorTotal;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }

    public Pedido getOrder() { return this.order; }
    public double getValorBase() { return this.valorBase; }
    public double getSubtotal() { return this.subtotal; }
    public double getTotal() { return this.valorTotal; }
    public double getDescuento() { return this.descuento; }
}
