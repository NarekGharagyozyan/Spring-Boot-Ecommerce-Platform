package com.smartCode.ecommerce.util.exceptionHandlerResponse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Setter
public class ApiError {

    private Integer status;
    private String path;
    private Map<String, String> errors;

    public ApiError(Integer status, String path, Map<String, String> errors) {
        super();
        this.status = status;
        this.path = path;
        this.errors = errors;
    }
}
