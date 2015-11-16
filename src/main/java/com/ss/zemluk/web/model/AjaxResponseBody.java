package com.ss.zemluk.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.ss.zemluk.web.jsonview.Views;

/**
 * Class for representing values in JSON
 */
public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	List<Data> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Data> getResult() {
		return result;
	}

	public void setResult(List<Data> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AjaxResponseResult [msg=" + msg + ", code=" + code + ", result=" + result + "]";
	}

}
