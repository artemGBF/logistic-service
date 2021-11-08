package ru.gbf.logisticservice.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gbf.logisticservice.model.StockGood;
import ru.gbf.logisticservice.repository.StockGoodRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class StockGoodDAO {
    private final StockGoodRepository repository;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Long check(StockGood stockGood) {
        return repository.check(
                stockGood.getStockId(),
                stockGood.getGoodId()
        );
    }

    public Map<Long, Long> checkAll(List<Long> ids) {
        Map<String, Object> init = new HashMap<>();
        init.put("ids", ids);
        List<Map<Long, Long>> query = jdbcTemplate.query(
                "select good_id, count from xref_stock_2_goods where stock_id = 1 and good_id in (:ids)",
                init,
                (resultSet, i) -> {
                    Map<Long, Long> res = new HashMap<>();
                    res.put(
                            resultSet.getLong("good_id"),
                            resultSet.getLong("count")
                    );
                    return res;
                }
        );
        Map<Long, Long> checked = new HashMap<>();
        query.forEach(checked::putAll);
        return checked;
    }

    public void fill(List<StockGood> collect) {
        Map<String, Object>[] init = init(collect);
        jdbcTemplate.batchUpdate(
                "INSERT INTO xref_stock_2_goods VALUES (:stock, :good, :count)" +
                        "ON CONFLICT(stock_id, good_id) DO UPDATE SET count = :count + " +
                        "(select count from xref_stock_2_goods gc where gc.good_id = :good and gc.stock_id = :stock);",
                init
        );
    }

    public void order(List<StockGood> collect) {
        Map<String, Object>[] init = init(collect);
        jdbcTemplate.batchUpdate(
                "update xref_stock_2_goods SET count = - :count + " +
                        "(select count from xref_stock_2_goods gc where gc.good_id = :good and gc.stock_id = :stock)" +
                        "where good_id = :good and stock_id = :stock;",
                init
        );
    }

    private Map<String, Object>[] init(List<StockGood> list) {
        Map<String, Object>[] params = new Map[list.size()];
        for (int i = 0; i < list.size(); i++) {
            params[i] = new HashMap<>();
            StockGood stockGood = list.get(i);
            params[i].put("stock", stockGood.getStockId());
            params[i].put("good", stockGood.getGoodId());
            params[i].put("count", stockGood.getCount());
        }
        return params;
    }
}
