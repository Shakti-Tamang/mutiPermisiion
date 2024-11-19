package com.nextstep.multiauhtnticate.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private   String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("status code ")
    private int statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(" token")
    private String Token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("refresh token")
    private String refreshToken;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data ")
    private   T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("List ")
    private List<T> list;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("peginateList")
    private Page<T> pageList;




}
