package com.smartCode.ecommerce.model.dto.product.filterAndSearch;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

public class FilterSearchProduct {
    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Filter {
        Integer startCount;
        Integer endCount;
        Double startPrice;
        Double endPrice;
    }

    @Setter
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Search {
        String text;
    }
}