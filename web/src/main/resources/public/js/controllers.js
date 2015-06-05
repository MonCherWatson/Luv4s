var luv4sControllers = angular.module('luv4sControllers', []);

luv4sControllers.controller("matchesCtrl", function($scope, matchesResource) {
  matchesResource.query({ region: "EU" }, function(data) {
    $scope.euMatches = data;
  });

  matchesResource.query({ region: "US" }, function(data) {
      $scope.usMatches = data;
    });
});

