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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;


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


    // NOT tested did not found API on frontend
    @PostMapping("/reservation/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> createReservation(@RequestBody Reservations reservation) throws IOException {//throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        int propertyId=reservation.getPropertyId();
        Property property=propertyService.getProperty(propertyId);
        property.addReservation(reservation);
        reservationRepo.save(reservation);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/reservation/new")
    @ResponseStatus(value = HttpStatus.CREATED)
    //public Reservations(float bookedPrice, float bookedPriceWeekend, float bookedPriceWeekday, OffsetDateTime bookingDate, OffsetDateTime startDate, OffsetDateTime endDate, int guestId, int propertyId) {
    public ResponseEntity<?> newReservation(@RequestBody Map<String, String> payload) throws ParseException{//, MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        System.out.println("payload"+payload);
        String bookedPrice = (String)payload.get("bookedPrice");
        int booked_price=Integer.parseInt(bookedPrice);
        String bookedPriceWeekend = (String)payload.get("bookedPriceWeekend");//=payload.get(payload.keySet().toArray()[1]);
        int booked_price_weekend=Integer.parseInt(bookedPriceWeekend);

        String bookedPriceWeekday =(String)payload.get("bookedPriceWeekday");//= payload.get(payload.keySet().toArray()[2]);

        int booked_price_weekday=Integer.parseInt(bookedPriceWeekday);


        String bookingDate = (String)payload.get("bookingDate");//=payload.get(payload.keySet().toArray()[3]);

        System.out.println(bookingDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf1.parse(bookingDate);
        OffsetDateTime booking_date = date1.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("booking_date"+booking_date);
        String startDate =(String)payload.get("startDate");//= payload.get(payload.keySet().toArray()[4]);
        System.out.println("startDate"+startDate);
        System.out.println(startDate);
        java.util.Date date2 = sdf1.parse(startDate);
        OffsetDateTime start_date = date2.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("start_date "+start_date);
        String endDate = (String)payload.get("endDate");//=payload.get(payload.keySet().toArray()[5]);
        System.out.println("endDate "+endDate );
        System.out.println(endDate);
        java.util.Date date3 = sdf1.parse(endDate);
        OffsetDateTime end_date = date3.toInstant()
                .atOffset(ZoneOffset.UTC);

        System.out.println("end_date "+end_date );
        String guestId =(String)payload.get("guestId");//= payload.get(payload.keySet().toArray()[6]);

        System.out.println("guestId"+guestId);
        int guest_id=Integer.parseInt(guestId);

        String propertyId =(String)payload.get("propertyId");//= payload.get(payload.keySet().toArray()[7]);

        System.out.println("propertyId"+propertyId);

        int id=Integer.parseInt(propertyId);
        Property property=propertyService.getProperty(id);

        String address =(String)payload.get("address");//= payload.get(payload.keySet().toArray()[8]);
        System.out.println("address "+address);
        String description =(String)payload.get("description");//= payload.get(payload.keySet().toArray()[9]);
        System.out.println("description "+description);


        Reservations reservation=new Reservations(booked_price,booked_price_weekend,booked_price_weekday,booking_date, start_date, end_date,guest_id,id,address,description);
        reservation.setStatus("Booked");
        reservation.setState("Booked");
        property.addReservation(reservation);
        reservationRepo.save(reservation);

        String recevier = personJPARepo.findById(reservation.getGuestId()).getEmail();

//        if(!recevier.equals("")) {
//            System.out.println("mailsent");
//            SendMail y = new SendMail();
//            y.sendEmail("You have successfully booked your home at Open Home.", recevier,
//                    "You have successfully booked your home at Open Home. Thank you for considering OpenHome\n\n For more details check the dashboard.\n\n " +
//                            "Thanks and Regards, \n OpenHome Team");
//        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
