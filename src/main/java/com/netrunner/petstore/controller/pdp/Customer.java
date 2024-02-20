package com.netrunner.petstore.controller.pdp;

import com.netrunner.petstore.entity.pdp.customer.ProductEntries;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pdp")
public class Customer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    private String context = "[PDP]\t";

    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntries> getProductDetails(@RequestParam String product_id) {
        logger.debug(context + "Fetching product details for id - " + product_id);

        ProductEntries results = (ProductEntries) entityManager.createNativeQuery("select * from Products_Animal " +
                "inner join Animals " +
                "on Products_Animal.animal_id = Animals.idAnimals " +
                "where idProducts_Animal = ?", ProductEntries.class)
                .setParameter(1, product_id)
                .getSingleResult();

        return ResponseEntity.status(HttpStatus.OK)
                .body(results);
    }
}
