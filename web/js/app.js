var jlApp = angular.module('jlApp', ['jlControllers','jlServices','ui.bootstrap','ngAnimate']);
var controllers = angular.module('jlControllers', []);
var services = angular.module('jlServices', ['ngResource','ngWebSocket']);



jlApp.config(['$routeProvider',
 function($routeProvider) {
 $routeProvider
 .when('/', {
    templateUrl: 'html/devices.html'

 })
 .when('/device/:device',{
    templateUrl: 'html/devices.html',
    controller: 'MainCtrl'
 })
 .when('/device/:device/session/:sessionId',{
    templateUrl: 'html/devices.html',
     controller: 'MainCtrl'
 })
 .when('/admin', {
    templateUrl: 'html/admin.html',
    controller: 'AdminCtrl'
 })
 .when('/user-menu', {
    templateUrl: 'html/user-menu.html',
    controller: 'UserMenuCtrl'
 })
 .otherwise({
 redirectTo: '/',
 controller: 'MainCtrl'
 });
 }]);
