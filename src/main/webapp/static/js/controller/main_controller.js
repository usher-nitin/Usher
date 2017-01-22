var app = angular.module("myMainApp", ["ui.router"]);
app.config(function($stateProvider, $urlMatcherFactoryProvider, $urlRouterProvider, $locationProvider) {
    //$routeProvider.caseInsensitiveMatch=true;
	//$urlRouterProvider.otherwise("/main");
	//$urlRouterProvider.otherwise(function ($injector, $location) {
      //  var $state = $injector.get('$state');

        //$state.go('main', {
          //  title: "Page not found",
            //message: 'Could not find a state associated with url "'+$location.$$url+'"'
        //});
    //});
	$urlMatcherFactoryProvider.caseInsensitive(true);
	$stateProvider
    .state("main", {
		//url: '/main',
        templateUrl : "Main.jsp"
    })
    .state("location", {
		//url: '/location',
        templateUrl : "Usher/location"
    })
    .state("home", {
		//url: '/home',
        templateUrl : "Usher/home"
    })
	
	$locationProvider.html5Mode(true);
})


.controller('mainController', function($scope, $rootScope, $state, $location){
		//alert("ReadCookie");
		$rootScope.rootValue="Main";
		$scope.IsCookieFound = $.cookie("CookieValue");
		//alert($scope.IsCookieFound);
		if($scope.IsCookieFound!==undefined)
		{
			$state.go('home');
		}
		else{
			$state.go('location');
		} 
})

.controller('locationController', function($scope, $rootScope, $state, $location){
	$scope.message="click!!";
	if($rootScope.rootValue==undefined){
		$state.go('main');
		}
	
    $scope.WriteCookie = function(){
		//alert("WriteCookie");
		$.cookie("CookieValue",$scope.Location);
		//alert($.cookie("CookieValue"));
        $state.go('home');
    };
})


.controller('homeController', function($scope, $rootScope, $state, $location){
	if($rootScope.rootValue==undefined){
		$state.go('main');
		}
	else{
    $scope.askQuestion = function(){
		alert("Home Controller ask Ques");
		//$.cookie("CookieValue",$scope.cookieValue);
		//alert($.cookie("CookieValue"));
        //$state.go('home');
    };
	}
});
