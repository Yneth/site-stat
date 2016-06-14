(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .config(httpConfig);

    httpConfig.$inject = ['$urlRouterProvider', '$httpProvider', '$urlMatcherFactoryProvider'];

    function httpConfig($urlRouterProvider, $httpProvider, $urlMatcherFactoryProvider) {
        $urlRouterProvider.otherwise('/');
    }
})();