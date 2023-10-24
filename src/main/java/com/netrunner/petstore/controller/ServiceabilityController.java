package com.netrunner.petstore.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("http://localhost:9000")
public class ServiceabilityController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    @RequestMapping(value = "/serviceability", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection getServiceabilityStates(@RequestParam String type) throws Exception {
        logger.info("Fetching all serviceability areas with params: type = " + type);

        Query query = switch (type) {
            case "state" -> entityManager.createNativeQuery("select distinct state from Location;");
            case "city" -> entityManager.createNativeQuery("select distinct city from Location;");
            case "area" -> entityManager.createNativeQuery("select distinct area from Location;");
            default -> throw new Exception("Unknown serviceability type");
        };

        if(query != null)
            return query.getResultList();

        throw new Exception("Unknown serviceability type");
    }
}
