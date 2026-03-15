package usecases.services;

import entities.Reserva;
import usecases.dto.OperationResult;
import usecases.ports.ReservaRepository;

public class ConfirmarReserva {
    private final ReservaRepository reservaciones;

    public ConfirmarReserva(ReservaRepository reservaciones) {
        this.reservaciones = reservaciones;
    }

    public OperationResult confirmar(String idReserva) {
        Reserva reservation = reservaciones.findById(idReserva);

        if (reservation == null) {
            return OperationResult.fail("Reserva no encontrada con ID: " + idReserva);
        }

        try {
            reservation.entregar();
            return OperationResult.ok("Reserva " + idReserva + " confirmada.");
        } catch (IllegalStateException e) {
            return OperationResult.fail(e.getMessage());
        }
    }
}
