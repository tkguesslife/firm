define([], function () {
    'use strict';

    function config($routeProvider, ConfigService) {
        $routeProvider.
                when('/home', {
                    templateUrl: ConfigService.COMP_BASE+'/home/homeView.html',
                    controller: 'homeController'
                }).
                when('/user', {
                    templateUrl: ConfigService.COMP_BASE+'/user/list.html',
                    controller: 'userController'
                }).
                when('/user/new', {
                    templateUrl: ConfigService.COMP_BASE+'/user/form.html',
                    controller: 'userController'
                }).
                when('/user/:id', {
                    templateUrl: ConfigService.COMP_BASE+'/user/form.html',
                    controller: 'userController'
                }).
                when('/company', {
                    templateUrl: ConfigService.COMP_BASE+'/company/list.html',
                    controller: 'companyController',
                    controllerAs: 'companyCtrl'
                }).
                when('/company/new', {
                    templateUrl: ConfigService.COMP_BASE+'/company/new.html',
                    controller: 'companyController',
                    controllerAs: 'companyCtrl'
                }).
                        
                otherwise({
                    redirectTo: '/home'
                });
    }
    
    config.$inject=['$routeProvider', 'ConfigService'];
    return config;

});
