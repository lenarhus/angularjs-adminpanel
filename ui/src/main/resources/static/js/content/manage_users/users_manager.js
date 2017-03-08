angular.module('users_manager',['ngCookies'])
    .controller('UsersController', function ($http,$scope,$state,$window,$cookies) {

        var vm = this;

        vm.showField = false;
        vm.users = [];
        vm.size = "";
        vm.findAllUsers = findAllUsers;
        vm.updateUserState = updateUserState;

        init();

        // vm.token = $("meta[name='_csrf']").attr("content");
        // var header = $("meta[name='_csrf_header']").attr("content");

        // $scope.token = {
        //     xsrf:""
        // };

        // function updateToken(token) {
        //     $scope.token.xsrf = token;
        // }

        function init(){
            findAllUsers();
            vm.size = vm.users.length;
        }

        $scope.reloadRoute = function() {
            $window.location.reload();
            //$state.reload('vm.users');
            console.log("Reloaded");
        }

        function findAllUsers(){
            console.log("Showing users");
            $http.get('resource/users/all/').then(function(response) {
                    vm.users = response.data;
            });
        }

        function updateUserState(id,state) {
            console.log("Updating state");
            var dataObj = {
                state : state
            };
            var url = 'resource/users/update/state/' + id;
            $http.post(url,dataObj).then(function(response) {
                    vm.users = response.data;
            });
        }

    });