package com.netrunner.petstore.requestbody.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cart {

    private String product_id;

    private String customer_id;

    private Integer quantity;
}
