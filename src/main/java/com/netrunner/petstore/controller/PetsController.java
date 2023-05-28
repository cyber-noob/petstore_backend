package com.netrunner.petstore.controller;

import com.netrunner.petstore.entity.Pets;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class PetsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    @RequestMapping(value = "/pets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection getPets(@RequestParam String breed, @RequestParam String area, @RequestParam String city, @RequestParam String state){
        logger.info("Fetching all pets with following params:\n" + breed + "|\t" + area + "|\t" + city + "|\t" + state);

        Query query = entityManager.createNativeQuery("select loc.area, loc.city, loc.state, loc.country,\n" +
                "d.idDealer, d.breedId, d.MultiMediaId, d.isKCI,\n" +
                "b.name as breedName,\n" +
                "pup.quantity, pup.age, pup.sex, pup.price,\n" +
                "di.name as dealerName, di.state as dealerState, di.contactNumber, di.credibilityPoints, di.isPremium, di.address,\n" +
                "mm.url, mm.mediaType\n" +
                "from (((((Location as loc\n" +
                "inner join Dealer as d on d.locationId = loc.idLocation)\n" +
                "inner join Breeds as b on b.idBreeds = d.breedId)\n" +
                "inner join Puppies as pup on pup.dealerId = d.idDealer)\n" +
                "inner join DealerInfo as di on di.idDealerInfo = d.idDealer)\n" +
                "inner join MultiMedia as mm on mm.idImages = d.multiMediaId)\n" +
                "where loc.area = \"%s\" and loc.city = \"%s\" and loc.state = \"%s\" and b.name = \"%s\";".formatted(area, city, state, breed)
        , Pets.class);

        return query.getResultList();
    }
}
