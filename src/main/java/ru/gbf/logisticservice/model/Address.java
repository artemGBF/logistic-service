package ru.gbf.logisticservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private Integer house;

    public String getAddressString() {
        return this.getCity() + "+" + this.getStreet() + "+" + this.getHouse();
    }
}
