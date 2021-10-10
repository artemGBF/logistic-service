package ru.gbf.logisticservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Route {
    private Address start;
    private Address end;
    private List<Maneuver> maneuvers;
}
