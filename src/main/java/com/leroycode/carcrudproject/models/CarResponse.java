package com.leroycode.carcrudproject.models;

public record CarResponse(long id, String engine, String transmission, String pads, String rotors,
                           String calipers, String shocks, String struts, String steeringRacks,
                           String controlArms, String battery, String alternator, String starter,
                           String headlights, String tailLights, String turnSignalLights) {
}
