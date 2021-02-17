package ru.gbf.cartservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.gbf.cartservice.dto.AddGoodsDto;
import ru.gbf.cartservice.dto.CartDTO;
import ru.gbf.cartservice.dto.AddOneGoodDto;
import ru.gbf.cartservice.errors.ResourceLackException;
import ru.gbf.cartservice.model.Cart;
import ru.gbf.cartservice.model.GoodStock;
import ru.gbf.cartservice.service.CartService;

@RestController
@RequestMapping(value = "/api/cart", produces = "application/json")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{id}")
    @Operation(summary = "Получение содержимого корзины")
    public CartDTO showCart(@PathVariable Long id) {
        return service.getAllFromCart(id);
    }

    @PostMapping("/create/{idUser}")
    @Operation(summary = "Создание корзины для пользователя")
    public Cart create(@PathVariable Long idUser) {
        return service.createCart(idUser);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Первичное добавление товара")
    public void addGood(@RequestBody AddOneGoodDto goodForAddDTO) {
        HttpEntity<GoodStock> httpEntity = new HttpEntity<>(
                new GoodStock(
                        goodForAddDTO.getIdGood(),
                        1L,
                        1
                )
        );
        Long aLong = restTemplate.postForObject(
                "http://localhost:8080/api/stockpile/check",
                httpEntity,
                Long.class,
                (Object) null);
        if (aLong == 0) {
            throw new ResourceLackException();
        }
        service.addGood(goodForAddDTO);
    }

    @PostMapping("/batchadd")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Первичное добавление товара")
    public void addGoods(@RequestBody AddGoodsDto goods) {
        HttpEntity<GoodStock> httpEntity = new HttpEntity<>(
                new GoodStock(
                        goods.getIdGood(),
                        1L,
                        goods.getCount()
                )
        );
        Long aLong = restTemplate.postForObject(
                "http://localhost:8080/api/stockpile/check",
                httpEntity,
                Long.class,
                (Object) null);
        if (aLong - goods.getCount() <= 0) {
            throw new ResourceLackException(goods.getCount());
        }
        service.addGoods(goods);
    }

    @PatchMapping("/clear")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    @Operation(summary = "Очищение корзины")
    public void clear(@RequestBody Long idCart) {
        service.clear(idCart);
    }
}
