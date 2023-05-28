package com.netrunner.petstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DealerInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealerInfo {

    @Id
    @Column(name = "idDealerInfo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDealerInfo;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "credibilityPoints")
    private Integer credibilityPoints;

    @Column(name = "isPremium", nullable = false, columnDefinition = "TINYINT")
    private boolean isPremium;

    @Column(name = "address")
    private String address;
}
