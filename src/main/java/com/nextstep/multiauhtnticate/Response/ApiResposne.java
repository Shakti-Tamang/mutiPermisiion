package com.nextstep.multiauhtnticate.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ApiResposne<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private   String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("status code ")
    private String statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data ")
    private   T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("List ")
    private List<T> list;


}
