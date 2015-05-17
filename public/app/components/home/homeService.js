define([], function () {

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