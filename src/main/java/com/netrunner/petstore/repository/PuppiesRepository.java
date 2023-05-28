package com.netrunner.petstore.repository;

import com.netrunner.petstore.entity.Puppies;
import org.springframework.data.repository.CrudRepository;

public interface PuppiesRepository extends CrudRepository<Puppies, Long> {
}
