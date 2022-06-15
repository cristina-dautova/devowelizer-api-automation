package com.casumo.rest.client;

import io.restassured.specification.RequestSpecification;
import lombok.Data;

@Data
public class RequestParametersRestAssured {

    private HttpVerbs httpVerb;
    private RequestSpecification requestSpecification;

    public RequestParametersRestAssured(HttpVerbs httpVerb, RequestSpecification requestSpecification) {
        this.httpVerb = httpVerb;
        this.requestSpecification = requestSpecification;
    }
}
