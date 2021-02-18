package ru.gbf.cartservice.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gbf.cartservice.model.Cart;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query("insert into cart_good values (:idCart, :idGood, :count")
    void saveGood(@Param("idCart") Long idCart, @Param("idGood") Long idGood, @Param("count") int count);

    @Modifying
    @Query("update carts set is_active = false where id = :idCart")
    void disactivateCart(@Param("idCart") Long idCart);

    Optional<Cart> findByIdAndIsActiveIsTrue(Long id);
}
