package usecases.services;

import entities.Cliente;
import entities.Pedido;
import entities.Producto;
import entities.Reglas;
import entities.TipoEntrega;
import usecases.dto.OperationResult;
import usecases.dto.ResumenPedido;
import usecases.ports.ClienteRepository;
import usecases.ports.IdGenerator;
import usecases.ports.PedidoRepository;
import usecases.ports.ProductoRepository;

public class RealizarPedido {
    private final PedidoRepository pedidos;
    private final ProductoRepository menu;
    private final ClienteRepository clientes;
    private final IdGenerator idGenerator;
    private final CalculadoraTotal calculadora;

    public RealizarPedido(PedidoRepository pedidos, ProductoRepository menu, ClienteRepository clientes, IdGenerator idGenerator, CalculadoraTotal calculadora) {
        this.pedidos = pedidos;
        this.menu = menu;
        this.clientes = clientes;
        this.idGenerator = idGenerator;
        this.calculadora = calculadora;
    }

    public OperationResult registrar(String idCliente, String idProducto, int cantidad, TipoEntrega tipoEntrega) {

        Cliente client = clientes.findById(idCliente);
        Producto product = menu.findById(idProducto);

        if (client == null) return OperationResult.fail("Cliente no encontrado.");

        if (product == null) return OperationResult.fail("Producto no encontrado.");

        if (!product.estaDisponible()) return OperationResult.fail("El producto '" + product.getNombre() + "' no está disponible actualmente.");

        if (cantidad < 1 || cantidad > Reglas.MAX_CANT) return OperationResult.fail("Cantidad inválida. Debe pedir entre 1 y " + Reglas.MAX_CANT);

        try {
            Pedido order = new Pedido(idGenerator.nextId("PED") , client, product, cantidad, tipoEntrega);
            order.pedir();
            pedidos.saveOrder(order);
 
            double valorBase = product.calcularValorBase();
            double subtotal = valorBase * cantidad;
            double descuento;
            if (cantidad >= Reglas.CANT_DESCUENTO) {
                descuento = subtotal * 0.20; 
            } else {
                descuento = 0; 
            }
            double total = calculadora.calcular(valorBase, cantidad, tipoEntrega);
            ResumenPedido resumen = new ResumenPedido(order, valorBase, subtotal, total, descuento);
            return OperationResult.ok("Pedido registrado exitosamente. ", resumen);

        } catch (Exception e) {
            return OperationResult.fail("Error al registrar pedido: " + e.getMessage());
        } 
    }
}
