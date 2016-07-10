(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('SiteCreateDialogController', SiteCreateUpdateController);

    SiteCreateUpdateController.$inject = ['$uibModalInstance', 'entity', 'Site'];

    function SiteCreateUpdateController($uibModalInstance, entity, Site) {
        var vm = this;
        vm.site = entity;
        vm.save = function () {
            Site.save(entity);
        }
    }
})();