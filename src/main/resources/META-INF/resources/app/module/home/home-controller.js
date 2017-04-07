/*global app*/
'use strict';

app.controller('HomeController', ['$scope', '$rootScope','$location', 'StorageService', 'NotificationService','ApiService',
    function($scope, $rootScope, $location, StorageService, NotificationService, ApiService) {
        var vm = this;

        vm.init = function() {
            $rootScope.$broadcast('enableMenuBroadcast');
            StorageService.clearNotification();
            StorageService.setSession(qs["token"]);
        };

        var qs = (function(a) {
            if (a == "") return {};
            var b = {};
            for (var i = 0; i < a.length; ++i)
            {
                var p=a[i].split('=', 2);
                if (p.length == 1)
                    b[p[0]] = "";
                else
                    b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
            }
            return b;
        })(window.location.search.substr(1).split('&'));

        vm.init();

    }
]);
