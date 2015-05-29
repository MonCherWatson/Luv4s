var luv4sApp = angular.module('luv4sApp', ['ngResource','pascalprecht.translate']);

luv4sApp.factory("Matches", function($resource) {
  return $resource("http://localhost:8080/ws/matches/:region");
});



luv4sApp.config(function($translateProvider) {
    $translateProvider.useUrlLoader('http://localhost:8080/ws/translations');
    $translateProvider.preferredLanguage('fr');
});

luv4sApp.controller("MatchesCtrl", function($scope, Matches) {
  Matches.query({ region: "EU" }, function(data) {
    $scope.euMatches = data;
  });

  Matches.query({ region: "US" }, function(data) {
      $scope.usMatches = data;
    });
});

