package com.casumo.rest.client;

import io.restassured.http.Header;
import io.restassured.response.ResponseOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ResponseParameters {

    private String body;
    private Integer statusCode;
    private Map<String, String> headers;
    private Object entity;

    @SneakyThrows
    public <T> ResponseParameters(ResponseOptions responseOptions, Class<T> clazz) {
        this(responseOptions.getBody().asString(), responseOptions.getStatusCode(), new HashMap<>(), null);
        for (Header header : responseOptions.getHeaders()) {
            headers.put(header.getName(), header.getValue());
        }

        /* TODO:

          if headers contain application/json add deserialization
          this.entity = deserializeJson(getBody(), clazz);
          */

        this.entity = getBody();
    }
}
