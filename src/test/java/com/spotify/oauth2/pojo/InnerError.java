package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;


	@JsonInclude(JsonInclude.Include.NON_NULL)

	public class InnerError {


		private Integer status;

		private String message;


		public InnerError() {
		}


		public InnerError(Integer status, String message) {
			super();
			this.status = status;
			this.message = message;
		}


		public Integer getStatus() {
			return status;
		}


		public void setStatus(Integer status) {
			this.status = status;
		}


		public String getMessage() {
			return message;
		}


		public void setMessage(String message) {
			this.message = message;
		}
	}

