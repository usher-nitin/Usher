package com.usher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "Main";
    }
  
  @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage() {
        return "Home";
    }
  
  @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String getLocationPage() {
        return "Location";
    }
	  @RequestMapping(value = "/usermanagement", method = RequestMethod.GET)
	    public String getUserManagementPage() {
	        return "UserManagement";
	    }
	  
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String getLoginPage() {
	        return "login";
	    }

}