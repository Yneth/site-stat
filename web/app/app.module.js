(function () {
    'use strict';

    angular.module('socialStatApp', [
        'ngStorage',
        'ngResource',
        'pascalprecht.translate',
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