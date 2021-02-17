package ru.gbf.cartservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Good {
    @Id
    private Long id;
    private String name;
    private Integer price;
    @Column("category_id")
    private Long categoryID;
}
