/*global app*/
'use strict';

app.controller('ListMercadoriaController', ['$scope', '$rootScope', '$timeout', '$location', 'NotificationService', 'ApiService',
    function ($scope, $rootScope, $timeout, $location, NotificationService, ApiService) {
        var vm = this;
        vm.form = {};
        
        vm.query = {
		    limit: 10,
		    page: 1,
		    order: "nome"
        };

        $scope.selected = [];
        $scope.products = [];
        
        vm.limpar = function(){
        	vm.form = {};
        }
        
       vm.success =  function(response) {
        	vm.products = response.data.content;
        	vm.query.total = response.data.totalElements;
            if(vm.products.length === 0){
                NotificationService.send('No produts found!');
            }
          }
       
       vm.order = function(order) {
    	   vm.query.order = order;
           vm.pesquisar();
       };
        
        vm.onPaginate = function(page, limit) {
            vm.query.limit = limit;
            vm.query.page = page;
            vm.pesquisar();
       };
        
        vm.pesquisar = function(){
        	
            $scope.promisse = ApiService.getPage("rest/mercadoria/filter", {
				        		page: vm.query.page -1, 
								size: vm.query.limit,
								sort: vm.query.order ,
						            preco : vm.form.preco,
						            codigo : vm.form.codigo,
						            nome : vm.form.nome
								});
            
            $scope.promisse.then(vm.success);
        };
        
        vm.edited = function(idMercadoria){
        	 $location.path("mercadoria/" + idMercadoria);
        };
        
        vm.remove = function(idMercadoria){
        	ApiService.delete("rest/mercadoria/"+idMercadoria)
        	.then(
    	        function (data) {
    	        	NotificationService.send('Product successfully deleted');
    	        	vm.pesquisar(vm.form);
    	        },
    	        function (error) {
                  NotificationService.send(error.message);
    	          console.log(error);
    	        }
    	    );  
        };
    }
]);