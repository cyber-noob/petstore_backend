package com.netrunner.petstore.entity.pdp.customer;

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
public class ProductEntries {

    @Id
    @Column
    private String idProducts_Animal;

    @Column
    private Integer animal_id;

    @Column
    private String seller_id;

    @Column
    private Integer available_count;

    @Column
    private String description;

    @Column
    private String age;

    @Column
    private String lineage;

    @Column
    private String weight;

    @Column
    private String photos;

    @Column
    private Long price;

    @Column
    private String sex;

    @Column
    private String color;

    @Column
    private String title;
}
