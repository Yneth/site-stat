(function() {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'LoginService'];

    function NavbarController ($state, Auth, Principal, LoginService) {
        var vm = this;

        vm.isCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        vm.login = login;
        vm.logout = logout;
        vm.toggle = toggle;
        vm.collapse = collapse;
        vm.$state = $state;

        function login() {
            collapse();
            LoginService.open();
        }

        function logout() {
            collapse();
            Auth.logout();
            $state.go('home');
        }

        function toggle() {
            vm.isCollapsed = !vm.isCollapsed;
        }

        function collapse() {
            vm.isCollapsed = true;
        }
    }
})();
