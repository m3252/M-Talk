package com.msc.mtalk.zuul;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoutingTest {

    private static final String ROUTE_URL = "http://localhost:8080/api-member/members/test";

    @Test
    public void whenSendRequestToFooResource_thenOK() {
        final Response response = RestAssured.get(ROUTE_URL);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void whenSendRequest_thenHeaderAdded() {
        Response response = RestAssured.get(ROUTE_URL);

        assertEquals(200, response.getStatusCode());
        assertEquals("TestSample", response.getHeader("Test"));
    }



}
