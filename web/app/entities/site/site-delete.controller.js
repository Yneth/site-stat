(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('SiteDeleteController', SiteDeleteController);

    SiteDeleteController.$inject = ['$uibModalInstance', 'entity', 'Site', '$http'];

    function SiteDeleteController($uibModalInstance, entity, Site) {
        var vm = this;

        vm.site = entity;

        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        }
        vm.confirmDelete = function (id) {
            Site.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                }
            );
        }
    }
})();