package com.netrunner.petstore.requestbody.search.smartsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ranking {

    @Builder.Default
    private String profile = "bert_title_description_all";

    private boolean listFeatures = true;
}
