package com.netrunner.petstore.controller;

import com.netrunner.petstore.entity.DealerInfo;
import com.netrunner.petstore.service.DealerInfoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealerInfoController {

    @Autowired
    private DealerInfoService dealerInfoService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @RequestMapping(value = "/dealers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DealerInfo> getAllDealers(){
        logger.info("Fetching all values from DealerInfo Table");
        List<DealerInfo> dealerInfos = dealerInfoService.getDealers();
        logger.debug("obj -> " + dealerInfos.toString());
        dealerInfos.forEach(dealerInfo -> System.out.println("[DEBUG]\t " + dealerInfo.getName()));
        return dealerInfos;
    }

    @RequestMapping(value = "/dealer", method = RequestMethod.POST)
    public DealerInfo postDealer(@Valid @RequestBody DealerInfo dealerInfo){
        logger.info("Posting dealer info into db: " + dealerInfo);
        return dealerInfoService.addDealer(dealerInfo);
    }

    @RequestMapping(value = "/dealers", method = RequestMethod.POST)
    public Iterable<DealerInfo> postDealers(@Valid @RequestBody List<DealerInfo> dealerInfos){
        logger.info("Posting list of dealers into db: " + dealerInfos);
        return dealerInfoService.addDealers(dealerInfos);
    }
}
