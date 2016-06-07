(function () {
    'use strict';
    angular.
        module('socialStatApp', [
        'ngStorage',
        'ngResources'
    ]).run(run);
    run.$inject = ['stateHandler', 'translationHandler'];

    function run(stateHandler, translationHandler) {
        stateHandler.initialize();
        translationHandler.initialize();
    }
})();