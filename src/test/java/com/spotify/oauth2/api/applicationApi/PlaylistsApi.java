package com.spotify.oauth2.api.applicationApi;
import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlists;
import com.spotify.oauth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlaylistsApi {
//	static String access_token = "BQDDSKVi8na0nyOhu8f1HcC4BO8tFnLSiF6JuP17QLe-0i7Ra_jF1HBWCcaZsWfC9DsT9M7S5tWDmXNHTGI3zD0UE3_NtRR2FeFYPAur-63DEoWHKf3bHbpyirsL7yfZMD8RBEucE8RzAjtKniOdwpjH6stC64oKIRotJwNszwzDwMu3D5LjeMXC6ZwdU6AHGhxLvbn5MdwGFgSlq6_hKMCpZbZnS_32lwi1_kjQ5BMg7JxmsvPM-SwU4YGdli2TAM9ei2gIP6Knl0yW";


	@Step
	public static Response post(Playlists requestPlaylist) {

		return RestResource.post(USERS+"/"+ConfigLoader.getInstance().getUser_id()
				+PLAYLISTS, TokenManager.getToken(), requestPlaylist);

	}

	public static Response post(String token, Playlists requestPlaylist) {

		return RestResource.post(USERS+"/"+ConfigLoader.getInstance().getUser_id()+PLAYLISTS, token, requestPlaylist);

	}

	public static Response get(String playListId) {
		return RestResource.get(PLAYLISTS+"/" + playListId, TokenManager.getToken());

	}

	public static Response update(Playlists requestPlaylist, String playlistId) {

		return RestResource.update(PLAYLISTS+"/" + playlistId, TokenManager.getToken(), requestPlaylist);

	}

}
