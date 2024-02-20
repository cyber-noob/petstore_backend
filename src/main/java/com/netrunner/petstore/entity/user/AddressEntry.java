package com.netrunner.petstore.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table
public class AddressEntry {

    @Id
    @Column
    private Integer idAddress;

    @Column
    private String line_one;

    @Column
    private String line_two;

    @Column
    private String pincode;

    @Column
    private String is_default;

    @Column
    private String customer_id;
}
