angular.module('movies_manager',[])
    .controller('MoviesController', function ($http,$scope,$window) {

        var vm = this;

        vm.movies = [];
        vm.findAllMovies = findAllMovies;
        vm.users = [];
        vm.findAllUsers = findAllUsers;
        vm.updateRestrictedUser = updateRestrictedUser;
        vm.apnd = apnd;

        $scope.mov = {};
        $scope.usr = {};
        var iduser="";
        var append="";

        init();

        function init(){
            findAllMovies();
            findAllUsers();
        }

        $scope.reloadRoute = function() {
            $window.location.reload();
            console.log("Reloaded");
        }

        function apnd() {
            console.log("Appending movies");
            $.each($scope.mov,function(key,value){
                if(value == true){
                    append+=key+"#";
                }
            });
        }

        function us() {
            console.log("Function us");
            $.each($scope.usr,function(key,value){
                iduser = value;
            });
        }

        function findAllMovies(){
            console.log("Showing movies");
            $http.get('resource/movies/all')
                .then(function(response) {
                    vm.movies = response.data;
            });
        }

        function findAllUsers(){
            console.log("Showing users");
            $http.get('resource/users/all')
                .then(function(response) {
                    vm.users = response.data;
            });
        }

        function updateRestrictedUser() {
            console.log("Update Restricted User");
            apnd();us();
            var dataObj = {
                restricted : append
            };
            $http.post('resource/users/update/restrictions/' + iduser,dataObj)
                .then(function(response) {
                    vm.users = response.data;
            });
        }

    });