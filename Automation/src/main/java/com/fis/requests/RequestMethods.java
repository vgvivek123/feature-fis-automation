package com.fis.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class RequestMethods {
	
	static Response response;
	public static Response getRequest (String url){
		
		 
        // Send the GET request to the CoinDesk API
        Response response = RestAssured.get(url);
        return response;

      
    }

}
