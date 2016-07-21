(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope', '$state', '$timeout', 'Auth'];

    function LoginController($rootScope, $state, $timeout, Auth) {
        var vm = this;

        vm.login = login;
        vm.cancel = cancel;
        vm.register = register;
        vm.requestResetPassword = requestResetPassword;
        vm.credentials = {};
        vm.username = null;
        vm.password = null;
        vm.rememberMe = true;
        vm.authenticationError = false;

        $timeout(function () {
            angular.element('#username').focus();
        });

        function login() {
            Auth.login({
                username: vm.username,
                password: vm.password,
                rememberMe: vm.rememberMe
            }).then(function () {
                vm.authenticationError = false;

                if ($state.current.name === 'register' || $state.current.name === 'activate' ||
                    $state.current.name === 'login' ||
                    $state.current.name === 'finishReset' || $state.current.name === 'requestReset') {
                    $state.go('site');
                }

                $rootScope.$broadcast('authenticationSuccess');

                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is succesful, go to stored previousState and clear previousState
                if (Auth.getPreviousState()) {
                    var previousState = Auth.previousState();
                    Auth.resetPreviousState();
                    $state.go(previousState.name, previousState.params);
                }
            }).catch(function () {
                vm.authenticationError = true;
            });
        }

        function cancel() {
            vm.credentials = {
                username: null,
                password: null,
                rememberMe: true
            };
            vm.authenticationError = false;
        }

        function register() {
            $state.go('register');
        }

        function requestResetPassword() {
            $state.go('requestReset');
        }
    }
})();
