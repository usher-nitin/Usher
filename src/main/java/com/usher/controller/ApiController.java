package com.usher.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.usher.model.AuthTokenInfo;
import com.usher.model.User;
import com.usher.utils.TokenUtils;

@RestController
@RequestMapping(value = "v1/api")
public class ApiController {
	public static final Logger logger = LogManager.getLogger(ApiController.class);

	/*
	 * Send a GET request to get list of all users.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List> listAllUsers() {
		AuthTokenInfo tokenInfo = TokenUtils.sendTokenRequest();
		Assert.notNull(tokenInfo, "Authenticate first please......");

		logger.info("Fetching listAllUsers API-----------");
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(TokenUtils.getHeaders());
		ResponseEntity<List> response = restTemplate.exchange(
				TokenUtils.REST_SERVICE_URI + "/user/" + TokenUtils.QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				HttpMethod.GET, request, List.class);
		List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) response.getBody();

		if (usersMap != null) {
			for (LinkedHashMap<String, Object> map : usersMap) {
				logger.info("User : id=" + map.get("id") + ", Name=" + map.get("username") + ", Address="
						+ map.get("address") + ", Email=" + map.get("email"));
				;
			}
		} else {
			logger.info("No user exist----------");
		}
		return response;
	}

	/*
	 * Send a GET request to get a specific user.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		AuthTokenInfo tokenInfo = TokenUtils.sendTokenRequest();
		Assert.notNull(tokenInfo, "Authenticate first please......");
		logger.info("Fetching User with id " + id);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(TokenUtils.getHeaders());
		ResponseEntity<User> response = restTemplate.exchange(
				TokenUtils.REST_SERVICE_URI + "/user/" + id + TokenUtils.QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				HttpMethod.GET, request, User.class);
		User user = response.getBody();
		logger.info(user);
		return response;
	}

	/*
	 * Send a POST request to create a new user.
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public void createUser(@RequestBody User user) {
		AuthTokenInfo tokenInfo = TokenUtils.sendTokenRequest();
		Assert.notNull(tokenInfo, "Authenticate first please......");
		logger.info("Creating User " + user.getUsername());
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Object> request = new HttpEntity<Object>(user, TokenUtils.getHeaders());
		URI uri = restTemplate.postForLocation(
				TokenUtils.REST_SERVICE_URI + "/user/" + TokenUtils.QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				request, User.class);
		logger.info("Location : " + uri.toASCIIString());
	}

	/*
	 * Send a PUT request to update an existing user.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		AuthTokenInfo tokenInfo = TokenUtils.sendTokenRequest();
		Assert.notNull(tokenInfo, "Authenticate first please......");
		logger.info("Updating User " + id);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Object> request = new HttpEntity<Object>(user, TokenUtils.getHeaders());
		ResponseEntity<User> response = restTemplate.exchange(
				TokenUtils.REST_SERVICE_URI + "/user/" + id + TokenUtils.QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				HttpMethod.PUT, request, User.class);
		return response;
	}

	/*
	 * Send a DELETE request to delete a specific user.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		AuthTokenInfo tokenInfo = TokenUtils.sendTokenRequest();
		Assert.notNull(tokenInfo, "Authenticate first please......");
		logger.info("Fetching & Deleting User with id " + id);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(TokenUtils.getHeaders());
		ResponseEntity<User> response = restTemplate.exchange(
				TokenUtils.REST_SERVICE_URI + "/user/" + id + TokenUtils.QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				HttpMethod.DELETE, request, User.class);
		return response;
	}

	/*
	 * Send a DELETE request to delete all users.
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		AuthTokenInfo tokenInfo = TokenUtils.sendTokenRequest();
		Assert.notNull(tokenInfo, "Authenticate first please......");
		logger.info("Deleting All Users");
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(TokenUtils.getHeaders());
		ResponseEntity<User> response = restTemplate.exchange(
				TokenUtils.REST_SERVICE_URI + "/user/" + TokenUtils.QPM_ACCESS_TOKEN + tokenInfo.getAccess_token(),
				HttpMethod.DELETE, request, User.class);
		return response;
	}

}