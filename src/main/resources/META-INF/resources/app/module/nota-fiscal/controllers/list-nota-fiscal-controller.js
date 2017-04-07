/*global app*/
'use strict';

app.controller('ListNotaFiscalController', ['$scope', '$rootScope', '$timeout', '$location', 'NotificationService', 'ApiService',
    function ($scope, $rootScope, $timeout, $location, NotificationService, ApiService) {
        var vm = this;
        vm.filter = {};
        
        vm.query = {
		    limit: 10,
		    page: 1,
		    order: "nroNota"
        };

        $scope.selected = [];
        $scope.notas = [];
        
        vm.limpar = function(){
        	vm.filter = {};
        }
        
        ApiService.get("rest/emitente/").then(
	        function (response) {
	        	vm.emitentes = response.data;
	        }
       );
        
        ApiService.get("rest/mercadoria/").then(
	        function (response) {
	        	vm.listMercadorias = response.data;
	        }
       );
        
       vm.success =  function(response) {
        	vm.notas = response.data.content;
        	vm.query.total = response.data.totalElements;
            if(vm.notas.length === 0){
                NotificationService.send('No invoice found, change search filters!');
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
        	
            $scope.promisse = ApiService.getPage("rest/nota-fiscal/filter", {
				        		page: vm.query.page -1, 
								size: vm.query.limit,
								sort: vm.query.order ,
						            idMercadoria : vm.filter.idMercadoria,
						            idEmitente : vm.filter.idEmitente
								});
            
            $scope.promisse.then(vm.success);
        };
        
        vm.edited = function(idNota){
        	 $location.path("nota-fiscal/" + idNota);
        };
        
        vm.remove = function(idNota){
        	ApiService.delete("rest/nota-fiscal/"+idNota)
        	.then(
    	        function (data) {
    	        	NotificationService.send('Invoice deleted successfully');
    	        	vm.pesquisar(vm.form);
    	        },
    	        function (error) {
                  NotificationService.send('Could not delete record, try later.');
    	          console.log(error);
    	        }
    	    );  
        };
    }
]);