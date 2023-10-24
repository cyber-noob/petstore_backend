package com.netrunner.petstore.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netrunner.petstore.entity.cart.CartEntries;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cart")
public class Cart {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addToCart(@RequestBody com.netrunner.petstore.requestbody.Cart body) {
        logger.info("Adding item to cart");

        entityManager.joinTransaction();
        Arrays.stream(body.getProduct_id())
                        .forEach(product -> entityManager.createNativeQuery("insert into Cart " +
                                        "(product_id, quantity, customer_id) " +
                                        "values (?, ?, ?);")
                                .setParameter(1, product)
                                .setParameter(2, body.getQuantity())
                                .setParameter(3, body.getCustomer_id())
                                .executeUpdate());

        logger.info("Successfully added items to cart");
        return Map.of(
            "message", "Successfully added items to cart"
        );
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getCart(@RequestParam String customer_id) {
        logger.info("Fetching cart info for customer -> " + customer_id);

        List<CartEntries> results = entityManager.createNativeQuery("select * " +
                        "from Cart " +
                        "where customer_id = ?", CartEntries.class)
                .setParameter(1, customer_id)
                .getResultList();

        Map<String, Object> resultant = new HashMap<>();
        resultant.put("customer_id", customer_id);
        List<Map<String, Object>> listOfProducts = new ArrayList<>();
        for (CartEntries result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", result.getProduct_id());
            map.put("count", result.getQuantity());
            listOfProducts.add(map);
        }
        resultant.put("products", listOfProducts);

        return resultant;
    }

    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> deleteItem(@RequestParam String customer_id, @RequestParam String product_id) {
        logger.info("Deleting product - " + product_id + " from customer - " + customer_id);

        entityManager.joinTransaction();
        int rowsAffected = entityManager.createNativeQuery("delete from Cart " +
                "where customer_id = ? " +
                "and product_id = ?;")
                .setParameter(1, customer_id)
                .setParameter(2, product_id)
                .executeUpdate();

        logger.info("No. of rows deleted -> " + rowsAffected);
        return Map.of(
                "message", "Successfully deleted the requested products"
        );
    }
}
