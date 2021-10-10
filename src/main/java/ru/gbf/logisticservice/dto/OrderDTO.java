package ru.gbf.logisticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbf.logisticservice.model.Address;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long userId;
    private Address originAddr;
    private Address destinationAddr;
    private DeliveryType deliveryType;
    private PayType payType;

    private List<OrderGoodsDTO> orderGoods;
}
