package ru.gbf.logisticservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("xref_stock_2_goods")
public class StockGood {
    private Long goodId;
    private Long stockId;
    private Integer count;
}



