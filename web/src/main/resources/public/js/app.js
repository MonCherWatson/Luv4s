'use strict';

var luv4sApp = angular.module('luv4sApp', ['ngResource',
    'pascalprecht.translate',
     'ngRoute',
     'luv4sControllers']);

luv4sApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/matches', {
        templateUrl: 'partials/matches.html',
        controller: 'matchesCtrl'
      }).
      when('/match/:matchId', {
        templateUrl: 'partials/match.html',
        controller: 'matchCtrl'
      }).
       when('/player/signUp', {
              templateUrl: 'partials/signUp.html',
              controller: 'signUpCtrl'
            }).
      otherwise({
        redirectTo: '/matches'
      });
  }]);


  luv4sApp.factory('matchesResource', function($resource) {
    return $resource("http://localhost:8080/api/matches?zone=:zone");
  });


luv4sApp.factory('matchResource', function($resource) {
    return $resource("http://localhost:8080/api/matches/:matchId");
  });


  luv4sApp.config(function($translateProvider) {
      $translateProvider.useUrlLoader('http://localhost:8080/api/translations');
      $translateProvider.preferredLanguage('fr');
  });


  luv4sApp.filter('onlyHardStuff', function () {
    return function (items) {
      return items.filter(function (item) {
        return /KEEP|TOWER|CASTLE/.test(item.description.type);
//        return true;
      });
    };
  });