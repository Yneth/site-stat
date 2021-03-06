(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('authExpiredInterceptor', authExpiredInterceptor)

    authExpiredInterceptor.$inject = ['$injector', '$q', '$localStorage', '$sessionStorage'];

    function authExpiredInterceptor($injector, $q, $localStorage, $sessionStorage) {
        var service = {
            responseError: responseError
        };
        return service;

        function responseError(response) {
            if (response.status === 401) {
                delete $localStorage.authenticationToken;
                delete $sessionStorage.authenticationToken;
                var Principal = $injector.get('Principal');
                if (Principal.isAuthenticated()) {
                    var Auth = $injector.get('Auth');
                    Auth.authorize(true);
                }
            }
            return $q.reject(response);
        }
    }
})();