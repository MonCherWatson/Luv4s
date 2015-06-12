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
