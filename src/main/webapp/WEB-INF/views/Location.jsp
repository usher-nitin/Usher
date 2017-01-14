<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Usher Home</title>  
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
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="LocationController">
      <div class="logincontainer">
      <form name="myForm" class="form-horizontal">
                      <div class="row">
                          <div class="form-group col-md-12">
                              <div class="col-md-7">
                                  <input type="text" ng-model="Location" name="location" class="username form-control input-sm" placeholder="Enter location" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.location.$error.required">This is a required field</span>
                                      <span ng-show="myForm.location.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="button"  ng-click="WriteCookie()" value="{{message}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="LocationController.RemoveCookie()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                      </form>
                      </div>
</div>      
      <div class="login-container">
<div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Login</span></div>
              <div class="formcontainer">
              <form name="myForm" class="form-horizontal">
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-4 control-lable" for="file">Email</label>
                              <div class="col-md-7">
                                  <input type="email" name="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$invalid">This email is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                        
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-4 control-lable" for="file">Password</label>
                              <div class="col-md-7">
                                  <input type="password" class="input-sm" placeholder="Enter your Password"/>
                              </div>
                          </div>
                      </div>
                       <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="Login" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Sign Up</button>
                          </div>
                      </div>
                      </form>
              </div>
              </div>
</div>

      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular-cookies.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/controller/location_controller.js' />"></script>
  </body>
</html>