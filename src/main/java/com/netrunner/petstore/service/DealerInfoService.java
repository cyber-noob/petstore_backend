package com.netrunner.petstore.service;

import com.netrunner.petstore.entity.DealerInfo;

import java.util.List;

public interface DealerInfoService {

    //Fetch all dealers info
    List<DealerInfo> getDealers();

    //Add Dealer info table
    DealerInfo addDealer(DealerInfo dealerInfo);

    //Add Dealers info table
    Iterable<DealerInfo> addDealers(List<DealerInfo> dealerInfos);
}
