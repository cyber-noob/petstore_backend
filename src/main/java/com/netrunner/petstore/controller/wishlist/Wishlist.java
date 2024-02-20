package com.netrunner.petstore.controller.wishlist;

import com.netrunner.petstore.entity.cart.CartEntries;
import com.netrunner.petstore.entity.pdp.customer.ProductEntries;
import com.netrunner.petstore.entity.wishlist.WishlistEntries;
import com.netrunner.petstore.requestbody.cart.Cart;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/wishlist")
public class Wishlist {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    private String context = "[Wishlist]\t";

    @Transactional
    @CrossOrigin(origins = "http://localhost:4321")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addToWishlist(@RequestBody Cart body) {
        logger.info(context + "Adding item to wishlist");

        if (entityManager.createNativeQuery("select * from Wishlist " +
                        "where customer_id = ? and product_id = ?;")
                .setParameter(1, body.getCustomer_id())
                .setParameter(2, body.getProduct_id())
                .getResultList().isEmpty()) {
            logger.info(context + "uuid - " + UUID.randomUUID().toString());
            entityManager.joinTransaction();
            entityManager.createNativeQuery("insert into Wishlist " +
                            "(idWishlist, product_id, customer_id) " +
                            "values (?, ?, ?);")
                    .setParameter(1, UUID.randomUUID().toString())
                    .setParameter(2, body.getProduct_id())
                    .setParameter(3, body.getCustomer_id())
                    .executeUpdate();

            logger.info("Successfully added items to cart");
            return Map.of("code", "WL008",
                    "message", "Successfully added items to cart");
        } else {
            return Map.of("code", "WL000",
                    "message", "Item already present in wishlist");
        }
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HashMap<String, Object>> getWishlist(@RequestParam String customer_id) {
        logger.info(context + "Fetching cart info for customer -> " + customer_id);


        List<WishlistEntries> results = entityManager.createNativeQuery("select * " +
                        "from Wishlist " +
                        "where customer_id = ?", WishlistEntries.class)
                .setParameter(1, customer_id)
                .getResultList();

        List<HashMap<String, Object>> resultant = new ArrayList<>();
        results.parallelStream()
                .forEach(wishlistEntry -> {
                    String productId = wishlistEntry.getProduct_id();
                    List<ProductEntries> productEntries = entityManager.createNativeQuery("select * from Products_Animal " +
                                    "where idProducts_Animal = ?", ProductEntries.class)
                            .setParameter(1, productId)
                            .getResultList();

                    ProductEntries entry = productEntries.get(0);
                    HashMap<String, Object> item = new HashMap<>();
                    item.put("id", entry.getIdProducts_Animal());
                    item.put("description", entry.getDescription());
                    item.put("age", entry.getAge());
                    item.put("lineage", entry.getLineage());
                    item.put("weight", entry.getWeight());
                    item.put("photos", entry.getPhotos());
                    item.put("price", entry.getPrice());
                    item.put("sex", entry.getSex());
                    item.put("color", entry.getColor());
                    item.put("title", entry.getTitle());

                    resultant.add(item);
                });

        return resultant;
    }

    @Transactional
    @RequestMapping(value = "/remove/item", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> deleteItem(@RequestParam String customer_id, @RequestParam String product_id) {
        logger.info(context + "Deleting product - " + product_id + " from customer - " + customer_id);

        entityManager.joinTransaction();
        int rowsAffected = entityManager.createNativeQuery("delete from Wishlist " +
                        "where customer_id = ? " +
                        "and product_id = ?;")
                .setParameter(1, customer_id)
                .setParameter(2, product_id)
                .executeUpdate();

        logger.info("No. of rows deleted -> " + rowsAffected);
        return Map.of(
                "message", context + "Successfully deleted the requested products"
        );
    }
}
