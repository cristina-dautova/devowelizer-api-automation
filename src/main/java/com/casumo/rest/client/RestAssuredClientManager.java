package com.casumo.rest.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredClientManager extends BaseRestClient {

    private static Logger LOGGER = LogManager.getLogger(RestAssuredClientManager.class);

    private RequestSpecification buildRequestSpecification(String baseUri, Map<String, String> pathParameters,
                                                           Map<String, String> queryParameters) {
        StringBuilder basePath = new StringBuilder();
        for (Map.Entry<String, String> entry : pathParameters.entrySet()) {
            basePath.append("/").append(entry.getValue());
        }
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUri);
        requestSpecBuilder.setBasePath(basePath.toString());
        if (queryParameters != null) {
            requestSpecBuilder.addQueryParams(queryParameters);
        }
        requestSpecBuilder.log(LogDetail.URI);
        return requestSpecBuilder.build();
    }

    @Override
    protected <T> Object buildRequest(HttpVerbs method, String url, Map<String, String> pathParameters,
                                      Map<String, String> queryParameters, Map<String, String> headers, T value) {
        RequestSpecification requestSpecification = buildRequestSpecification(url, pathParameters, queryParameters);
        RequestSpecification req = given(requestSpecification);
        if (headers != null) {
            for (Map.Entry entry : headers.entrySet()) {
                req = req.header((String) entry.getKey(), entry.getValue());
            }
        }
        if (value != null) {
            req = req.body(value);
        }
        return new RequestParametersRestAssured(method, req);
    }

    @Override
    protected <T> ResponseParameters executeRequest(Object responseObj, Class<T> clazz) {
        ResponseOptions responseOptions;

        HttpVerbs method = ((RequestParametersRestAssured) responseObj).getHttpVerb();
        RequestSpecification req = ((RequestParametersRestAssured) responseObj).getRequestSpecification();

        switch (method) {
            case GET -> responseOptions = req.get();
            case DELETE -> responseOptions = req.delete();
            case POST -> responseOptions = req.post();
            case PUT -> responseOptions = req.put();
            default -> throw new IllegalStateException("Unexpected value: " + method);
        }
        LOGGER.info("info");
        return new ResponseParameters(responseOptions, clazz);
    }
}
