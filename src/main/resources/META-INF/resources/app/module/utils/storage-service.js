/*global app*/

app.service('StorageService', ['$localStorage', function($localStorage){
  'use strict';

  var service = {};

  service.setSession = function(token){
    $localStorage.session = {token: token};
  };

  service.setUser = function(user){
    $localStorage.user = user;
  };

  service.updateNameUser = function(name){
    if($localStorage.user){
      $localStorage.user.name = name;
    }
  };

  service.isLogged = function(){
    if($localStorage.user){
      return true;
    }else{
      return false;
    }
  };

  service.setNotification = function(message){
    if($localStorage.notification === undefined){
      $localStorage.notification = [];
    }
    $localStorage.notification.push({text: message});
  };

  service.getNotification = function(){
    if($localStorage.notification === undefined){
      return [{text: "Nenhuma notificação!"}];
    }
    return $localStorage.notification;
  };

  service.clearNotification = function(){
    $localStorage.notification = undefined;
  };

  service.clearAll = function(){
    $localStorage.session = undefined;
    $localStorage.user = undefined;
    $localStorage.notification = undefined;
  };

  return service;
}]);
