package org.acme.entity;

import javax.persistence.*;

@Entity
@Table(name="property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", nullable = false)
    private int propertyId;

    public Property(){
    }

}
