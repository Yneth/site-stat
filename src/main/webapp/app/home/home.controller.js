(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', '$state'];

    function HomeController($scope, Principal, $state) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.register = register;
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        getAccount();

        function register() {
            $state.go('register');
        }

        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
    }
})();
