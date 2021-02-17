package ru.gbf.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("carts")
public class Cart {
    @Id
    private Long id;
    private String uuid;
    @Column("id_user")
    private Long idUser;
    @Column("is_active")
    private boolean isActive;
}
