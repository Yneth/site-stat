(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('SettingsController', SettingsController);

    SettingsController.$inject = ['Auth', 'entity'];

    function SettingsController(Auth, entity) {
        var vm = this;

        vm.account = entity;
        vm.updateAccount = function () {
            console.log(vm.account);
            Auth.updateAccount(vm.account);
            // todo: add on finish notification bubble
        }
    }
})();