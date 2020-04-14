package org.acme.hibernate.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "FRUIT_TABLE")
public class Fruit extends PanacheEntity {

    @Column(name = "FRUIT_NAME", length = 40, unique = true)
    private String name;

    @Column(name = "FRUIT_COLOR", length = 40)
    private String color;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
