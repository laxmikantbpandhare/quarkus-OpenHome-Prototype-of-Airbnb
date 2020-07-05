package org.acme.repository;

import org.acme.entity.Reservations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("reservations")
@Repository
public interface ReservationRepo extends JpaRepository<Reservations,Integer> { //}, JpaSpecificationExecutor {

    //  List<Reservations> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(OffsetDateTime start_date, OffsetDateTime end_date);

    @Query("SELECT r FROM Reservations r WHERE r.guestId = ?1")
    List<Reservations> findByGuestId(int guestId);

    @Query("SELECT r FROM Reservations r WHERE r.propertyId = ?1")
    List<Reservations> findByPropertyId(int propertyId);

//    @Query("SELECT * FROM reservations r WHERE r.end_date <= ?1 AND r.status='Payment Processed'")
//    List<Reservations> findAllByEndDate(OffsetDateTime current_date);
//
//    @Query("SELECT * FROM reservations r WHERE r.start_date <= ?1 AND r.status='Booked'")
//    List<Reservations> findAllByStartDate(OffsetDateTime current_date);

    List<Reservations> findAllByStatusEqualsAndCheckInDateIsNotNull(String status);

    List<Reservations> findAllByStatusEquals(String status);



//    @Query("SELECT p FROM person p WHERE p.name = ?1")
//    Person findByName(String name);


}
