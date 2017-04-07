/*global app*/

app.factory('NotificationService', ['StorageService', '$mdToast', 'ApiService' , function(StorageService, $mdToast, ApiService){
  'use strict';

  var service = {
	  showToast:showToast,
	  send: send,
	  error: error,
	  success: success,
	  deletadoComSucesso: deletadoComSucesso,
	  verifyEventsUnread: verifyEventsUnread,
	  verifyUnread: verifyUnread
  };
  
  return service;
  
  function verifyUnread()
  {
	  setInterval(verifyEventsUnread,10000); 
  };
  
  function verifyEventsUnread(){
	  ApiService.get("rest/events/unread").then(
		  function (response) {
	        	var unread = response.data;
	        	
	        	angular.forEach(unread, function(ev) {
	        		StorageService.setNotification(ev.message);
	        		var toast = $mdToast.simple()
			          .textContent(ev.message)
			          .action('READ')
			          .highlightAction(true)
			          .position('bottom right')
			          .hideDelay(3000);
		  
					  $mdToast.show(toast).then(function(response) {
					      if ( response == 'ok' ) {
					    	  ApiService.get("rest/events/read/"+response.id)
				            	.then(
				        	        function (response) {
				        	        }
				        	    );
					      	}
					  });
	        	});
	      }
	  );
  };
  
  function showToast(message, positionCustom) {
      $mdToast.show(
          $mdToast.simple()
          .textContent(message)
          .position('bottom right')
          .hideDelay(3000)
      );
  }

  function send(message){
    StorageService.setNotification(message);
    showToast(message);
  };

  	function error(){
        var message = "Ocorreu um erro! Se o erro persistir entre em contato com a gente!";
        StorageService.setNotification(message);
        showToast(message);
    };

   function success(){
        var message = "Registro cadastrado com sucesso!";
        StorageService.setNotification(message);
        showToast(message);
    };

    function deletadoComSucesso(){
        var message = "Registro(s) deletado(s) com sucesso!";
        StorageService.setNotification(message);
        showToast(message);
    };

}]);
