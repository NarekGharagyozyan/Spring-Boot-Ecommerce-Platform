package com.smartCode.ecommerce.util.page;

import com.smartCode.ecommerce.exceptions.PageRequestValidationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.util.Objects.isNull;

public class CustomPageRequest extends PageRequest {

    private static final int PAGE_MIN_SIZE = 1;
    private static final int PAGE_MAX_SIZE = 100;
    private static final int PAGE_DEFAULT_VALUE = 1;
    private static final int SIZE_DEFAULT_VALUE = 20;

    protected CustomPageRequest(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    public static PageRequest from(Integer page, Integer size, Sort sort) {
        if (isNull(page)) {
            page = PAGE_DEFAULT_VALUE;
        }
        if (isNull(size)) {
            size = SIZE_DEFAULT_VALUE;
        }

        if (page < PAGE_MIN_SIZE) {
            throw new PageRequestValidationException("Page can't be less than " + PAGE_MIN_SIZE);
        }
        if (size < PAGE_MIN_SIZE || size > PAGE_MAX_SIZE) {
            throw new PageRequestValidationException(String.format("Size must be between '%s' and '%s'", PAGE_MIN_SIZE, PAGE_MAX_SIZE));
        }
        return new CustomPageRequest(page - 1, size, sort);
    }
}
