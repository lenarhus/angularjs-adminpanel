angular.module('main',[])
    .controller('DateController', function ($http) {

        var vm = this;

        vm.todayusers = 0;
        vm.weekly = 0;
        vm.users = [];

        init();

        function init(){
            findAllUsers();
        }

        function findAllUsers(){
            console.log("Showing users");
            $http.get('resource/users/all').then(function(response) {
                    vm.users = response.data;
                    compare(vm.users);
            });
        }

        function compare(users){
            var i;
            var today = new Date().format('Y-m-d');
            var week = new Date(today);
            week.setDate(week.getDate()-7);
            var newweek = week.format('Y-m-d');
            for (i = 0; i < users.length; i++) {
                var user = users[i];
                if (user.lastseen == today) {
                    vm.todayusers += 1;
                }
                if (user.lastseen >= newweek) {
                    vm.weekly += 1;
                }
            }
        }

    }).controller('Map', function ($http) {

        var vm = this;
        vm.users = [];

        init();

        function init(){
            findAllUsers();
        }

        function findAllUsers(){
            console.log("Showing users");
            $http.get('resource/users/all').then(function(response) {
                    vm.users = response.data;
                    initialize(vm.users);
            });
        }

        function initialize(users) {
            var i;
            var myOptions = {
                zoom: 10,
                center: new google.maps.LatLng(59.9342802, 30.3350986),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };

            var map = new google.maps.Map(document.getElementById("map"), myOptions);


            var infowindow =  new google.maps.InfoWindow({
                content: ''
            });

            for (i = 0; i < users.length; i++) {
                var user = users[i];
                var marker = new google.maps.Marker({
                    map: map,
                    position: new google.maps.LatLng(user.lat, user.lon)
                });
                bindInfoWindow(marker, map, infowindow, "<p>" + user.username );//, "<p>" + Locations[i].descr + "</p>",Locations[i].title

            }

        }

        function codeAddress(address) {
            var i;
            var loc = [];
            var geocoder = new google.maps.Geocoder();
            for (i = 0; i < 1; i++) {
                geocoder.geocode({'address': address}, function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        loc[0] = results[0].geometry.location;
                        return loc[0];
                    } else {
                        alert("Geocode was not successful for the following reason: " + status);
                    }
                });
            }
        }

        function bindInfoWindow(marker, map, infowindow, html, Ltitle) {
            google.maps.event.addListener(marker, 'mouseover', function() {
                infowindow.setContent(html);
                infowindow.open(map, marker);

            });
            google.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();

            });
        }

    });