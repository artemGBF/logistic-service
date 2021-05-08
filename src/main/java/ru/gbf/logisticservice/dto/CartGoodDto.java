package ru.gbf.logisticservice.dto;

import lombok.Data;

@Data
public class CartGoodDto {
    private Long idCart;
    private Long idGood;
    private int count;
}
