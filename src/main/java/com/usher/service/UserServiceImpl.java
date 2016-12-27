package com.usher.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.usher.model.User;
import com.usher.utils.AppUtils;

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
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
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
		List<User> users = new ArrayList<User>();

		// User (long id, String username, String address, String email,
		// String password, String level, String location, boolean recent)

		users.add(new User(counter.incrementAndGet(), "Sumit Dang", "Washington DC", "sumit.acse@gmail.com",
				AppUtils.generateOTP(), "L0", AppUtils.generateLocationKey(), true));
		users.add(new User(counter.incrementAndGet(), "Nitin Goel", "Gurgaon, India", "nitingoel16aug@gmail.com",
				AppUtils.generateLocationKey(), "L0", AppUtils.generateLocationKey(), true));
		users.add(new User(counter.incrementAndGet(), "Shivani", "New Delhi", "shivani@gmail.com",
				AppUtils.generateOTP(), "L1", AppUtils.generateLocationKey(), true));
		users.add(new User(counter.incrementAndGet(), "Akash", "Pune, India", "mail2akashdang@gmail.com",
				AppUtils.generateLocationKey(), "L2", AppUtils.generateLocationKey(), true));
		return users;
	}

}
