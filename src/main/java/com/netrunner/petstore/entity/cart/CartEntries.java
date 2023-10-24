package com.netrunner.petstore.entity.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table
public class CartEntries {

    @Id
    @Column
    private String idCart;

    @Column
    private String product_id;

    @Column
    private String customer_id;

    @Column
    private Integer quantity;
}
