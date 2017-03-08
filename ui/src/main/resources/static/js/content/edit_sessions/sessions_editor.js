angular.module('sessions_editor',[])
    .controller('SessionsController', function ($http,$window,$scope,PagerService,$filter,$log) {

        var vm = this;

        vm.pager = {};
        vm.setPage = setPage;
        vm.showField = false;
        vm.sessions = [];
        vm.showSessions = showSessions;
        vm.updateSession = updateSession;
        vm.newSession = newSession;
        vm.deleteSession = deleteSession;
        vm.editActionSession = editActionSession;

        $scope.reloadRoute = function() {
            $window.location.reload();
        }

        $scope.clearInput = function () {
            $scope.new_session = null;
        }

        init();

        function init() {
            showSessions();
        }

        function setPage(page) {
            if (page < 1 || page > vm.pager.totalPages) {
                return;
            }

            // get pager object from service
            vm.pager = PagerService.GetPager(vm.sessions.length, page);

            // get current page of items
            vm.items = vm.sessions.slice(vm.pager.startIndex, vm.pager.endIndex + 1);
        }

        function showSessions() {
            $http.get('resource/sessions/all')
                .then(function(response) {
                    console.log("Showing sessions");
                    vm.sessions = response.data;
                    vm.setPage(1);
            });
        }

        $scope.new_session = {
            idSession:"1",
            idCinema:"wqwq",
            idMovie:"",
            price:"",
            time:"",
            idFormat:""
        }

        $scope.$watch('new_session.time', function (newValue) {
            $scope.new_session.time = $filter('date')(newValue, 'HH:mm:ss');
        });

        function editActionSession(idSession,idCinema,idMovie,price,time,idFormat) {
            console.log("Edit action");
            $scope.new_session.idSession = idSession;
            $scope.new_session.idCinema = idCinema;//vm.cinemas[idCinema].value
            $scope.new_session.idMovie = idMovie;
            $scope.new_session.price = price;
            $scope.new_session.time = time;
            $scope.new_session.idFormat = idFormat;
        }

        function newSession() {
            console.log("New session");
            var dataObj = {
                idSession: $scope.new_session.idSession,
                idCinema: $scope.new_session.idCinema,
                idMovie: $scope.new_session.idMovie,
                price: $scope.new_session.price,
                time: $scope.new_session.time,
                idFormat: $scope.new_session.idFormat
            };
            $http.post('resource/sessions/new/', dataObj)
                .then(function(response) {
                    showSessions();
                    vm.sessions = response.data;
                });
        }

        function deleteSession(id) {
            console.log("Delete session");
            $http.post('resource/sessions/delete/' + id)
                .then(function(response) {
                    showSessions();
                    vm.sessions = response.data;
                });
        }

        function updateSession(id) {
            console.log("Update session");
            var dataObj = {
                idSession: $scope.new_session.idSession,
                idCinema: $scope.new_session.idCinema,
                idMovie: $scope.new_session.idMovie,
                price: $scope.new_session.price,
                time: $scope.new_session.time,
                idFormat: $scope.new_session.idFormat
            };
            $http.patch('resource/sessions/update/' + id,dataObj)
                .then(function(response) {
                    showSessions();
                    vm.sessions = response.data;
                });
        }

        vm.cinemas = [
            {id:1,value:"Angleterre Cinema Lounge"},
            {id:2,value:"Cinema Grand Palace"},
            {id:3,value:"Mori Cinema"},
            {id:4,value:"Roof Cinema"},
            {id:5,value:"SkyFilm"}
        ]

        vm.movies = [
            {id:373158,value:"Райское озеро"},
            {id:425673,value:"Охотники за привидениями"},
            {id:461939,value:"Линкольн для адвоката"},
            {id:463464,value:"Афера под прикрытием"},
            {id:468522,value:"Отряд самоубийц"},
            {id:726794,value:"Любой ценой"}
        ]

        vm.formats = [
            {id:1,value:"2D"},
            {id:2,value:"3D"},
            {id:3,value:"DA 3D"},
            {id:4,value:"2D 4DX"},
            {id:5,value:"3D IMAX"},
            {id:6,value:"3D 4DX"},
            {id:7,value:"DA 2D"}
        ]

    }
    );