package org.acme.Controller;


import org.acme.Service.PropertyService;
import org.acme.Service.ReservationService;
import org.acme.Service.TimeService;
import org.acme.entity.Property;
import org.acme.entity.Reservations;
import org.acme.repository.PersonJPARepo;
import org.acme.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class ReservationController {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    PropertyService propertyService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    TimeService timeService;

    @Autowired
    PersonJPARepo personJPARepo;

    @PostMapping("/reservation/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> createReservation(@RequestBody Reservations reservation) throws IOException {//throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        int propertyId=reservation.getPropertyId();
        Property property=propertyService.getProperty(propertyId);
        property.addReservation(reservation);
        reservationRepo.save(reservation);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
