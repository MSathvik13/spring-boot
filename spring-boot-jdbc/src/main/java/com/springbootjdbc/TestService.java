package com.springbootjdbc;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	static Scanner in = new Scanner(System.in);
	@Autowired
	UserDao dao;

	Integer getCountOfUsers() {
		return dao.getCountOfUsers();
	}

	String getUserName(int id, String address) {
		return dao.getUserName(id, address);
	}
	
	String addUser(User user) {
		return dao.addUser(user);
	}
	
	String removeUser(int id) {
		return dao.removeUser(id);
	}
	
	String updateUser(User user) {
		return dao.updateUser(user);
	}
	
	List<User> getAllUsers() {
		return dao.getAllUsers();
	}
	
	User getUserById(int id) {
		return dao.getUserById(id);
	}

}
