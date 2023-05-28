package com.netrunner.petstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Puppies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Puppies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPuppies;

    @Column(name = "breedId")
    private Integer breedId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "price")
    private Integer price;
}
