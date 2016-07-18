(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('authInterceptor', authInterceptor)

    authInterceptor.$inject = ['$localStorage', '$sessionStorage'];

    function authInterceptor($localStorage, $sessionStorage) {
        var service = {
            request: request
        };
        return service;

        function request(config) {
            config.headers = config.headers || {};

            var token = $localStorage.authenticationToken || $sessionStorage.authenticationToken;
            
            if (token) {
                config.headers.Authorization = 'Bearer ' + token;
            }

            return config;
        }
    }
})();