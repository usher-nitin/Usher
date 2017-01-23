<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Usher Home</title>
<base href="/" />  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myMainApp" class="ng-cloak" ng-controller="mainController">
  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Usher!! - your path finder</a>
    </div>
     <form name="myForm">
    <ul class="nav navbar-nav" style="float:right;">
      <li class="active"><a href="#">About us</a></li>
      <li class="active"><a href="#">Contact us</a></li>
     
      <li>
      <h6 style="margin-top: 2px;margin-bottom: 2px;color: #fff;">
						Sign in to My Account
					</h6>
            <input type='email' ng-pattern='/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/' name='email' id='email' ng-model='ctrl.user.email' class="email" style="padding: 5px 10px;margin-right: 3px;" placeholder="Enter your Email" required />
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$error.pattern">This field is invalid </span>
                                  </div>
      </li>
      <li>
      <h6 style="margin-top: 2px;margin-bottom: 2px;color: #fff;"></h6>
      <input type="password" style="padding: 5px 10px;margin-top: 15px;" placeholder="Enter your Password"/></li>
      <li><button class="btn-primary" style="margin-top: 16px;padding: 5px 15px;">Login</button></li>
      
    </ul>
    </form> 
  </div>
</nav>
      <div class="generic-container" >
          <div style="height:100px;text-align:center;font-size:initial;font-style:italic;">This Usher web app helps you to find the exact location wherever you want to go...</div>
<div class="formcontainer">
<ui-view></ui-view>
</div>
          </div>
          
</div>      
      
	 <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.4.0/angular-ui-router.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
      <script src="<c:url value='/static/js/controller/main_controller.js' />"></script>
  </body>
</html>	