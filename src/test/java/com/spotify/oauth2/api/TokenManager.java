package com.spotify.oauth2.api;
import java.time.Instant;
import java.util.HashMap;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class TokenManager {

	private static String access_token;
	private static Instant expiry_time;

	public synchronized static String getToken() {

		try {

			if(access_token== null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing Token...");
				Response response= renewToken();
				access_token= response.path("access_token");
				int expiryTimeInSeconds= response.path("expires_in");
				expiry_time=Instant.now().plusSeconds(expiryTimeInSeconds - 300);

			}else {
				System.out.println("Token is good to use.");
			}
		}
		catch(Exception e){
			throw new RuntimeException("Abort!!.. token is expired..");

		}
		return access_token;
	}



	public static Response renewToken() {
		HashMap<String, String> formParams = new HashMap<>();

		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret", ConfigLoader.getInstance().getClient_secret());
		formParams.put("grant_type", ConfigLoader.getInstance().getGrant_type());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefresh_token());


		Response response=RestResource.postAccount(formParams);



		System.out.println(response.asString());

		if(response.statusCode()!=200) {
			throw new RuntimeException("Abort!! Renew token failed");
		}
		//return response.path("access_token");
		return response;

	}


}
