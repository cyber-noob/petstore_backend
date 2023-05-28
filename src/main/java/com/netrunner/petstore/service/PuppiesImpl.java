package com.netrunner.petstore.service;

import com.netrunner.petstore.entity.DealerInfo;
import com.netrunner.petstore.entity.Puppies;
import com.netrunner.petstore.repository.PuppiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuppiesImpl implements PuppiesService{

    @Autowired
    PuppiesRepository puppiesRepository;

    @Override
    public List<Puppies> getPuppies() {
        return (List<Puppies>) puppiesRepository.findAll();
    }

    @Override
    public Puppies addPuppy(Puppies puppies) {
        return puppiesRepository.save(puppies);
    }

    @Override
    public Iterable<Puppies> addPuppies(List<Puppies> puppies) {
        return puppiesRepository.saveAll(puppies);
    }
}
