package ru.gbf.cartservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbf.cartservice.dao.CartGoodDao;
import ru.gbf.cartservice.dto.AddGoodsDto;
import ru.gbf.cartservice.dto.CartDTO;
import ru.gbf.cartservice.dto.AddOneGoodDto;
import ru.gbf.cartservice.errors.ResourceNotFoundException;
import ru.gbf.cartservice.model.Cart;
import ru.gbf.cartservice.model.CartGood;
import ru.gbf.cartservice.repository.CartRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final CartGoodDao dao;

    public Cart createCart(Long idUser) {
        return repository.save(new Cart(null,
                        UUID.randomUUID().toString(),
                        idUser,
                        true
                )
        );
    }

    public void addGood(AddOneGoodDto dto) {
         dao.fill(List.of(new CartGood(
                        dto.getIdCart(),
                        dto.getIdCart(),
                        1
                ))
        );
    }

    public void addGoods(AddGoodsDto dto) {
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
        repository.disactivateCart(idCart);
    }
}
