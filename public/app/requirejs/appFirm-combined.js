define('app/Shared/ConfigService',[], function () {


    function ConfigService() {

        return {
            COMP_BASE: "assets/app/components/"
        };

    }

    return ConfigService;


});
define('app/app.config',[], function () {
    

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

define('app/components/home/homeService',[], function () {

    function homeService($http, $resource) {

        var svc = {
            loadSomething: loadSomething
        };

        return svc;

        function loadSomething() {
            return 'loaded something';
        }

    }

    homeService.$inject = ['$http', '$resource'];

    return homeService;

});
define('app/components/home/homeController',[],function(){
    
    function homeController($scope, homeService){
        
        
    }
    
    homeController.$inject=['$scope', 'homeService'];
    
    return homeController;
    
});



define('app/components/user/userController',[], function () {


    function userController($scope, $routeParams, userService) {
        $scope.formData = {};

        userService.getUsers().then(function (result) {
            $scope.users = result;
        });

        $scope.saveUser = function () {
            userService.saveUser($scope.formData);

        };

    }

    userController.$inject = ['$scope', '$routeParams', 'userService'];

    return userController;

});

define('app/components/user/userService',[] , function(){
   
    function userService($http){
       var svc = {
           getUsers: getUsers,
           saveUser: saveUser
       };
       
       return svc;
       
       
       /**
        * get Users
        * @returns {unresolved}
        */
       function getUsers() {
            return $http.get('/user/list')
                    .then(function (result) {
                        return result.data;
                    });
        }
       
       /**
        * Save user
        * @param {type} userData
        * @returns {unresolved}
        */
       function saveUser(userData){
           console.log("Save user called");
           
           return $http({
                method: 'POST',
                url: '/user/save',
                data: $.param(userData), // pass in data as strings
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
            }).then(function(result){
               return result; 
            });
           
       }
        
    }
    
    userService.$inject = ['$http'];
    
    return userService;
    
});

define('app/components/company/companyController',[], function () {

    function companyController(companyService) {
        this.formData = {};

        this.word = "This is my word";

        this.someFunction = function () {
            var someVar = "Hello World";
        };

        this.saveCompany = function () {
            
            companyService.saveCompany(this.formData);
        };
    }


    companyController.$inject = ['companyService'];
    return companyController;

});

define('app/components/company/companyService',[], function(){
    
    function companyService(){
        
        var svc = {
            saveCompany: saveCompany
        };
        
        
        return svc;
        
        function saveCompany(formData){
            console.log("Called save company in the service");
            
        }
        
        
    }
    
    
    companyService.$inject = ["$http"];
    
    
    return companyService;
    
});


define('app/app.module',[
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

require(['app/app.module'],
    function() {
        

        angular.bootstrap(document, ['firmApp']);
    }
);
define("main", function(){});

