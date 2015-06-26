var services = angular.module('services', []);

services.factory('authService', function ($http, $window, $rootScope) {
  var authService = {};

  authService.login = function (name, password) {
    return $http
      .get('http://localhost:8080/api/login/'+name+'/'+password)
      .then(function (resp) {
        $window.sessionStorage.token = resp.data.token;
        $rootScope.authenticated = true;
      });
  };

  return authService;
});


services.factory('authInterceptor', function ($rootScope, $q, $window) {
  return {
    request: function (config) {
      config.headers = config.headers || {};
      if ($window.sessionStorage.token) {
        config.headers.Authorization = 'X-Auth-Token ' + $window.sessionStorage.token;
      }
      return config;
    },
    response: function (response) {
      if (response.status === 401) {
      }
      return response || $q.when(response);
    }
  };
});