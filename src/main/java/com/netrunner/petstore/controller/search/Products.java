package com.netrunner.petstore.controller.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.netrunner.petstore.requestbody.search.smartsearch.KNNSearch;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/search")
public class Products {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    private String context = "[Search]\t";

    @Autowired
    private Environment env;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchForProducts(@RequestParam String query) {
        logger.info("Searching for search query -> " + query);

        RestTemplate restTemplate = new RestTemplate();
        String embedderServer = env.getProperty("embedder.url") + "/embedding?query=" + query;
        JsonNode embeddings = restTemplate.getForObject(embedderServer, JsonNode.class);

        String vespa = env.getProperty("vespa.url") + "/search/";
        KNNSearch knnSearch = new KNNSearch();
        knnSearch.setUserQuery(query);

        knnSearch.setTensor(embeddings.get("embeddings"));

        return ResponseEntity.status(HttpStatus.OK)
                .body(restTemplate.postForEntity(vespa, knnSearch, JsonNode.class)
                        .getBody());
    }
}
