package usecases.services;

import entities.Reserva;
import usecases.dto.OperationResult;
import usecases.ports.ReservaRepository;

public class CancelarReserva {
    private final ReservaRepository reservaciones;

    public CancelarReserva(ReservaRepository reservaciones) {
        this.reservaciones = reservaciones;
    }

    public OperationResult cancelar(String idReserva) {
        Reserva reservation = reservaciones.findById(idReserva);

        if (reservation == null) {
            return OperationResult.fail("Reserva no encontrada con ID: " + idReserva);
        }

        try {
            reservation.cancelar();
            return OperationResult.ok("Reserva " + idReserva + " cancelada.");
        } catch (IllegalStateException e) {
            return OperationResult.fail(e.getMessage());
        }
    }
}
