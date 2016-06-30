(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal'];

    function NavbarController($state, Auth, Principal) {
        var vm = this;

        vm.isCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        vm.logout = logout;
        vm.toggle = toggle;
        vm.collapse = collapse;
        vm.$state = $state;

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
