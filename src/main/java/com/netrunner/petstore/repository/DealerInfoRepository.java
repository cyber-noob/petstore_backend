package com.netrunner.petstore.repository;

import com.netrunner.petstore.entity.DealerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerInfoRepository extends CrudRepository<DealerInfo, Long>{
}
