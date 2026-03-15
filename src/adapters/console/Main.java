package adapters.console;


import entities.*;
import infrastructure.repositories.*;
import infrastructure.services.SimpleIdGenerator;
import java.util.List;
import java.util.Scanner;
import usecases.dto.*;
import usecases.ports.IdGenerator;
import usecases.services.*;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {

        PizzaAnimation.mostrar();

        IdGenerator idGen = new SimpleIdGenerator();
        PizzeriaApp atlas = new PizzeriaApp("Papa's Pizzeria", new InMemoryClienteRepository(), 
            new InMemoryProductoRepository(), new InMemoryPedidoRepository(), new InMemoryReservaRepository(),idGen);

        cargaInicialDeDatos(atlas);

        try (Scanner sc = new Scanner(System.in)) {
            int op;
            do {
                System.out.println("\n── " + atlas.getNombre() + " ──");
                menuPrincipal();
                op = leerEntero(sc);
                
                try {
                    switch (op) {
                        case 1 -> imprimirLista("Menú", atlas.mostrarMenu());
                        case 2 -> imprimirLista("Clientes", atlas.mostrarClientes());
                        case 3 -> {
                            System.out.println("\n--- NUEVO PEDIDO ---");
                            imprimirLista("Clientes", atlas.mostrarClientes());
                            System.out.print("ID Cliente: ");
                            String idCliente = sc.nextLine();
                            imprimirLista("Menú", atlas.mostrarMenu());
                            System.out.print("ID Producto: ");
                            String idProducto = sc.nextLine();
                            System.out.print("Cantidad: ");
                            int cantidad = leerEntero(sc);
                            TipoEntrega tipo = elegirTipoEntrega(sc);
                            OperationResult result = atlas.realizarPedido(idCliente, idProducto, cantidad, tipo);
                            System.out.println(result.getMessage());
                            if (result.isSuccess() && result.getResumen() != null) {
                                imprimirTicket(result.getResumen());
                            }
                        }
                        case 4 -> {
                            System.out.println("\n--- ENTREGAR PEDIDO ---");
                            List<Pedido> activos = atlas.mostrarPedidosActivos();
                            imprimirLista("Pedidos Activos", activos);
                            if (!activos.isEmpty()) {
                                System.out.print("ID pedido a entregar: ");
                                OperationResult result = atlas.entregarPedido(sc.nextLine());
                                System.out.println(result.getMessage());
                            }
                        }

                        case 5 -> {
                            System.out.println("\n--- CANCELAR PEDIDO ---");
                            List<Pedido> activos = atlas.mostrarPedidosActivos();
                            imprimirLista("Pedidos Activos", activos);
                            if (!activos.isEmpty()) {
                                System.out.print("ID Pedido a cancelar: ");
                                OperationResult result = atlas.cancelarPedido(sc.nextLine());
                                System.out.println(result.getMessage());
                            }
                        }
                        case 6 -> imprimirLista("Pedidos", atlas.mostrarPedidos());
                        case 7 -> {
                            System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");
                            System.out.print("Nombre completo: ");
                            String nombre = sc.nextLine();
                            System.out.print("Teléfono (10 dígitos): ");

                            String telefono = sc.nextLine();
                            OperationResult result = atlas.registrarCliente(nombre, telefono);
                            System.out.println(result.getMessage());
                        }

                        case 8 -> {
                            System.out.println("\n--- NUEVA RESERVA ---");
                            imprimirLista("Clientes", atlas.mostrarClientes());
                            System.out.print("ID Cliente: ");
                            String idCliente = sc.nextLine();
                            System.out.print("Número de personas: ");
                            int personas = leerEntero(sc);
                            System.out.print("Hora de llegada (ej: 19:30): ");
                            String hora = sc.nextLine();
                            OperationResult result = atlas.registrarReserva(idCliente, personas, hora);
                            System.out.println(result.getMessage());
                        }

                        case 9 -> {
                            System.out.println("\n--- CONFIRMAR RESERVA ---");
                            List<Reserva> activas = atlas.mostrarReservasActivas();
                            imprimirLista("Reservas Activas", activas);
                            if (!activas.isEmpty()) {
                                System.out.print("ID Reserva a confirmar: ");
                                OperationResult result = atlas.confirmarReserva(sc.nextLine());
                                System.out.println(result.getMessage());
                            }
                        }

                        case 10 -> {
                            System.out.println("\n--- CANCELAR RESERVA ---");
                            List<Reserva> activas = atlas.mostrarReservasActivas();
                            imprimirLista("Reservas Activas", activas);
                            if (!activas.isEmpty()) {
                                System.out.print("ID Reserva a cancelar: ");
                                OperationResult result = atlas.cancelarReserva(sc.nextLine());
                                System.out.println(result.getMessage());
                            }
                        }
                        case 11 -> imprimirLista("Listado de Reservas", atlas.mostrarReservaciones());
                        case 0 -> System.out.println("Saliendo del sistema...");
                        default -> System.out.println("Opción no válida");
                    }
                } catch (DomainException e) {
                    System.err.println("\nERROR: " + e.getMessage());
                }
            } while (op != 0);
        }
    }

    private static void menuPrincipal() {
        System.out.println("1)  Mostrar Menú");
        System.out.println("2)  Listar Clientes");
        System.out.println("3)  Realizar Pedido");
        System.out.println("4)  Entregar Pedido");
        System.out.println("5)  Cancelar Pedido");
        System.out.println("6)  Listar Pedidos");
        System.out.println("7)  Registrar Cliente");
        System.out.println("8)  Nueva Reserva");
        System.out.println("9)  Confirmar Reserva Realizada");
        System.out.println("10) Cancelar Reserva");
        System.out.println("11) Listar Reservas");
        System.out.println("0)  Salir");
        System.out.print("Opción: ");
    }

    private static void cargaInicialDeDatos(PizzeriaApp pizz) {
        pizz.registrarCliente("Martin Marco", "3132300222");
        pizz.registrarCliente("Arturo Arias", "3132302122");
        pizz.registrarCliente("Maria Mar", "3132303344");

        pizz.registrarPizza("Placer de antano", "M", "Pina - Peperoni");
        pizz.registrarPizza("Camaronzon", "G", "Camarones - Salsa");
        pizz.registrarPizza("Carnivora Suprema", "G", "Carne molida - Chorizo - Peperoni");
        pizz.registrarPizza("Veggie Deluxe", "P", "Champinones - Pimenton - Cebolla - Maiz");
        pizz.registrarPizza("Mexicana Picante", "M", "Carne - Jalapenos - Nachos triturados");
        pizz.registrarBebida("Cocacola", 350);
        pizz.registrarBebida("Sprite", 350);
        pizz.registrarBebida("Agua", 300);
    }

    private static void imprimirTicket(ResumenPedido resumen) {
        System.out.println("\n========================================");
        System.out.println("           RESUMEN DEL PEDIDO           ");
        System.out.println("========================================");
        System.out.println(" ID Pedido  : " + resumen.getOrder().getId());
        System.out.println(" Fecha      : " + resumen.getOrder().getFecha());
        System.out.println(" Cliente    : " + resumen.getOrder().getCliente().getNombre());
        System.out.println(" Producto   : " + resumen.getOrder().getProducto().getNombre());
        System.out.println(" Cantidad   : " + resumen.getOrder().getCantidad());
        System.out.println(" Entrega    : " + resumen.getOrder().getTipoEntrega());
        System.out.println("----------------------------------------");
        System.out.printf( " Valor Base : $%,.2f%n", (resumen.getValorBase()));
        System.out.printf( " Subtotal   : $%,.2f%n", (resumen.getSubtotal()));
        System.out.printf( " Descuento  : $%,.2f%n", resumen.getDescuento());
        System.out.printf( " TOTAL      : $%,.2f%n", resumen.getTotal());
        System.out.println("========================================\n");
    }

    private static TipoEntrega elegirTipoEntrega(Scanner sc) {
        System.out.println("Tipo de entrega:");
        System.out.println(" 1) " + TipoEntrega.PARA_AQUI);
        System.out.println(" 2) " + TipoEntrega.PARA_LLEVAR);
        System.out.println(" 3) " + TipoEntrega.DOMICILIO);
        System.out.print("Opcion: ");
    
        int opcion = leerEntero(sc); 
    
        switch (opcion) {
            case 1 -> {
                return TipoEntrega.PARA_AQUI;
            }
            case 2 -> {
                return TipoEntrega.PARA_LLEVAR;
            }
            case 3 -> {
                return TipoEntrega.DOMICILIO;
            }
            default -> {
                System.out.println("Opción no válida, asignando para comer aquí.");
                return TipoEntrega.PARA_AQUI;
            }
        }
    }
    
    private static <T> void imprimirLista(String titulo, List<T> objetos) {
        if (objetos.isEmpty()) {
            System.out.println("No hay " + titulo.toLowerCase() + ".");
            return;
        }
        System.out.println("\n--- " + titulo + " ---");
        for (T o : objetos){
            System.out.println(o);
        }
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Ingrese un número válido: ");
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }
}
