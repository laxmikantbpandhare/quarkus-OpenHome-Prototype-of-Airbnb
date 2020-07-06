package org.acme.repository;

import org.acme.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepo extends JpaRepository<Property,Integer> {

    // @Query("SELECT p FROM property p WHERE p.city LIKE ?1%")
//    List<Property> findByCityLike(String city);

}
