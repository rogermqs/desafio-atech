/*global app*/
'use strict';

app.controller('FormNotaFiscalController', [ '$scope', '$rootScope', 'NotificationService', 'ApiService',  '$route', '$routeParams',
    function ($scope, $rootScope, NotificationService, ApiService,  $route, $routeParams) {
        var vm = this;
        vm.edited  = false;
        vm.notaFiscal = {};
        vm.notaFiscal.valorTotal = 0;
        vm.notaFiscal.dataEmissao = new Date();
		vm.notaFiscal.mercadorias = [];
		vm.listaMercadorias = [];
        if( $routeParams.id )
        {
        	ApiService.get("rest/nota-fiscal/"+$routeParams.id)
        	.then(
    	        function (response) {
    	          vm.notaFiscal= response.data;
    	        },
    	        function (error) {
    	          console.log(error.message);
    	        }
    	    );
        	vm.edited  = true;
        }
        
        ApiService.get("rest/emitente/").then(
	        function (response) {
	        	vm.emitentes = response.data;
	        }
        );

		function encontrouMercadoria(){
			if(vm.listaMercadorias.length === 0){
				NotificationService.send('No products found, change the filter!');
			}
		}

        vm.buscarMercadorias = function(){

        	if( vm.pesquisa.mercadoria !== '' )
        	{
        		ApiService.get("rest/mercadoria/search/"+vm.pesquisa.mercadoria)
            	.then(
        	        function (response) {
        	        	vm.listaMercadorias = response.data;
						encontrouMercadoria();
        	        },
        	        function (error) {
        	          console.log(error.message);
        	        }
        	    );
        	}
        };

        vm.removerMercadoria = function(mercadoria){
        	vm.notaFiscal.mercadorias = vm.notaFiscal.mercadorias.filter(function(x){
                return x.codigo !== mercadoria.codigo;
            });
        	mercadoria.use = false;
        	vm.notaFiscal.valorTotal -=  mercadoria.preco;
        };

        vm.addMercadoria = function(mercadoria){
        	if( !vm.notaFiscal.mercadorias )
        	{
        		vm.notaFiscal.mercadorias = [];
        	}

            var existe = vm.notaFiscal.mercadorias.filter(function(x){
                return x.id === mercadoria.id;
            });
            if(existe.length === 0){
            	mercadoria.use = true;
            	vm.notaFiscal.mercadorias.push(mercadoria);
            	vm.notaFiscal.valorTotal +=  mercadoria.preco;
            }else{
                NotificationService.send('Product ('+mercadoria.nome+') already added!');
            }

        };

        vm.salvar = function(){
			if(!$scope.form.$valid){
				NotificationService.send('Required fields are not filled!');
				return false;
			}
			if(vm.notaFiscal.mercadorias.length === 0){
				NotificationService.send('It is necessary to add some products!');
				return false;
			}

        	if( vm.edited )
        	{
        		ApiService.put("rest/nota-fiscal",vm.notaFiscal)
            	.then(
        	        function (response) {
        	        	if( response.success === false )
        	        	{
        	        		NotificationService.send(response.message);
        	        	}
        	        	else
        	        	{
              	          	NotificationService.send('Invoice send to be change');
        	        	}
        	        }
        	    );
        	}
        	else
        	{
        		ApiService.post("rest/nota-fiscal",vm.notaFiscal)
            	.then(
        	        function (response) {
        	        	if( response.success === false )
        	        	{
        	        		NotificationService.send(response.message);
        	        	}
        	        	else
        	        	{
        	        		vm.clear();
              	          	NotificationService.send('Invoice send to be register');
        	        	}
        	        }
        	    );
        	}
        }

        vm.clear = function()
        {
        	vm.notaFiscal = {};
        	vm.notaFiscal.mercadorias = [];
    		vm.listaMercadorias = [];
    		vm.edited = false;
    		vm.pesquisa.mercadoria = '';
        }
	}
]);
