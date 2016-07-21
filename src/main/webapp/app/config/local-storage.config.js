(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .config(localStorageConfig);

    localStorageConfig.$injet = ['$localStorageProvider'];

    function localStorageConfig($localStorageProvider) {
        $localStorageProvider.setKeyPrefix('s-stat-');
    }
})();