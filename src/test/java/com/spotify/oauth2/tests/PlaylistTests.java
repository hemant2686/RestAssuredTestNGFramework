package com.spotify.oauth2.tests;

import static com.spotify.oauth2.utils.FakerUtils.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistsApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlists;
import com.spotify.oauth2.utils.DataLoader;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;

//mvn test -DBASE_URI="https://api.spotify.com" -DACCOUNT_BASE_URI="https://accounts.spotify.com"
//Run above query in cmd prompt


@Epic("Spotify oauth2.0")
@Feature("To test playlist test cases")
public class PlaylistTests extends BaseTest{

	@Step
	public Playlists playlistBuilder(String name, String description, boolean _public) {
		Playlists playlist = new Playlists();
		playlist.setName(name);
		playlist.setDescription(description);
		playlist.set_public(_public);
		return playlist;

		// return new
		// Playlists().setName(name).setDescription(description).setPublic(_public);
	}

	public void assertPlaylistEqual(Playlists responsePlaylist, Playlists requestPlaylist) {
		assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
	}

	@Step
	public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
		assertThat(actualStatusCode, equalTo(statusCode.code));
	}

	@Step
	public void assertErrorCode(Error responseError, StatusCode statusCode) {
		assertThat(responseError.getError().getStatus(), equalTo(statusCode.code));
		assertThat(responseError.getError().getMessage(), equalTo(statusCode.msg));

	}

	@Story("Create a playlist story")
	@Link("http://www.testng.com")
	@Issue("Issue-1")
	@TmsLink("http://test.com")
	@Description("This is the description")
	@Test(description = "Should be able to create a playlist")
	public void ShouldBeAbleToCreateAPlaylist() {

		Playlists requestPlaylist = playlistBuilder(generateName(), generateDesciption(), false);

		Response response = PlaylistsApi.post(requestPlaylist);

		assertStatusCode(response.statusCode(), StatusCode.CODE_201);

		assertPlaylistEqual(response.as(Playlists.class), requestPlaylist);

	}

	@Story("Get a playlist story")
	@Description("This is the description")
	@Test(description = "Should be able to get a playlist")
	public void ShouldBeAbleToGetAPlaylist() {

		Playlists requestPlaylist = playlistBuilder("Updated Playlist Name", "Updated playlist description", false);

		Response response = PlaylistsApi.get(DataLoader.getInstance().getPlaylistId());

		assertStatusCode(response.statusCode(), StatusCode.CODE_200);

		assertPlaylistEqual(response.as(Playlists.class), requestPlaylist);

	}

	@Story("Update a playlist story")
	@Description("This is the description")
	@Test(description = "Should be able to update a playlist")
	public void ShouldBeAbleToUpdateAPlaylist() {

		Playlists requestPlaylist = playlistBuilder(generateName(), generateDesciption(), false);

		Response response = PlaylistsApi.update(requestPlaylist, DataLoader.getInstance().get_update_playlist_id());
		assertStatusCode(response.statusCode(), StatusCode.CODE_200);

	}

	@Story("Negative test cases for Create a playlist story")
	@Description("This is the description")
	@Test(description = "Should not be able to create a playlist without name")
	public void ShouldNotBeAbleToCreateAPlaylistWithoutName() {

		Playlists requestPlaylist = playlistBuilder("", "Updated playlist description", false);

		Response response = PlaylistsApi.post(requestPlaylist);
		assertStatusCode(response.statusCode(), StatusCode.CODE_400);
		assertErrorCode(response.as(Error.class), StatusCode.CODE_400);

	}

	@Story("Negative test cases for expiry token")
	@Description("This is the description")
	@Test(description = "Should not be able to create a playlist with expired token")
	public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {

		Playlists requestPlaylist = playlistBuilder("", "Updated playlist description", false);
		/*
		 * Playlists requestPlaylist = new
		 * Playlists().setName("NewPlaylist").setDescription("New playlist description")
		 * .setPublic(false);
		 */

		Response response = PlaylistsApi.post("123456", requestPlaylist);

		assertStatusCode(response.statusCode(), StatusCode.CODE_401);
		assertErrorCode(response.as(Error.class), StatusCode.CODE_401);

	}

}
