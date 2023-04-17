package com.example._Buzila_Andra_Court_Reserve_Backend.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Tariff implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    //Constructor without id:
    public Tariff(Location location) {
        this.location = location;
    }

    //Constructor:
    public Tariff() {
    }
}
