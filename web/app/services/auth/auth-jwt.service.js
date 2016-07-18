(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = ['$http', '$q', '$localStorage', '$sessionStorage'];

    function AuthServerProvider($http, $q, $localStorage, $sessionStorage) {
        var service = {
            getToken: getToken,
            hasValidToken: hasValidToken,
            login: login,
            loginWithToken: loginWithToken,
            storeAuthenticationToken: storeAuthenticationToken,
            logout: logout
        };

        return service;

        function getToken() {
            return $localStorage.authenticationToken || $sessionStorage.authenticationToken;
        }

        function hasValidToken() {
            var token = this.getToken();
            return token && token.expires && token.expires > new Date().getTime();
        }

        function login(credentials) {
            var data = {
                'username': credentials.username,
                'password': credentials.password,
                'rememberMe': credentials.rememberMe
            };

            return $http.post('api/authenticate', data).success(authenticateSuccess);

            function authenticateSuccess(data, status, headers) {
                var token = headers('Authorization');
                if (angular.isDefined(token) && token.slice(0, 7) === 'Bearer ') {
                    var jwt = token.slice(7, token.length);
                    service.storeAuthenticationToken(jwt);
                    return jwt;
                }
            }
        }

        function loginWithToken(jwt, rememberMe) {
            var deferred = $q.defer();

            if (angular.isDefined(jwt)) {
                this.storeAuthenticationToken(jwt, rememberMe);
                deferred.resolve(jwt);
            } else {
                deferred.reject();
            }

            return deferred.promise();
        }

        function storeAuthenticationToken(jwt) {
            $localStorage.authenticationToken = jwt;
        }

        function logout() {
            delete $localStorage.authenticationToken;
        }
    }
})();