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
		$rootScope.rootValue="Main";
		$scope.IsCookieFound = $.cookie("UsherLocationKey");
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
		$.cookie("UsherLocationKey",$scope.Location);
        $state.go('home');
    };
})


.controller('homeController', function($scope, $rootScope, $state, $location){
	if($rootScope.rootValue==undefined){
		$state.go('main');
		}
	else{
    $scope.changeLocation = function(){
		$.removeCookie('UsherLocationKey', { path: '/Usher' });
		$state.go('location');
    };
	}
});
