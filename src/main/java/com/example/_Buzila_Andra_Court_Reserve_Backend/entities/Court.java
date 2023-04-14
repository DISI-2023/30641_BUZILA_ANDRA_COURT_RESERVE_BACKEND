package com.example._Buzila_Andra_Court_Reserve_Backend.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Court implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

//    @Column(name = "location", nullable = false)
//    private String location;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "name", nullable = false)
    private String name;

    //Constructor with id:
    public Court(UUID id, Location location, String type, String name) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.name = name;
    }

    //Constructor without id:
    public Court(Location location, String type, String name) {
        this.location = location;
        this.type = type;
        this.name = name;
    }

    //Empty constructor:
    public Court() {
    }

    //Getters and Setters:
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}