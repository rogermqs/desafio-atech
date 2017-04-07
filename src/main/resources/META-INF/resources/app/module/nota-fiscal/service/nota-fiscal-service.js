/*global app*/

app.service('NotaFiscalService', ['$http', '$q', function($http, $q){
  'use strict';
  
      var URI =  "http://localhost:8080/proimport-app/rest/royalty";

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
      
      service.save = function (object){
    	  var def = $q.defer();

          $http.post(URI, object)
            .success(function(data){
              def.resolve(data);
            })
            .error(function(error){
              def.reject(error);
            });

          return def.promise;
      };
      
      service.update = function (object){
    	  var def = $q.defer();

          $http.put(URI, object)
            .success(function(data){
              def.resolve(data);
            })
            .error(function(error){
              def.reject(error);
            });

          return def.promise;
      };

      service.remove = function(object) {
        var def = $q.defer();

        $http({
          method: 'DELETE',
          url: URI,
          headers: {
            'Content-Type': 'application/json'
          },
          data: object
        })
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