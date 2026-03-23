package com.leroycode.carcrudproject.models;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record CarRequest(@NotBlank String engine, @NotBlank String transmission, @NotBlank String pads,
                         @NotBlank String rotors, @NotBlank String calipers, @NotBlank String shocks,
                         @NotBlank String struts, @NotBlank String steeringRacks, @NotBlank String controlArms,
                         @NotBlank String battery, @NotBlank String alternator, @NotBlank String starter,
                         @NotBlank String headlights, @NotBlank String tailLights, @NotBlank String turnSignalLights) {

}
