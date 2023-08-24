package com.smartCode.ecommerce.model.dto.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonSerialize
public class ActionRequestDto implements Serializable {

    @JsonProperty
    private String actionType;

    @JsonProperty
    private String entityType;

    @JsonProperty
    private LocalDateTime actionDate;

    @JsonProperty
    private Integer userId;

}
