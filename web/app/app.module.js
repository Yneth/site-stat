(function () {
    'use strict';

    angular.module('socialStatApp', [
        'ngStorage',
        'ngResource',
        'ui.bootstrap',
        'ui.router'
    ]).run(run);
    
    run.$inject = [
        'stateHandler', 'translationHandler'
    ];

    function run(stateHandler, translationHandler) {
        stateHandler.initialize();
        translationHandler.initialize();
    }
})();