package ru.gbf.logisticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockGoodDto {
    private Long goodId;
    private Long stockId;
    private Integer count;
}
