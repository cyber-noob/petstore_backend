package com.netrunner.petstore.entity.wishlist;

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
public class WishlistEntries {

    @Id
    @Column
    private String idWishlist;

    @Column
    private String product_id;

    @Column
    private String customer_id;
}
