package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

	public static String generateName() {
		Faker faker= new Faker();
		//faker.funnyName();
		return "Playlist " + faker.regexify("[A-Za-z0-9 ,_-]{10}");
	}
	public static String generateDesciption() {
		Faker faker= new Faker();
		//faker.funnyName();
		return "Description " + faker.regexify("[A-Za-z0-9 ,_-]{20}");
	}
	
}
