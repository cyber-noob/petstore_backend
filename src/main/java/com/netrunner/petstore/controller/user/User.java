package com.netrunner.petstore.controller.user;

import com.netrunner.petstore.entity.user.AddressEntry;
import com.netrunner.petstore.requestbody.user.Address;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user/address")
public class User {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    private String context = User.class.getSimpleName() + "\t";

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HashMap<String, Object>> getAddresslist(@RequestParam String customer_id) {
        logger.info(context + "Fetching address list");

        List<AddressEntry> addressEntries = entityManager.createNativeQuery("select * from Address where customer_id = ?;")
                .setParameter(1, customer_id)
                .getResultList();

        List<HashMap<String, Object>> result = new ArrayList<>();
        addressEntries.parallelStream()
                .forEach(entry -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("line_one", entry.getLine_one());
                    map.put("line_two", entry.getLine_two());
                    map.put("pincode", entry.getPincode());
                    map.put("is_default", entry.getIs_default());

                    result.add(map);
                });

        return result;
    }

    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> putAddress(@RequestParam String customer_id, @RequestBody Address address) {
        if (entityManager.createNativeQuery("select * from Address " +
                        "where customer_id = ?;")
                .setParameter(1, customer_id)
                .getResultList()
                .size() < 5) {

            entityManager.joinTransaction();

            resetPreExistingDefaultAddresses(customer_id, address);
            entityManager.createNativeQuery("insert into Address (line_one, line_two, pincode, is_default)" +
                            "values " +
                            "(?, ?, ?, ?)")
                    .setParameter(1, address.getLine_one())
                    .setParameter(2, address.getLine_two())
                    .setParameter(3, address.getPincode())
                    .setParameter(4, address.getIs_default())
                    .executeUpdate();

            return Map.of("code", "AD000",
                    "message", "Address added successfully");
        } else
            return Map.of("code", "AD001",
                    "message", "Max limit for address book exceeded. Please remove existing entries to add a new one");
    }

    private void resetPreExistingDefaultAddresses(String customer_id, Address address) {
        if (!address.getIs_default().isBlank() &&
                address.getIs_default().equalsIgnoreCase("true") &&
                !entityManager.createNativeQuery("select * from Address " +
                                "where customer_id = ? and is_default = true;")
                        .setParameter(1, customer_id)
                        .getResultList().isEmpty()) {
            entityManager.createNativeQuery("update Address " +
                            "set is_default = false " +
                            "where customer_id = ? and is_default = true;")
                    .setParameter(1, customer_id)
                    .executeUpdate();
        }
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateAddress(@RequestParam String customer_id, @RequestBody Address address) {
        resetPreExistingDefaultAddresses(customer_id, address);
        entityManager.joinTransaction();
        entityManager.createNativeQuery("update Address " +
                "set line_one = ?, line_two = ?, pincode = ?, is_default = ? " +
                "where customer_id = ? and idAddress = ?;")
                .setParameter(1, address.getLine_one())
                .setParameter(2, address.getLine_two())
                .setParameter(3, address.getPincode())
                .setParameter(4, address.getIs_default())
                .setParameter(5, customer_id)
                .setParameter(6, address.getId())
                .executeUpdate();

        return Map.of("code", "AD010",
                "message", "Address updated successfully");
    }

    @Transactional
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> removeAddress(@RequestParam String customer_id, @RequestParam String address_id) {
        entityManager.createNativeQuery("delete from Address " +
                "where customer_id = ? and idAddress = ?;")
                .setParameter(1, customer_id)
                .setParameter(2, address_id)
                .executeUpdate();

        return Map.of("code", "AD100",
                "message", "Address removed successfully");
    }
}
