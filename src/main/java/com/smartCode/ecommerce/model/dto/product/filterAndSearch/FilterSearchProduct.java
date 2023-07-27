package com.smartCode.ecommerce.model.dto.product.filterAndSearch;

import lombok.Getter;
import lombok.Setter;

public class FilterSearchProduct {
    @Getter
    @Setter
    public static class Filter {
        private Integer startCount;
        private Integer endCount;
        private Double startPrice;
        private Double endPrice;
    }

    @Setter
    @Getter
    public static class Search {
        private String text;
    }
}