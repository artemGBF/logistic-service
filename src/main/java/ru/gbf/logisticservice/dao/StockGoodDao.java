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
public class StockGoodDao {
    private final StockGoodRepository repository;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private Map<String, Object>[] init(List<StockGood> list) {
        Map<String, Object>[] params = new Map[list.size()];
        for (int i = 0; i < list.size(); i++) {
            params[i] = new HashMap<>();
            StockGood stockGood = list.get(i);
            params[i].put("stock", stockGood.getIdStock());
            params[i].put("good", stockGood.getIdGood());
            params[i].put("count", stockGood.getCount());
        }
        return params;
    }

    public Long check(StockGood stockGood) {
        return repository.check(
                stockGood.getIdStock(),
                stockGood.getIdGood()
        );
    }

    public Map<Long, Long> checkAll(List<Long> ids) {
        Map<String, Object> init = new HashMap<>();
        init.put("ids", ids);
        List<Map<Long, Long>> query = jdbcTemplate.query(
                "select id_good, count from stock_good where id_stock = 1 and id_good in (:ids)",
                init,
                (resultSet, i) -> {
                    Map<Long, Long> res = new HashMap<>();
                    res.put(
                            resultSet.getLong("id_good"),
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
                "INSERT INTO stock_good VALUES (:stock, :good, :count)" +
                        "ON CONFLICT(id_stock, id_good) DO UPDATE SET count = :count + " +
                        "(select count from stock_good gc where gc.id_good = :good and gc.id_stock = :stock);",
                init
        );
    }

    public void order(List<StockGood> collect) {
        Map<String, Object>[] init = init(collect);
        jdbcTemplate.batchUpdate(
                "update stock_good SET count = - :count + " +
                        "(select count from stock_good gc where gc.id_good = :good and gc.id_stock = :stock)" +
                        "where id_good = :good and id_stock = :stock;",
                init
        );
    }
}
