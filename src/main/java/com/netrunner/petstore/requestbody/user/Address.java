package com.netrunner.petstore.requestbody.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    private String id;

    private String line_one;

    private String line_two;

    private String pincode;

    private String is_default;
}
