(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('SiteCreateUpdateController', SiteCreateUpdateController);

    SiteCreateUpdateController.$inject = ['$state', 'entity', 'Site'];

    function SiteCreateUpdateController($state, entity, Site) {
        var vm = this;
        vm.site = entity;

        vm.save = function () {
            if (vm.site.id !== null) {
                Site.update(vm.site, onSaveSuccess, onSaveError);
            } else {
                Site.save(vm.site, onSaveSuccess, onSaveError);
            }
        }

        function onSaveError() {
            $state.go('^');
        }

        function onSaveSuccess() {
            $state.go('site', null, {reload: true});
        }
    }
})();