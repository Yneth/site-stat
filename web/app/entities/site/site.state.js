(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .config(stateConfig);
    
    stateConfig.$inject = ['$stateProvider'];
    
    function stateConfig($stateProvider) {
        $stateProvider.state('site', {
            parent: 'entity',
            
        });
    }
})();