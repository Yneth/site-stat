(function () {
    'use strict';

    angular
        .module('socialStatApp', [
            'ngStorage',
            'ngResource',
            'pascalprecht.translate',
            'tmh.dynamicLocale',
            'ngCookies',
            'ui.bootstrap',
            'ui.router'
        ]).run(run);

    run.$inject = [
        'stateHandler', 'translationHandler', 'StateDebugService'
    ];

    function run(stateHandler, translationHandler) {
        stateHandler.initialize();
        translationHandler.initialize();
    }
})();