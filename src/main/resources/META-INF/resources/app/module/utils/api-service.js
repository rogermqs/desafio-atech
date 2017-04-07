/*global app*/

app.service('ApiService', ['$q','$http', '$location','$localStorage',
    function($q, $http, $location, $localStorage){

        'use strict';

        var service = {};

        service.get = function(url){
            return $http.get(url,_headerToken()).then(_success, _error);
        };
        
        service.getPage = function(url, params){
        	if( params.sort )
        	{
        		var orderBy = params.sort.indexOf('-') === -1 ? params.sort+',ASC': params.sort.replace('-', '')+',DESC';
            	params.sort = orderBy;
        	}
        	return $http.get(url, {params: params }, _headerToken()).then(_success, _error);
        };
        service.post = function(url, data){
            return $http.post(url, data,_headerToken()).then(_success, _error);
        };
        service.put = function(url, data){
            return $http.put(url, data,_headerToken()).then(_success, _error);
        };
        service.delete = function(url){
            return $http.delete(url,_headerToken()).then(_success, _error);
        };
        service.deleteMultiple = function(listUrl){
            var promises = listUrl.map(function(url) {
                return $http.delete(url,_headerToken());
            });
            return $q.all(promises);            
        };        

        function _headerToken(){
            return { headers:{'x-access-token': ($localStorage.session || {}).token } };
        };

        function _success(res) {
            return res;
        };

        function _error(res) {
            if(res.status === 401){
                $location.path('/');
            }
            return { success: false, status: res.status, message: res.data.message };
        };

        return service;
    }]);
