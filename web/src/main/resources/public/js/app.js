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
//      when('/match/:phoneId', {
//        templateUrl: 'partials/phone-detail.html',
//        controller: 'PhoneDetailCtrl'
//      }).
      otherwise({
        redirectTo: '/matches'
      });
  }]);


  luv4sApp.factory("matchesResource", function($resource) {
    return $resource("http://localhost:8080/ws/matches/:region");
  });



  luv4sApp.config(function($translateProvider) {
      $translateProvider.useUrlLoader('http://localhost:8080/ws/translations');
      $translateProvider.preferredLanguage('fr');
  });