package com.netrunner.petstore.service;

import com.netrunner.petstore.entity.DealerInfo;
import com.netrunner.petstore.entity.Puppies;

import java.util.List;

public interface PuppiesService {

    //Fetch all dealers info
    List<Puppies> getPuppies();

    //Add Dealer info table
    Puppies addPuppy(Puppies puppies);

    //Add Dealers info table
    Iterable<Puppies> addPuppies(List<Puppies> puppies);
}
