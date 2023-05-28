package com.netrunner.petstore.service;

import com.netrunner.petstore.entity.DealerInfo;
import com.netrunner.petstore.repository.DealerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerInfoImpl implements DealerInfoService{

    @Autowired
    DealerInfoRepository dealerInfoRepository;

    @Override
    public List<DealerInfo> getDealers() {
        return (List<DealerInfo>) dealerInfoRepository.findAll();
    }

    @Override
    public DealerInfo addDealer(DealerInfo dealerInfo) {
        return dealerInfoRepository.save(dealerInfo);
    }

    @Override
    public Iterable<DealerInfo> addDealers(List<DealerInfo> dealerInfos) {
        return dealerInfoRepository.saveAll(dealerInfos);
    }
}
