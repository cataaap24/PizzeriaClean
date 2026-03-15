package usecases.services;

import entities.Cliente;
import entities.Reglas;
import entities.Reserva;
import usecases.dto.OperationResult;
import usecases.ports.ClienteRepository;
import usecases.ports.IdGenerator;
import usecases.ports.ReservaRepository;

/* Testearlo ahorita, no estoy seguro de que maneje los limites de reservas activas */

public class RegistrarReserva {
    private final ReservaRepository reservaciones;
    private final ClienteRepository clientes;
    private final IdGenerator idGenerator;

    public RegistrarReserva(ReservaRepository reservaciones, ClienteRepository clientes, IdGenerator idGenerator) {
        this.reservaciones = reservaciones;
        this.clientes = clientes;
        this.idGenerator = idGenerator;
    }

    public OperationResult registrar(String idCliente, int numPersonas, String hora) {

        Cliente client = clientes.findById(idCliente);

        if (client == null) return OperationResult.fail("Cliente no encontrado.");

        if (reservaciones.ReservasEnCurso().size() == Reglas.MAX_CANT_RESERVAS) {
            System.out.println("===== Reservas activas =====");
            for (Reserva reservation : reservaciones.ReservasEnCurso()) {
                System.out.println(reservation);
            } 
            return OperationResult.fail("Máxima cantidad de reservaciones activas, por favor, confirme o cancele al menos una reservación activa.");
        }

        if (numPersonas < 1 || numPersonas > Reglas.MAX_CANT_PERSONAS_X_RESERVA) return OperationResult.fail("Cantidad inválida de personas en la reservación. Por favor, ingrese un número entre 1 y " + Reglas.MAX_CANT_PERSONAS_X_RESERVA + ".");

        try {
            Reserva r = new Reserva(idGenerator.nextId("RES"), client, numPersonas, hora);
            r.pedir();
            reservaciones.saveReservation(r);
            return OperationResult.ok("Reserva registrada exitosamente.");
        } catch (Exception e) {
            return OperationResult.fail("Error en la reservación: " + e.getMessage());
        }
    }
}
