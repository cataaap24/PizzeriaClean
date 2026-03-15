package usecases.services;

import entities.Cliente;
import entities.Pedido;
import entities.Producto;
import entities.Reserva;
import entities.TipoEntrega;
import java.util.List;
import usecases.dto.OperationResult;
import usecases.ports.*;

public class PizzeriaApp {
    private final String nombre;

    private final ProductoRepository productoRepo;
    private final ClienteRepository clienteRepo;
    private final PedidoRepository pedidoRepo;
    private final ReservaRepository reservaRepo;

    private final RegistrarCliente registrarCliente;
    private final RegistrarPizza registrarPizza;
    private final RegistrarBebida registrarBebida;
    private final RealizarPedido realizarPedido;
    private final EntregarPedido entregarPedido;
    private final CancelarPedido cancelarPedido;
    private final RegistrarReserva registrarReserva;
    private final ConfirmarReserva confirmarReserva;
    private final CancelarReserva cancelarReserva;
    
    public PizzeriaApp(String nombre, ClienteRepository clienteRepo, ProductoRepository productoRepo, PedidoRepository pedidoRepo, ReservaRepository reservaRepo, IdGenerator idGenerator) {
        this.nombre = nombre;
        this.clienteRepo = clienteRepo;
        this.productoRepo = productoRepo;
        this.pedidoRepo = pedidoRepo;
        this.reservaRepo = reservaRepo;

        CalculadoraTotal calc = new CalculadoraTotal();
        this.registrarCliente = new RegistrarCliente(clienteRepo, idGenerator);
        this.registrarPizza = new RegistrarPizza(productoRepo, idGenerator);
        this.registrarBebida = new RegistrarBebida(productoRepo, idGenerator);
        this.realizarPedido = new RealizarPedido(pedidoRepo, productoRepo, clienteRepo, idGenerator, calc);
        this.entregarPedido = new EntregarPedido(pedidoRepo);
        this.cancelarPedido = new CancelarPedido(pedidoRepo);
        this.registrarReserva = new RegistrarReserva(reservaRepo, clienteRepo, idGenerator);
        this.confirmarReserva = new ConfirmarReserva(reservaRepo);
        this.cancelarReserva = new CancelarReserva(reservaRepo);
    }

    public String getNombre() { return this.nombre; }

    //Métodos para mostrar

    public List<Cliente> mostrarClientes() { return clienteRepo.findAll(); }
    public List<Producto> mostrarMenu() { return productoRepo.findAll(); }
    public List<Pedido> mostrarPedidos() { return pedidoRepo.findAll(); }
    public List<Reserva> mostrarReservaciones() { return reservaRepo.findAll(); }
    public List<Reserva> mostrarReservasActivas() { return reservaRepo.ReservasEnCurso(); }
    public List<Pedido> mostrarPedidosActivos() { return pedidoRepo.findActivos(); }

    //Métodos de ejecución
    public OperationResult registrarCliente(String nombre, String telefono) { return registrarCliente.registrar(nombre, telefono); }
    public OperationResult registrarPizza(String nombre, String tamano, String sabor) { return registrarPizza.registrar(nombre, tamano, sabor); }
    public OperationResult registrarBebida(String nombre, int volumenMl) { return registrarBebida.registrar(nombre, volumenMl); }

    public OperationResult realizarPedido(String idCliente, String idProducto, int cantidad, TipoEntrega tipoEntrega) {
        return realizarPedido.registrar(idCliente, idProducto, cantidad, tipoEntrega);
    }
    public OperationResult entregarPedido(String idPedido) { return entregarPedido.entregar(idPedido); }
    public OperationResult cancelarPedido(String idPedido) { return cancelarPedido.cancelar(idPedido); }

    public OperationResult registrarReserva(String idCliente, int numPersonas, String hora) {
        return registrarReserva.registrar(idCliente, numPersonas, hora);
    }
    public OperationResult confirmarReserva(String idReserva) { return confirmarReserva.confirmar(idReserva); }
    public OperationResult cancelarReserva(String idReserva) { return cancelarReserva.cancelar(idReserva); }
}

    
