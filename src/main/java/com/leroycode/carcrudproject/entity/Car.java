package com.leroycode.carcrudproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String engine;

    @Column
    private String transmission;

    @Column
    private String pads;

    @Column
    private String rotors;

    @Column
    private String calipers;

    @Column
    private String shocks;

    @Column
    private String struts;

    @Column
    private String steeringRacks;

    @Column
    private String controlArms;

    @Column
    private String battery;

    @Column
    private String alternator;

    @Column
    private String starter;

    @Column
    private String headlights;

    @Column
    private String tailLights;

    @Column
    private String turnSignalLights;

    //default constructor
    public Car() {
    }

    public Car(String engine, String transmission, String pads, String rotors,
               String calipers, String shocks, String struts, String steeringRacks,
               String controlArms, String battery, String alternator, String starter,
               String headlights, String tailLights, String turnSignalLights) {
        this.engine = engine;
        this.transmission = transmission;
        this.pads = pads;
        this.rotors = rotors;
        this.calipers = calipers;
        this.shocks = shocks;
        this.struts = struts;
        this.steeringRacks = steeringRacks;
        this.controlArms = controlArms;
        this.battery = battery;
        this.alternator = alternator;
        this.starter = starter;
        this.headlights = headlights;
        this.tailLights = tailLights;
        this.turnSignalLights = turnSignalLights;

    }

}
