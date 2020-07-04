package org.acme.Service;

import org.acme.entity.Reservations;
import org.acme.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationService {

    @Autowired
    //@Qualifier("reservations")
    ReservationRepo reservationRepo;

    public Reservations getReservation(int id) {
        return reservationRepo.findById(id).orElse(null);
    }
}
