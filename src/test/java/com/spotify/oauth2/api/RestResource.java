package com.spotify.oauth2.api;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.response.Response;

public class RestResource {
	//static String access_token = "BQDDSKVi8na0nyOhu8f1HcC4BO8tFnLSiF6JuP17QLe-0i7Ra_jF1HBWCcaZsWfC9DsT9M7S5tWDmXNHTGI3zD0UE3_NtRR2FeFYPAur-63DEoWHKf3bHbpyirsL7yfZMD8RBEucE8RzAjtKniOdwpjH6stC64oKIRotJwNszwzDwMu3D5LjeMXC6ZwdU6AHGhxLvbn5MdwGFgSlq6_hKMCpZbZnS_32lwi1_kjQ5BMg7JxmsvPM-SwU4YGdli2TAM9ei2gIP6Knl0yW";

	public static Response post(String path, String token, Object requestPlaylist) {
		return given().spec(getRequestSpec()).
				body(requestPlaylist).
				auth().oauth2(token).
				//header("Authorization", "Bearer " + token).
				when().post(path).
				then().spec(getResponseSpec()).
				extract().
				response();
			}


	public static  Response get(String path, String token) {
		return given(getRequestSpec()).
				auth().oauth2(token).
				//header("Authorization", "Bearer " + token).
				when().get(path).
				then().spec(getResponseSpec()).
				extract().
				response();
	}

	public static  Response update(String path, String token, Object requestPlaylist) {
		return given().
		spec(getRequestSpec()).
		body(requestPlaylist).
		auth().oauth2(token).
		//header("Authorization", "Bearer " + token).
		when().put(path).
		then().spec(getResponseSpec()).
		extract().response();
	}


		public static Response postAccount(HashMap<String, String> formParams) {
			return given(SpecBuilder.getAccountRequestSpec()).
					formParams(formParams).
				when().post(API+TOKEN).
				then().spec(SpecBuilder.getResponseSpec()).
					extract().
					response();

	}

}
