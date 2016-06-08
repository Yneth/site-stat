(function() {
    'use strict';

    angular.
        module('socialStatApp').
        factory('Auth', Auth);

    Auth.$inject = ['$rootScope', '$state', '$sessionStorage', '$q', 'Principal' ];

    function Auth($rootScope, $state, $sessionStorage, $q, Principal) {
        return {
            authorize: authorize
        }

        function authorize() {

        }
    }
})();
