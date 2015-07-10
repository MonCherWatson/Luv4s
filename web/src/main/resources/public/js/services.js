var services = angular.module('services', []);

services.factory('authService', function ($http, $window, $rootScope) {
  var authService = {};

  authService.login = function (name, password) {
    return $http
      .get('http://localhost:8080/api/login/'+name+'/'+password)
      .then(function (resp) {
        $rootScope.currentToken = resp.data.token;
        $rootScope.authenticated = true;
        $rootScope.currentPlayerName = name;
      });
  };

  return authService;
});


services.factory('authInterceptor', function ($rootScope, $q, $window) {
  return {
    'request': function (config) {
      config.headers = config.headers || {};
//      if ($window.sessionStorage.token) {
      if ($rootScope.currentToken){
//        config.headers.Authorization = 'X-Auth-Token ' + $rootScope.currentToken;
        config.headers['X-Auth-Token'] = $rootScope.currentToken;
      }
      return config;
    },
    'response': function (response) {
      if (response.status === 401) {
      }
      return response || $q.when(response);
    }
  };
});