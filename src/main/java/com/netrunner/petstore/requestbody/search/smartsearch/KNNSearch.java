package com.netrunner.petstore.requestbody.search.smartsearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class KNNSearch {

    @Builder.Default
    private String yql = "select * from sources * where ({targetHits: 1000, label: \"nns\"}nearestNeighbor(embeddings_bert, tensor_bert))";

    private String userQuery;

    @Builder.Default
    private Ranking ranking = new Ranking();

    @JsonProperty("input.query(tensor_bert)")
    private Object tensor;
}
