package com.fis.Automation;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fis.requests.RequestMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
@SuppressWarnings("unused")
public class test_api {
	
	static String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

	@Test
	public void validate_bpi_size() {

        Response response = RequestMethods.getRequest(url);
        response.then().body("bpi.size()", equalTo(3));
        response.then().body("bpi", hasKey("USD"));
        response.then().body("bpi", hasKey("GBP"));
        response.then().body("bpi", hasKey("EUR"));
	}
        
        @Test
    	public void validate_gbp_description() {
        Response response = RequestMethods.getRequest(url);
        String gbp_description = response.jsonPath().getString("bpi.GBP.description");
        System.out.println(gbp_description);
        assertEquals("British Pound Sterling",gbp_description);
      
    }
}
