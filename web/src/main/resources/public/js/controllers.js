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

luv4sControllers.controller("worldCtrl", function($scope, $rootScope, $routeParams, matchResource, scoutingSessionsResource, $http) {
    matchResource.get({
        matchId: $routeParams.matchId
    }, function(data) {
        $scope.match = data;
        var worlds = $scope.match.worlds;
        var wvwMaps = $scope.match.wvwMaps;
        for (var color in worlds) {
            if (worlds.hasOwnProperty(color)) {
                if (worlds[color].id.toString() == $routeParams.worldId) {
                    $rootScope.currentWorld = worlds[color];
                }
            }
        }
        var key = $rootScope.currentScoutingKey;
        if (key !== undefined)
            for (var color in wvwMaps) {
                if (wvwMaps.hasOwnProperty(color)) {
                    var objectives = wvwMaps[color].objectives;
                    for (var i in objectives) {
                        var objective = objectives[i];
                        if ((objective.owner !== undefined) && (objective.owner.id === $rootScope.currentWorld.id)) {
                            scoutingSessionsResource.query({
                                key: key,
                                objectiveId: objective.pk
                            }, function(data) {
                                objective.scoutingSessions = data;

                            });
                        }
                    }
                }
            }

    });

    $scope.newScout = function(objective) {
        var rootScope = $rootScope;

        console.log(objective);
        var startDate = new Date();
        var newSession = {};
        newSession.objectivePk = objective.pk

        newSession.start = startDate.getTime();
        newSession.end = newSession.start + 60000;
        newSession.description = "1 hour only...";
        newSession.key = $rootScope.currentScoutingKey;

        $http.post('http://localhost:8080/api/scoutingsessions', newSession).
        success(function(data, status, headers, config) {
            scoutingSessionsResource.query({
                key: rootScope.currentScoutingKey,
                objectiveId: objective.pk
            }, function(data) {
                objective.scoutingSessions = data;

            });
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
            $scope.signUpErrors.type = {};
            if (status == 409) {
                $scope.signUpErrors.type.validation = true;
                var fields = data.fields;
                for (var property in fields) {
                    if (fields.hasOwnProperty(property)) {
                        $scope.signUpErrors[property] = {};
                        $scope.signUpErrors[property][fields[property]]= true;
                    }
                }
            } else {
                $scope.signUpErrors.type.server = true;
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