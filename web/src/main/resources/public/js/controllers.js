var luv4sControllers = angular.module('luv4sControllers', []);

luv4sControllers.controller("matchesCtrl", function($scope, matchesResource) {
    matchesResource.query({
        zone: "EU"
    }, function(data) {
        $scope.euMatches = data;
    });

    matchesResource.query({
        zone: "US"
    }, function(data) {
        $scope.usMatches = data;
    });
});

luv4sControllers.controller("worldCtrl", function($scope, $rootScope, $routeParams, matchResource, $http) {
    matchResource.get({
        matchId: $routeParams.matchId
    }, function(data) {
        $scope.match = data;
        var worlds = $scope.match.worlds;
        for (var world in worlds) {
            if (worlds.hasOwnProperty(world)) {
                if (worlds[world].id.toString() == $routeParams.worldId) {
                    $rootScope.currentWorld = worlds[world];
                }
            }
        }

    });

    $scope.newScout = function(objective) {
        console.log(objective);
        var newSession = {};
        newSession.objectivePk = objective.pk
        newSession.startTime = new Date();
        newSession.endTime = new Date();
        newSession.endTime.setHours(newSession.endTime.getHours() + 1);
        newSession.description = "1 hour only...";
        newSession.key = $rootScope.currentScoutingKey;

        $http.post('http://localhost:8080/api/scoutingsessions', newSession).
        success(function(data, status, headers, config) {
            console.log(data);
        }).
        error(function(data, status, headers, config) {
            console.log(status);
        });
    };
});


luv4sControllers.controller("matchCtrl", function($scope, $routeParams, matchResource) {
    matchResource.get({
        matchId: $routeParams.matchId
    }, function(data) {
        $scope.match = data;
    });
});


luv4sControllers.controller("signUpCtrl", function($scope, $http, authService) {
    $scope.submit = function() {
        $scope.signUpErrors = {}
        $http.post('http://localhost:8080/api/players', $scope.player).
        success(function(data, status, headers, config) {
            authService.login($scope.player.name, $scope.player.password);
        }).
        error(function(data, status, headers, config) {
            console.log("signUp error");
            if (status == 409) {
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

luv4sControllers.controller("scoutingKeyCtrl", function($scope, $http, $rootScope) {
    $scope.submit = function() {
        var newKey = {};
        newKey.wordId = $rootScope.currentWorld.id
        $http.post('http://localhost:8080/api/keys/', newKey).
        success(function(data, status, headers, config) {
            console.log("Server response: ", data);
            $rootScope.currentScoutingKey = data.uuid;
        }).
        error(function(data, status, headers, config) {
            console.log("oups something went wrong...");

        });
    }
});