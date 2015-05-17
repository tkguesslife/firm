define([
        'app/Shared/ConfigService',
        'app/app.config'
        ,'app/components/home/homeService'
        ,'app/components/home/homeController'
        
        , 'app/components/user/userController'
        , 'app/components/user/userService'
        
        , 'app/components/company/companyController'
        , 'app/components/company/companyService'
    ],
    function(ConfigService, config, homeService, homeController, userController, userService, companyController, companyService){
    'use strict';

    var firmApp = angular.module('firmApp', ['ngRoute','ngResource','ngGrid', 'ngAnimate']);

//    console.log(ConfigService);
    
    firmApp.constant('ConfigService',{
            COMP_BASE: "assets/app/components/"
        });
    firmApp.config(config);
    firmApp.factory('homeService',homeService);
    firmApp.factory('userService', userService);
    firmApp.factory('companyService', companyService);
    
    firmApp.controller('homeController', homeController);
    firmApp.controller('userController', userController);
    firmApp.controller('companyController', companyController);
});
