package com.spotify.oauth2.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {

	private InnerError error;

}