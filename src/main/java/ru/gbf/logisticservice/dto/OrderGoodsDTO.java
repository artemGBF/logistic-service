package ru.gbf.logisticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoodsDTO {
    private Long orderId;
    private Long goodId;
    private int count;
}
