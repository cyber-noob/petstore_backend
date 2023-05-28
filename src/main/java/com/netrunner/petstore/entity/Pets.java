package com.netrunner.petstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table
public class Pets {
    @Column(name = "area")
    private String area;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "idDealer")
    private int idDealer;

    @Id
    @Column(name = "breedId")
    private int breedId;

    @Column(name = "MultiMediaId")
    private int MultiMediaId;

    @Column(name = "isKCI")
    private int isKCI;

    @Column(name = "breedName")
    private String breedName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "price")
    private int price;

    @Column(name = "dealerName")
    private String dealerName;

    @Column(name = "dealerState")
    private String dealerState;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "credibilityPoints")
    private int credibilityPoints;

    @Column(name = "isPremium")
    private int isPremium;

    @Column(name = "address")
    private String address;

    @Column(name = "url")
    private String url;

    @Column(name = "mediaType")
    private String mediaType;

}
