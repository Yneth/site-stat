(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register($resource) {
        return $resource('api/register', {}, {});
    }
})();