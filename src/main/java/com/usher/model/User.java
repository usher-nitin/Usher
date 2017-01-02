package com.usher.model;

public class User implements Comparable {

	private long id;

	private String username;

	private String address;

	private String email;

	private String password;

	private String level;

	private String location;

	private boolean recent;

	public User() {
		id = 0;
	}

	public User(long id, String username, String address, String email, String password, String level, String location,
			boolean recent) {
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
		this.password = password;
		this.level = level;
		this.location = location;
		this.recent = recent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isRecent() {
		return recent;
	}

	public void setRecent(boolean recent) {
		this.recent = recent;
	}

	@Override
	public int compareTo(Object comparestu) {
		int compareage = (int) ((User) comparestu).getId();
		/* For Ascending order */
		return (int) (this.id - compareage);

		/* For Descending order do like this */
		// return compareage-this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", address=" + address + ", email=" + email + ", password="
				+ password + ", level=" + level + ", location=" + location + ", recent=" + recent + "]";
	}

}
