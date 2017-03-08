var app = angular.module('app', [
    'ngResource', 'ngRoute', 'ui.router', 'angular-loading-bar', 'chart.js','ngAnimate',
    'navigator','main', 'users_editor','movies_manager','sessions_editor',
    'stats','users_manager','users_editor',
    'PagerService','ngCookies'
    ])
    .config(function ($routeProvider, $locationProvider,$httpProvider,cfpLoadingBarProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/js/content/main/main.html',
                controller:'DateController',
                controllerAs : 'controller'
            })
            .when('/login', {
                templateUrl: '/js/content/login/login.html',
                controller:'navigator',
                controllerAs : 'controller'
            })
            .when('/usermanager', {
                templateUrl: '/js/content/manage_users/users_manager.html',
                controller:'UsersController',
                controllerAs : 'controller'
            })
            .when('/restrictmovies', {
                templateUrl: '/js/content/manage_movies/movies_manager.html',
                controller:'MoviesController',
                controllerAs : 'controller'
            })
            .when('/editsessions', {
                templateUrl: '/js/content/edit_sessions/sessions_editor.html',
                controller:'SessionsController',
                controllerAs : 'controller'
            })
            .when('/editusers', {
                templateUrl: '/js/content/edit_users/users_editor.html',
                controller:'UserEditorController',
                controllerAs : 'controller'
            })
            .when('/statistics', {
                templateUrl: '/js/content/stats/stats.html'
            })
            .otherwise({
                redirectTo: '/'
            });

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

        $locationProvider.hashPrefix('!');
        // $locationProvider.html5Mode({
        //     enabled: true,
        //     requireBase: false
        // });

        cfpLoadingBarProvider.includeSpinner = true;
    })
    // .provider('myCSRF',[function(){
    //     var headerName = 'X-CSRFToken';
    //     var cookieName = 'csrftoken';
    //     var allowedMethods = ['GET'];
    //
    //     this.setHeaderName = function(n) {
    //         headerName = n;
    //     }
    //     this.setCookieName = function(n) {
    //         cookieName = n;
    //     }
    //     this.setAllowedMethods = function(n) {
    //         allowedMethods = n;
    //     }
    //     this.$get = ['$cookies', function($cookies){
    //         return {
    //             'request': function(config) {
    //                 if(allowedMethods.indexOf(config.method) === -1) {
    //                     // do something on success
    //                     config.headers[headerName] = $cookies[cookieName];
    //                 }
    //                 return config;
    //             }
    //         }
    //     }];
    // }]).config(function($httpProvider) {
    //     $httpProvider.interceptors.push('myCSRF');
    // })
    ;
