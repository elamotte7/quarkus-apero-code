package org.acme.hibernate.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity(name = "FRUIT_TABLE")
@Cacheable
public class Fruit extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "hibernateSequence",
            sequenceName = "hibernate_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSequence")
    public Integer id;

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
