package ru.gbf.logisticservice.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gbf.logisticservice.model.StockGood;

public interface StockGoodRepository extends CrudRepository<StockGood, Long> {

    @Query("select gc.count from stock_good gc where gc.id_good = :idGood and gc.id_stock=:idStock")
    Long check(@Param("idStock") Long idStock, @Param("idGood") Long idGood);

}
