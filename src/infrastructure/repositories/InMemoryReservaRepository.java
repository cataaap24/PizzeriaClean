package infrastructure.repositories;

import entities.Reserva;
import java.util.ArrayList;
import java.util.List;
import usecases.ports.ReservaRepository;

public class InMemoryReservaRepository implements ReservaRepository {
    private final List<Reserva> reservas = new ArrayList<>();

    @Override
    public void saveReservation(Reserva reservation) { reservas.add(reservation); }

    @Override
    public Reserva findById(String id) {
        for (Reserva reservation : reservas) {
            if (reservation.getId().equals(id)) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public List<Reserva> findAll() { return new ArrayList<>(reservas); }

    /*Metodo necesario para conocer que reservas no se han confirmado o cancelado, con el fin
    de delimitar la cantidad de reservas actualmente en curso (Máximo 3)*/
    @Override
    public List<Reserva> ReservasEnCurso() {
        ArrayList<Reserva> res_en_curso = new ArrayList<>();
        for (Reserva reservation : reservas) {
            if (reservation.estaActivo()) {
                res_en_curso.add(reservation);
            }
        }
        return res_en_curso;
    } 
}
