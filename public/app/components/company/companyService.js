define([], function(){
    
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

