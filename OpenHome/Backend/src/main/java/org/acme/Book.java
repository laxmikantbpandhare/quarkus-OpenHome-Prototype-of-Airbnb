package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends PanacheEntity {

    //check @id of normal JPA into QUarkus one PanacheEntity
   // @Id
    private int bid;

    private String name;

    private int publicationYear;

    public int getBid() {
        return bid;
    }

    public String getName() {
        return name;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}
