(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('stateHandler', stateHandler);

    stateHandler.$inject = [
        '$rootScope', '$window', '$translate', 'Principal', 'Auth', 'LanguageService', 'translationHandler', 'VERSION'
    ];

    function stateHandler($rootScope, $window, $translate, Principal, Auth, translationHandler, LanguageService, VERSION) {
        return {
            initialize: initialize
        }

        function initialize() {
            $rootScope.VERSION = VERSION;

            var stateChangeStart = $rootScope.$on('$stateChangeStart',
                function (event, toState, toParams, fromState) {
                    $rootScope.toState = toState;
                    $rootScope.toStateParams = toStateParams;
                    $rootScope.fromState = fromState;

                    // Redirect to a state with an external URL (http://stackoverflow.com/a/30221248/1098564)
                    if (toState.external) {
                        event.preventDefault();
                        $window.open(toState.url, '_self');
                    }

                    if (Principal.isIdentityResolved()) {
                        Auth.authorize();
                    }

                    LanguageService.getCurrent().then(function (language) {
                        $translate.use(language);
                    });
                }
            );

            var stateChangeSuccess = $rootScope.$on('$stateChangeSuccess',
                function (event, toState, toParams, fromState) {
                    var titleKey = 'global.title';

                    // Set the page title key to the one configured in state or use default one
                    if (toState.data.pageTitle) {
                        titleKey = toState.data.pageTitle;
                    }
                    translationHandler.updateTitle(titleKey);
                }
            )

            $rootScope.$on('$destroy',
                function () {
                    if (angular.isDefined(stateChangeStart) && stateChangeStart !== null) {
                        stateChangeStart();
                    }
                    if (angular.isDefined(stateChangeSuccess) && stateChangeSuccess !== null) {
                        stateChangeSuccess();
                    }
                }
            );
        }
    }
})();