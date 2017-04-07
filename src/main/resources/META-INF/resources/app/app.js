/*global angular*/
var app = angular.module('atechppApp', ['ngMaterial', 'md.data.table', 'ngRoute', 'ngStorage', 'angular-loading-bar']);

app.config(['$locationProvider','$routeProvider', '$mdThemingProvider', '$mdDateLocaleProvider', 'cfpLoadingBarProvider',
function ($locationProvider, $routeProvider, $mdThemingProvider, $mdDateLocaleProvider, cfpLoadingBarProvider) {

cfpLoadingBarProvider.spinnerTemplate = '<div><span class="fa fa-spinner loadCustom">Carregando...</div>';

  $locationProvider.hashPrefix('!');
/*
app.config(['$routeProvider','$httpProvider','$mdThemingProvider',
function ($routeProvider, $httpProvider, $mdThemingProvider) {
*/

// pt-BR localization.
$mdDateLocaleProvider.months = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
$mdDateLocaleProvider.shortMonths = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
$mdDateLocaleProvider.days = ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'];
$mdDateLocaleProvider.shortDays = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];

$mdDateLocaleProvider.formatDate = function(date) {
       return moment(date).format('DD-MM-YYYY');
    };

//red, pink, purple, deep-purple, indigo, blue, light-blue, cyan, teal, green, light-green, lime, yellow, amber, orange, deep-orange, brown, grey, blue-grey
  $mdThemingProvider.theme('default')
    .primaryPalette('blue', {
      'default': '800', // by default use shade 400 from the pink palette for primary intentions
      'hue-1': '100', // use shade 100 for the <code>md-hue-1</code> class
      'hue-2': '300', // use shade 600 for the <code>md-hue-2</code> class
      'hue-3': 'A100' // use shade A100 for the <code>md-hue-3</code> class
    })
    // If you specify less than all of the keys, it will inherit from the
    // default shades
    .accentPalette('blue', {
      'default': '600' // use shade 200 for default, and keep all other shades the same
    });

  $routeProvider
        .when('/index', {
            templateUrl: 'app/module/home/index.html',
            controller: 'HomeController',
            controllerAs: 'vm'
        })
        .when('/mercadoria', {
            templateUrl: 'app/module/mercadoria/form-mercadoria.html',
            controller: 'FormMercadoriaController',
            controllerAs: 'vm'
        })
        .when('/mercadoria/:id', {
        	templateUrl: 'app/module/mercadoria/form-mercadoria.html',
            controller: 'FormMercadoriaController',
            controllerAs: 'vm'
        })
        .when('/list-mercadoria', {
            templateUrl: 'app/module/mercadoria/list-mercadoria.html',
            controller: 'ListMercadoriaController',
            controllerAs: 'vm'
        })
        .when('/nota-fiscal', {
            templateUrl: 'app/module/nota-fiscal/form-nota-fiscal.html',
            controller: 'FormNotaFiscalController',
            controllerAs: 'vm'
        })
        .when('/nota-fiscal/:id', {
            templateUrl: 'app/module/nota-fiscal/form-nota-fiscal.html',
            controller: 'FormNotaFiscalController',
            controllerAs: 'vm'
        })
        .when('/list-nota-fiscal', {
            templateUrl: 'app/module/nota-fiscal/list-nota-fiscal.html',
            controller: 'ListNotaFiscalController',
            controllerAs: 'vm'
        })
        .otherwise ({ redirectTo: '/index' });
}]);
