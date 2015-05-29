var luv4sApp = angular.module('luv4sApp', ['ngResource']);

luv4sApp.factory("Matches", function($resource) {
  return $resource("http://localhost:8080/ws/matches/:region");
});


luv4sApp.controller("MatchesCtrl", function($scope, Matches) {
  Matches.query({ region: "EU" }, function(data) {
    $scope.euMatches = data;
  });

  Matches.query({ region: "US" }, function(data) {
      $scope.usMatches = data;
    });
});

//luv4sApp.controller("USMatchesCtrl", function($scope) {
//  $scope.phones = [
//      {'name': 'Nexus S',
//       'snippet': 'Fast just got faster with Nexus S.'},
//      {'name': 'Motorola XOOM™ with Wi-Fi',
//       'snippet': 'The Next, Next Generation tablet.'},
//      {'name': 'MOTOROLA XOOM™',
//       'snippet': 'The Next, Next Generation tablet.'}
//    ];
//});
