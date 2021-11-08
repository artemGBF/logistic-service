package ru.gbf.logisticservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbf.logisticservice.dao.StockGoodDAO;
import ru.gbf.logisticservice.dto.CountDto;
import ru.gbf.logisticservice.dto.StockGoodDto;
import ru.gbf.logisticservice.mapper.GoodStockMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockGoodService {
    private final StockGoodDAO stockGoodDao;
    private final GoodStockMapper mapper;

    public CountDto check(StockGoodDto dto) {
        return new CountDto(dto.getGoodId(), stockGoodDao.check(mapper.toEntity(dto)));
    }

    public void fill(List<StockGoodDto> dto) {
        stockGoodDao.fill(dto.stream().map(mapper::toEntity).collect(Collectors.toList()));
    }

    public void order(List<StockGoodDto> dto) {
        stockGoodDao.order(dto.stream().map(mapper::toEntity).collect(Collectors.toList()));
    }

    public List<CountDto> checkAll(List<Long> ids) {
        List<CountDto> list = new ArrayList<>();
        stockGoodDao.checkAll(ids).forEach(
                (aLong, aLong2) -> list.add(new CountDto(aLong, aLong2))
        );
        return list;
    }
}
