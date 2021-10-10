package ru.gbf.logisticservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbf.logisticservice.type.ManeuverType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Maneuver {
    private Address start;
    private Address end;
    private double distance;//in meters
    private double time;//in sec
    private ManeuverType maneuverType;
    private String instructions;
}
