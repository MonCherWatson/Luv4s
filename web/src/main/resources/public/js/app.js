'use strict';

var luv4sApp = angular.module('luv4sApp', ['ngResource',
    'pascalprecht.translate',
     'ngRoute',
     'luv4sControllers',
     'services']);

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
        when('/world/:worldId/:matchId', {
            templateUrl: 'partials/world.html',
            controller: 'worldCtrl'
        }).
      when('/signUp', {
        templateUrl: 'partials/signUp.html',
        controller: 'signUpCtrl'
       }).
       when('/login', {
         templateUrl: 'partials/login.html',
         controller: 'loginCtrl'
       }).
       when('/nav', {
                templateUrl: 'partials/nav.html'
              }).
      otherwise({
        redirectTo: '/nav'
      });
  }]);


  luv4sApp.factory('matchesResource', function($resource) {
    return $resource("/luv4s/api/matches?zone=:zone");
  });


luv4sApp.factory('matchResource', function($resource) {
    return $resource("/luv4s/api/matches/:matchId");
  });


  luv4sApp.config(function($translateProvider) {
      $translateProvider.useUrlLoader('/luv4s/api/translations');
      $translateProvider.preferredLanguage('fr');
  });


  luv4sApp.filter('onlyHardStuff', function () {
    return function (items) {
      return items.filter(function (item) {
        return /KEEP|TOWER|CASTLE/.test(item.description.type);
      });
    };
  });

