package com.netrunner.petstore.controller;

import com.netrunner.petstore.entity.Puppies;
import com.netrunner.petstore.service.PuppiesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class PuppiesController {

    @Autowired
    private PuppiesService puppiesService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    @RequestMapping(value = "/puppies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Puppies> getAllPuppies() {
        logger.info("Fetching all puppies details");
        return puppiesService.getPuppies();
    }

    @RequestMapping(value = "/puppies", method = RequestMethod.POST)
    public Iterable<Puppies> addPuppies(@Valid @RequestBody List<Puppies> puppies){
        logger.info("Adding list of puppies to db - " + puppies);
        return puppiesService.addPuppies(puppies);
    }
}
