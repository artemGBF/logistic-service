package ru.gbf.logisticservice.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gbf.logisticservice.model.StockGood;

public interface StockGoodRepository extends CrudRepository<StockGood, Long> {

    @Query("select gc.count from xref_stock_2_goods gc where gc.good_id = :idGood and gc.stock_id=:idStock")
    Long check(@Param("idStock") Long idStock, @Param("idGood") Long idGood);

}
