(function () {
    'use strict';
    // TODO: implement
    angular
        .module('socialStatApp')
        .factory('authInterceptor', authInterceptor)

    authInterceptor.$inject = [];

    function authInterceptor() {
        var service = {};
        return service;
    }
})();