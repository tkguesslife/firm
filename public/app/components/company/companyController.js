define([], function () {

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
