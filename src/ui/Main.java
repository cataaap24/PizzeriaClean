package ui;
import model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Mini proyecto :)");
        Pizza pizzaPequena = new Pizza("Pizza", "PEQUENA", "pepperoni");
        Bebida cocaCola = new Bebida("Bebida", 500);
        Cliente arturito = new Cliente("Arturo", "3212314173");
        Pedido pedido1 = new Pedido(arturito, pizzaPequena, 1, TipoEntrega.PARA_AQUI);
        System.out.println("Pedido con ID: " + pedido1.getId());
        System.out.println("Fecha: " + pedido1.getFecha());
        System.out.println("====== Descripción ======");
        if (pedido1.isActivo()) { System.out.println("Pedido activo"); }
        else { System.out.println("Pedido cerrado"); }
        System.out.println("Cliente: " + pedido1.getCliente().getNombre());
        System.out.println("Producto: " + pedido1.getProducto().getTipo() + "con ID: " + pedido1.getProducto().getId());
        System.out.println("Cantidad: " + pedido1.getCantidad());
        System.out.println("Tipo de Entrega: " + pedido1.getTipoEntrega().getDescripcion());
    }
}
