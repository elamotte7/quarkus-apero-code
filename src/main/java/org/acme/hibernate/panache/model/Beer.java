package org.acme.hibernate.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity(name = "BEER_TABLE")
@Cacheable
public class Beer extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "hibernateSequence",
            sequenceName = "hibernate_sequence",
            allocationSize = 5,
            initialValue = 6)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSequence")
    public Integer id;

    @Column(name = "BEER_NAME", length = 40)
    public String name;

    @Column(name = "BEER_COLOR", length = 40)
    public String color;

    @Column(name = "TASTED")
    public Boolean tasted;

    @Transient
    public int order;

}
