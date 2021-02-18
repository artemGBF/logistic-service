package ru.gbf.cartservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gbf.cartservice.dao.CartGoodDao;
import ru.gbf.cartservice.dto.AddGoodsDto;
import ru.gbf.cartservice.dto.CartDTO;
import ru.gbf.cartservice.dto.AddOneGoodDto;
import ru.gbf.cartservice.errors.ResourceLackException;
import ru.gbf.cartservice.errors.ResourceNotFoundException;
import ru.gbf.cartservice.model.Cart;
import ru.gbf.cartservice.model.CartGood;
import ru.gbf.cartservice.model.GoodStock;
import ru.gbf.cartservice.repository.CartRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final CartGoodDao dao;
    private final RestTemplate restTemplate = new RestTemplate();

    public Cart createCart(Long idUser) {
        return repository.save(new Cart(null,
                        UUID.randomUUID().toString(),
                        idUser,
                        true
                )
        );
    }

    public void addGood(AddOneGoodDto dto) {
        HttpEntity<GoodStock> httpEntity = new HttpEntity<>(
                new GoodStock(
                        dto.getIdGood(),
                        1L,
                        1
                )
        );
        Long aLong = restTemplate.postForObject(
                "http://localhost:8080/api/stockpile/checkCount",
                httpEntity,
                Long.class,
                (Object) null);
        if (aLong == 0) {
            throw new ResourceLackException();
        }
        dao.fill(List.of(new CartGood(
                        dto.getIdCart(),
                        dto.getIdGood(),
                        1
                ))
        );
    }

    public void addGoods(AddGoodsDto dto) {
        HttpEntity<GoodStock> httpEntity = new HttpEntity<>(
                new GoodStock(
                        dto.getIdGood(),
                        1L,
                        dto.getCount()
                )
        );
        Long aLong = restTemplate.postForObject(
                "http://localhost:8080/api/stockpile/check",
                httpEntity,
                Long.class,
                (Object) null);
        if (aLong - dto.getCount() <= 0) {
            throw new ResourceLackException(dto.getCount());
        }
        dao.fill(List.of(new CartGood(
                        dto.getIdCart(),
                        dto.getIdCart(),
                        dto.getCount()
                ))
        );
    }

    public CartDTO getAllFromCart(Long idCart) {
        Cart cart = repository.findByIdAndIsActiveIsTrue(idCart).orElseThrow(
                () -> new ResourceNotFoundException("корзина", idCart)
        );
        List<CartGood> byId = dao.getAllByIdCartEquals(idCart);
        return new CartDTO(cart, byId);
    }

    public void clear(Long idCart) {
        dao.clear(idCart);
    }
}
