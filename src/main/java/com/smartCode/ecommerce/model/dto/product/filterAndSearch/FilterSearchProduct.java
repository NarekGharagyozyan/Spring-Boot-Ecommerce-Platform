package com.smartCode.ecommerce.model.dto.product.filterAndSearch;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Search {
        String text;
    }
}