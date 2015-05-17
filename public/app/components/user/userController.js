define([], function () {


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
