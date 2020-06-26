package org.acme;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
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
