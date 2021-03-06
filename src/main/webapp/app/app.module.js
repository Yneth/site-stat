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
            'ui.bootstrap.datetimepicker',
            'ui.router'
        ]).run(run);

    run.$inject = [
        'stateHandler', 'translationHandler', 'StateDebugService'
    ];

    function run(stateHandler, translationHandler, StateDebugService) {
        StateDebugService.active = true;
        stateHandler.initialize();
        translationHandler.initialize();
    }
})();