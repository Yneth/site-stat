(function() {
   'use strict';

    angular
        .module('socialStatApp')
        .controller(NavbarController, 'NavbarController');

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'LoginService'];

    function NavbarController($state, Auth, Principal, LoginService) {
        var vm = this;

        vm.isCollapsed = false;
        vm.isAuthenticated = Principal.isAuthenticated;

        vm.logout = logout;
        vm.login = login;
        vm.toggle = toggle;
        vm.collapse = collapse;
        vm.$state = $state;

        function toggle() {
            vm.isCollapsed = !vm.isCollapsed;
        }

        function collapse() {
            vm.isCollapsed = true;
        }

        function logout() {
            collapse();
            Auth.logout();
            $state.go('home');
        }

        function login() {
            collapse();
            LoginService.open();
        }
    }
});
