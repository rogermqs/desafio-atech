/*global app, angular*/
'use strict';

app.controller('HeaderController', ['$scope', '$timeout', '$mdSidenav', '$location', 'StorageService', 'NotificationService',
    function($scope, $timeout, $mdSidenav, $location, StorageService, NotificationService) {
        var vm = this;
        vm.voltarMenu = false;
        NotificationService.verifyUnread();
        vm.openPage = function(url){
            $location.path(url);
            vm.toggleLeft();
        };

        vm.loadNotification = function(){
            vm.listNotification = StorageService.getNotification();
        };

        vm.toggleLeft = buildToggler('left');
        $scope.toggleRight = buildToggler('right');

        function buildToggler(componentId) {
            if(componentId === 'left'){
                vm.voltarMenu = vm.voltarMenu ? false : true;
            }
            return function() {
                $mdSidenav(componentId).toggle();
            };
        }
        function enableMenu() {
          vm.disableMenu = false;
        }
        function disableMenu() {
          vm.disableMenu = true;
        }

        $scope.$on('enableMenuBroadcast', function (event) {
          enableMenu();
        });
        $scope.$on('disableMenuBroadcast', function (event) {
          disableMenu();
        });
        $scope.$on('closeMenuBroadcast', function (event) {
          vm.toggleLeft();
        });        

        angular.element(document).ready(function () {
            if(StorageService.isLogged()){
                enableMenu();
            }else{
                disableMenu();
            }
        });        

    }
]);
