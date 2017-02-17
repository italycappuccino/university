var myApp = angular.module('myApp',[]);

myApp.controller('DoubleController', ['$scope', function($scope) {
  $scope.double = function(value) { return value * 2; };
}]);

myApp.controller('SpicyController', ['$scope', function($scope) {
    $scope.spice = 'very';

    $scope.chiliSpicy = function() {
        $scope.spice = 'chili';
    };

    $scope.jalapenoSpicy = function() {
        $scope.spice = 'jalapeño';
    };
}]);


myApp.controller('SpicyController2', ['$scope', function($scope) {
    $scope.customSpice = "wasabi";
    $scope.spice = 'very';

    $scope.spicy = function(spice) {
        $scope.spice = spice;
    };
}]);


myApp.controller('MainController', ['$scope', function($scope) {
	  $scope.timeOfDay = 'morning';
	  $scope.name = 'Nikki';
	}]);
	myApp.controller('ChildController', ['$scope', function($scope) {
	  $scope.name = 'Mattie';
	}]);
	myApp.controller('GrandChildController', ['$scope', function($scope) {
	  $scope.timeOfDay = 'evening';
	  $scope.name = 'Gingerbread Baby';
	}]);