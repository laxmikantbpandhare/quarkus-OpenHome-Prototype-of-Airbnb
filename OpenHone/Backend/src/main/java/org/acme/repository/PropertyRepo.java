package org.acme.repository;


import org.acme.entity.Person;
import org.acme.entity.Property;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Qualifier("property")
@Transactional
public interface PropertyRepo extends JpaRepository<Property,Integer> {//} , JpaSpecificationExecutor {

    @Query("SELECT p FROM property p WHERE p.owner_id = ?1 AND p.status = 'Created'")
    List<Property> findByOwner(Person ownerId);

//    @Query("SELECT p FROM property p WHERE p.city like %city%")
//    List<Property> findByCityLike(String city);
}
