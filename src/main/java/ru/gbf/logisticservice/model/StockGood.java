package ru.gbf.logisticservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("stock_good")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockGood {

    private Long idGood;
    private Long idStock;
    private Integer count;
}



