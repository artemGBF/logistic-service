package ru.gbf.cartservice.dto;

import lombok.Data;

@Data
public class CartGoodDto {
    private Long idCart;
    private Long idGood;
    private int count;
}
