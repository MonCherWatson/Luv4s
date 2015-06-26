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


luv4sControllers.controller("signUpCtrl", function($scope, $http, authService) {
    $scope.submit = function() {
        $scope.signUpErrors = {}
        $http.post('http://localhost:8080/api/player', $scope.player).
          success(function(data, status, headers, config) {
            authService.login($scope.player.name, $scope.player.password);
          }).
          error(function(data, status, headers, config) {
            console.log("signUp error");
            if(status == 409) {
            var fields = data.fields;
                for (var property in fields) {
                    if (fields.hasOwnProperty(property)) {
                        $scope.signUpForm[property].$setValidity('server', false)
                        $scope.signUpErrors[property] = fields[property];
                    }
                }
            }

          });
    }
});


luv4sControllers.controller("loginCtrl", function(authService, $rootScope, $scope) {
    $scope.submit = function() {
        authService.login($scope.player.name, $scope.player.password);
        $scope.invalid = !($rootScope.authenticated === true);
    }
});