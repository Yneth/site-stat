(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .config(httpConfig);

    httpConfig.$inject = ['$urlRouterProvider', '$httpProvider'];

    function httpConfig($urlRouterProvider, $httpProvider) {
        $urlRouterProvider.otherwise('/');

        $httpProvider.interceptors.push('authExpiredInterceptor');
        $httpProvider.interceptors.push('authInterceptor');
    }
})();