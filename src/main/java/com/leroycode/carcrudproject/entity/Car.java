package com.leroycode.carcrudproject.entity;

import jakarta.persistence.*;
import lombok.Setter;

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

    public long getId() {
        return this.id;
    }

    public String getEngine() {
        return this.engine;
    }

    public String getTransmission() {
        return this.transmission;
    }

    public String getPads() {
        return this.pads;
    }

    public String getRotors() {
        return this.rotors;
    }

    public String getCalipers() {
        return this.calipers;
    }

    public String getShocks() {
        return this.shocks;
    }

    public String getStruts() {
        return this.struts;
    }

    public String getSteeringRacks() {
        return this.steeringRacks;
    }

    public String getControlArms() {
        return this.controlArms;
    }

    public String getBattery() {
        return this.battery;
    }

    public String getAlternator() {
        return this.alternator;
    }

    public String getStarter() {
        return this.starter;
    }

    public String getHeadlights() {
        return this.headlights;
    }

    public String getTailLights() {
        return this.tailLights;
    }

    public String getTurnSignalLights() {
        return this.turnSignalLights;
    }
}
