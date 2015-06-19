var luv4sControllers = angular.module('luv4sControllers', []);

luv4sControllers.controller("matchesCtrl", function($scope, matchesResource) {
  matchesResource.query({ zone: "EU" }, function(data) {
    $scope.euMatches = data;
  });

  matchesResource.query({ zone: "US" }, function(data) {
      $scope.usMatches = data;
    });
});



luv4sControllers.controller("matchCtrl", function($scope, $routeParams, matchResource) {
 matchResource.get({ matchId: $routeParams.matchId }, function(data) {
      $scope.match = data;
    });
});


luv4sControllers.controller("signUpCtrl", function($scope, $http) {
    $scope.submit = function() {
        $http.post('http://localhost:8080/api/player/create', $scope.player).
          success(function(data, status, headers, config) {
            console.log("success");
          }).
          error(function(data, status, headers, config) {
            console.log("error");
          });
    }
});