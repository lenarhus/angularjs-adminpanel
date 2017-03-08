angular.module('users_editor',[])
    .controller('UserEditorController', function ($http,$scope,$state,$window) {

        var vm = this;

        vm.showField = false;
        vm.users = [];
        vm.size = "";
        vm.findAllUsers = findAllUsers;
        vm.updateUserState = updateUserState;
        vm.newUser = newUser;
        vm.deleteUser = deleteUser;
        vm.updateUser = updateUser;

        init();

        function init(){
            findAllUsers();
            vm.size = vm.users.length;
        }

        $scope.reloadRoute = function() {
            $window.location.reload();
            //$state.reload('vm.users');
            console.log("Reloaded");
        }

        $scope.clearInput = function () {
            $scope.newuser = null;
            console.log("Cleared input");
        }

        $scope.newuser = {
            idusers:"",
            user_id:"",
            username:"",
            lastseen:"",
            state:""
        };

        $scope.editAction = function (id,user_id,username,lastseen,state) {
            // var i;
            // for (i = 0; i < vm.users.length; i++) {
            //     var user = vm.users[i];
            //     if (user.idusers = id) {
            $scope.newuser.idusers = id;
            $scope.newuser.user_id = user_id;
            $scope.newuser.username = username;
            $scope.newuser.lastseen = lastseen;
            $scope.newuser.state = state;
            //     }
            // }
        }

        function findAllUsers(){
            console.log("Showing users");
            $http.get('resource/users/all')
                .then(function(response) {
                    vm.users = response.data;
            });
        }

        function updateUserState(id,state) {
            console.log("Update state");
            var dataObj = {
                state : state
            };
            $http.post('resource/users/update/state/' + id,dataObj)
                .then(function(response) {
                    vm.users = response.data;
            });
        }

        function newUser() {
            console.log("New user");
            var dataObj = {
                user_id: $scope.newuser.user_id,
                username: $scope.newuser.username,
                lastseen: $scope.newuser.lastseen,
                state: $scope.newuser.state
            };
            $http.post('resource/users/new/',dataObj)
                .then(function(response) {
                    findAllUsers();
                    vm.users = response.data;
            });
        }

        function deleteUser(id) {
            console.log("Delete users");
            $http.post('resource/users/delete/' + id)
                .then(function(response) {
                    findAllUsers();
                    vm.users = response.data;
            });
        }

        function updateUser(id) {
            console.log("Update user");
            var dataObj = {
                idusers: $scope.newuser.idusers,
                username: $scope.newuser.username,
                lastseen: $scope.newuser.lastseen,
                state: $scope.newuser.state
            };
            $http.patch('resource/users/update/' + id,dataObj)
                .then(function(response) {
                    findAllUsers();
                    vm.users = response.data;
            });
        }

    });