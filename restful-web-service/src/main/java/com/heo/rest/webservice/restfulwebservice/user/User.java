package com.heo.rest.webservice.restfulwebservice.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heo.rest.webservice.restfulwebservice.post.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message="Name is at least 2 characters")
	// @JsonProperty("user_name") // Json 응답 시, 필드의 이름을 name에서 user_name으로 변경 
	private String name;
	@Past(message="Birth date should be in the past")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy="user") // Post의 user 멤버에 의해 매핑
	@JsonIgnore
	private List<Post> posts;
	
	public User() {
		
	}
	
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public List<Post> getPosts() {
		return posts;
	}

}
