/*global app*/
'use strict';

app.controller('FormMercadoriaController', [ '$scope', '$rootScope', 'NotificationService', 'ApiService',  '$route', '$routeParams',
    function ($scope, $rootScope, NotificationService, ApiService,  $route, $routeParams) {
        var vm = this;
        vm.edited  = false;
        vm.form = {};
        if( $routeParams.id )
        {
        	ApiService.get("rest/mercadoria/"+$routeParams.id)
        	.then(
    	        function (response) {
    	          vm.form = response.data;
    	        },
    	        function (error) {
    	          console.log(error.message);
    	        }
    	    );
        	vm.edited  = true;
        }

        vm.closeMenu = function () {
            $rootScope.$broadcast('enableMenuBroadcast');
        };

        vm.salvar = function(){
			if(!$scope.form.$valid){
				NotificationService.send('Required fields');
				return false;
			}

        	if( vm.edited )
        	{
        		ApiService.put("rest/mercadoria",vm.form)
            	.then(
        	        function (response) {
        	        	if( response.success === false )
        	        	{
        	        		NotificationService.send(response.message);
        	        	}
        	        	else
        	        	{
                            vm.selectedTab = 0;
              	          	NotificationService.send('Product send to be change');
        	        	}
        	        }
        	    );
        	}
        	else
        	{
        		ApiService.post("rest/mercadoria",vm.form)
            	.then(
        	        function (response) {
        	        	if( response.success === false )
        	        	{
        	        		NotificationService.send(response.message);
        	        	}
        	        	else
        	        	{
        	        		vm.clear();
              	          	NotificationService.send('Product send to be register');
        	        	}
        	        }
        	    );
        	}
        }

        vm.clear = function()
        {
        	vm.form = {};
    		vm.edited = false;
        }
	}
]);
