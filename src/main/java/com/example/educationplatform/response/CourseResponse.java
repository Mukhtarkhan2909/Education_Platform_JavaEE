package com.example.educationplatform.response;

import com.example.educationplatform.module.Courses;
import java.util.List;

public class CourseResponse {
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<Courses> oblist;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAUTH_TOKEN() {
		return AUTH_TOKEN;
	}

	public void setAUTH_TOKEN(String aUTH_TOKEN) {
		AUTH_TOKEN = aUTH_TOKEN;
	}

	public List<Courses> getOblist() {
		return oblist;
	}

	public void setOblist(List<Courses> oblist) {
		this.oblist = oblist;
	}

}
