/*global app*/

app.service('ProdutoService', ['$http', '$q', function($http, $q){
  'use strict';
  
      var URI =  "localhost:8080/proimport-app/rest/api/produto";

      var service = {};

      service.query = function() {
        var def = $q.defer();

        $http.get(URI)
          .success(function(data){
            def.resolve(data);
          })
          .error(function(error){
            def.reject(error);
          });

        return def.promise;
      };

      service.get = function (id) {
        var def = $q.defer();

        $http.get(URI + "/" + id)
          .success(function(data){
            def.resolve(data);
          })
          .error(function(error){
            def.reject(error);
          });

        return def.promise;
      };	
      
      service.search = function (data){
    	  var def = $q.defer();

    	  $http.get(URI + "/search/" + data)
          .success(function(data){
            def.resolve(data);
          })
          .error(function(error){
            def.reject(error);
          });

        return def.promise;
      };
      
     return service;
}]);