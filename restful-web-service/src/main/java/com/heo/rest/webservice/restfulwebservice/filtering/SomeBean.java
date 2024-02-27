package com.heo.rest.webservice.restfulwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

// @JsonIgnoreProperties("field1") //정적 필터링,  = 멤버 변수에 @JsonIgnore
@JsonFilter("SomeBeanFilter") // 동적 필터링 
public class SomeBean {
	// @JsonIgnore   // 정적 필터링 : 모든 REST API 요청에서 제외
	private String field1;
	private String field2;
	private String field3;
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	public String getField3() {
		return field3;
	}
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
	
	
}
