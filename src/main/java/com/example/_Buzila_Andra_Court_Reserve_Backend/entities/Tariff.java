package com.example._Buzila_Andra_Court_Reserve_Backend.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Tariff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    //@Type(type = "uuid-binary")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "criterion", nullable = false)
    private String criterion;

    @Column(name = "value", nullable = false)
    private float value;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Tariff() {
    }

    public Tariff(UUID id, String criterion, float value, Location location) {
        this.id = id;
        this.criterion = criterion;
        this.value = value;
        this.location = location;
    }

    public Tariff(String criterion, float value, Location location) {
        this.criterion = criterion;
        this.value = value;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
