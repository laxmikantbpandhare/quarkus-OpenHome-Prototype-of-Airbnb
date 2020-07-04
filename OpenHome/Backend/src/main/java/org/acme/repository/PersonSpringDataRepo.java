package org.acme.repository;

import org.acme.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonSpringDataRepo extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM person p WHERE p.name = ?1")
    Person findByName(String name);

    @Query("SELECT p FROM Person p WHERE p.email = ?1")
    Person findByEmail(String email);
}