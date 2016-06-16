(function () {
    'use strict';

    angular.module('socialStatApp').factory('Auth', Auth);

    Auth.$inject = [
        '$rootScope', '$state', '$sessionStorage', '$translate',
        '$q', 'Principal', 'AuthServerProvider',
        'Activate', 'Account', 'Register', 'LoginService'];

    function Auth($rootScope, $state, $sessionStorage, $translate,
                  $q, Principal, AuthServerProvider,
                  Activate, Account, Register, LoginService) {
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
            storePreviousState: storePreviousState,
            resetPreviousState: resetPreviousState
        };

        return service;

        function createAccount(account, callback) {
            var cb = callback || angular.noop;

            return Register.save(account,
                function () {
                    return cb(account);
                },
                function (error) {
                    this.logout();
                    return cb(error);
                }.bind(this)
            ).$promise;
        }

        function activateAccount(key, callback) {
            var cb = callback || angular.noop;

            return Activate.get(key,
                function (response) {
                    return cb(response);
                },
                function (error) {
                    return cb(error);
                }.bind(this)
            ).$promise;
        }

        function updateAccount(account, callback) {
            var cb = callback || angular.noop;

            return Account.save(account,
                function () {
                    return cb(account);
                },
                function (error) {
                    return cb(error);
                }.bind(this)
            ).$promise;
        }

        function resetPasswordInit() {
            // TODO: implement reset password
        }

        function resetPasswordFinish() {

        }

        function authorize(forced) {
            var auth = Principal.identity(forced).then(authThen);
            return auth;

            function authThen() {
                var isAuthenticated = Principal.isAuthenticated();

                // an authenticated user can't access to login and register pages
                if (isAuthenticated && $rootScope.toState.parent === 'account' && ($rootScope.toState.name === 'login' || $rootScope.toState.name === 'register' || $rootScope.toState.name === 'social-auth')) {
                    $state.go('home');
                }

                // recover and clear previousState after external login redirect (e.g. oauth2)
                if (isAuthenticated && !$rootScope.fromState.name && getPreviousState()) {
                    var previousState = getPreviousState();
                    resetPreviousState();
                    $state.go(previousState.name, previousState.params);
                }

                if ($rootScope.toState.data.authorities
                    && $rootScope.toState.data.authorities.length > 0
                    && !Principal.hasAnyAuthority($rootScope.toState.data.authorities)) {

                    if (isAuthenticated) {
                        // user is signed in but not authorized for desired state
                        $state.go('accessdenied');
                    }
                    else {
                        // user is not authenticated. stow the state they wanted before you
                        // send them to the login service, so you can return them when you're done
                        storePreviousState($rootScope.toState.name, $rootScope.toStateParams);

                        // now, send them to the signin state so they can log in
                        $state.go('accessdenied').then(function () {
                            LoginService.open();
                        });
                    }
                }
            }
        }

        function login(credentials, callback) {
            var cb = callback || angular.noop;

            return AuthServerProvider.login(credentials)
                .then(loginThen)
                .catch(function (err) {
                    this.logout();
                    return cb(err);
                }.bind(this));

            function loginThen(data) {
                Principal.identity(true).then(function (account) {
                    if (account !== null) {
                        $translate.use(account.langKey).then(function () {
                            $translate.refresh();
                        })
                    }
                });
                return cb();
            }
        }

        function loginWithToken(jwt, rememberMe) {
            AuthServerProvider.loginWithToken(jwt, rememberMe);
        }

        function logout() {
            AuthServerProvider.logout();
            Principal.authenticate(null);
        }

        function getPreviousState() {
            var previousState = $sessionStorage.previousState;
            return previousState;
        }

        function storePreviousState(previousStateName, previousStateParams) {
            var previousState = {"name": previousStateName, "params": previousStateParams};
            $sessionStorage.previousState = previousState;
        }

        function resetPreviousState() {
            delete $sessionStorage.previousState;
        }
    }
})();
