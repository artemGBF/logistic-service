package ru.gbf.logisticservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gbf.logisticservice.dto.CountDto;
import ru.gbf.logisticservice.dto.StockGoodDto;
import ru.gbf.logisticservice.service.StockGoodService;

import java.util.List;

@RestController
@RequestMapping("/api/stockpile")
@AllArgsConstructor
public class StockpileController {

    private final StockGoodService stockGoodService;

    @PostMapping("/checkCount")
    @Operation(summary = "Проверка количества товара на складе")
    public CountDto getAllGoodsOfStock(@RequestBody StockGoodDto dto) {
        return stockGoodService.check(dto);
    }

    @PostMapping("/checkAllCount")
    @Operation(summary = "Проверка количества товара на складе пачкой")
    public List<CountDto> checkFromStock(@RequestBody List<Long> ids) {
        return stockGoodService.checkAll(ids);
    }

    @PostMapping("/fill")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Заполнение склада")
    //todo -> queue
    public void fill(@RequestBody List<StockGoodDto> dto) {
        stockGoodService.fill(dto);
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Заказ товаров")
    public void order(@RequestBody List<StockGoodDto> dto) {
        stockGoodService.order(dto);
    }
}
