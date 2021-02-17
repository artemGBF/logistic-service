package ru.gbf.cartservice.mapper;

import org.springframework.stereotype.Component;
import ru.gbf.cartservice.dto.CartDTO;
import ru.gbf.cartservice.model.Cart;
import ru.gbf.cartservice.model.CartGood;

import java.util.List;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart, List<CartGood> goods) {
        return new CartDTO(cart, goods);
    }

   /* public Cart toEntity(CartDTO dto) {
        return new Cart(dto.getId(), dto.getUuid(), dto.getIdUser());
    }*/
}
