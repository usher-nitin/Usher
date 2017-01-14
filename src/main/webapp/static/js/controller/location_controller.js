'use strict';

angular.module('myApp', ['ngCookies'])
.config(function ($routeProvider){
$routeProvider
.when("/",{
	templateUrl:"UserManagement.jsp",
	controller:"UserController"
})	
})

.controller('LocationController', function ($scope, $cookies) {
    
	$scope.message="click!!";
	
	
	$scope.ReadCookie = function () {
	        alert($cookies.get('Location'));
	};
	$scope.WriteCookie = function () {
		alert("writecookie");
        $cookies.put("Location", $scope.Location);
        $scope.ReadCookie();
    };
        $scope.RemoveCookie = function RemoveCookie() {
        $cookies.remove('Location');
    };
});

