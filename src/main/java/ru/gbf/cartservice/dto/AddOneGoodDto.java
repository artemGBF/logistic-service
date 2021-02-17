package ru.gbf.cartservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AddOneGoodDto {
    private Long idGood;
    private Long idCart;
}
