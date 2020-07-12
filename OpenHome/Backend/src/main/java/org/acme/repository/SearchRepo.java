package org.acme.repository;

import org.acme.entity.Person;
import org.acme.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepo extends JpaRepository<Property,Integer> {
//'%"+city1+"%'
    @Query("SELECT p FROM Property p WHERE p.city LIKE ?1")
    List<Property> findByCity(String city);

    @Query("SELECT p FROM Property p WHERE p.city LIKE ?1 AND p.propertyDescription LIKE ?2")
    List<Property> findByCity1(String city,String desc);

}
