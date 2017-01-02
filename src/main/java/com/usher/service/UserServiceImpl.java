package com.usher.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.usher.model.User;
import com.usher.model.UserContentCoverter;
import com.usher.utils.FileUtils;
import com.usher.utils.UserUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User findByName(String name) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
		HashMap<String, String> hmap = new HashMap<>();
		hmap.put(counter.toString(), UserContentCoverter.convertToDatabaseColumn(user));
		FileUtils.appendToFile("login", hmap);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
		HashMap<String, String> hmap = new HashMap<>();
		hmap.put(String.valueOf(user.getId()), UserContentCoverter.convertToDatabaseColumn(user));
		FileUtils.updateInFile("login", hmap);
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				FileUtils.deleteFromFile("login", String.valueOf(user.getId()));
			}
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		List<User> users = UserUtils.getUsers(FileUtils.readFile("login"));
		Collections.sort(users);
		counter.set(UserUtils.getMaxUserId(users));
		return users;
	}

}
