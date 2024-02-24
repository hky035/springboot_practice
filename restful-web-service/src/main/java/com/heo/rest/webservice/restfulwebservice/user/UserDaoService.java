package com.heo.rest.webservice.restfulwebservice.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	private static Integer countUser = 0;
	static {
		users.add(new User(++countUser, "Kim",LocalDate.now().minusYears(10)));
		users.add(new User(++countUser, "Heo",LocalDate.now().minusYears(20)));
		users.add(new User(++countUser, "Park",LocalDate.now().minusYears(30)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(Integer id) {
		return users.stream()
				.filter(user -> user.getId().equals(id))
				.findAny().get();
	}
	
	public User save(User user) {
		user.setId(++countUser);
		users.add(user);
		return user;
	}
	
}
