package usecases.ports;

import entities.Reserva;
import java.util.List;

public interface ReservaRepository {
    void saveReservation(Reserva reservation);
    Reserva findById(String id);
    List<Reserva> findAll();
    List<Reserva> ReservasEnCurso();
}
