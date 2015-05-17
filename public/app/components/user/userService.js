define([] , function(){
   
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
