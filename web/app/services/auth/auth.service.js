(function () {
    'use strict';
    // TODO: implement
    angular.module('socialStatApp').factory('Auth', Auth);

    Auth.$inject = ['$rootScope', '$state', '$sessionStorage', '$q', 'Principal'];

    function Auth($rootScope, $state, $sessionStorage, $q, Principal) {
        var service = {
            createAccount: createAccount,
            activateAccount: activateAccount,
            updateAccount: updateAccount,
            resetPasswordInit: resetPasswordInit,
            resetPasswordFinish: resetPasswordFinish,
            authorize: authorize,
            login: login,
            logout: logout,
            loginWithToken: loginWithToken,
            getPreviousState: getPreviousState,
            storePreviousState: storePreviousState
        };

        return service;

        function createAccount() {

        }

        function activateAccount() {

        }

        function updateAccount() {

        }

        function resetPasswordInit() {

        }

        function resetPasswordFinish() {

        }

        function authorize() {

        }

        function login() {

        }

        function logout() {

        }

        function loginWithToken() {

        }

        function getPreviousState() {

        }

        function storePreviousState() {

        }
    }
})();
