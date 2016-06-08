(function() {
    angular
        .module("socialStatApp")
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('error', {
            parent: 'app',
            url: '/error',

            views: {
                'content@': {
                    templateUrl: 'app/layouts/error/error.html'
                }
            },

            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader',
                    function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('error');
                        return $translate.refresh();
                    }
                ]
            }
        }).state('accessdenied', {
            parent: 'app',
            url: '/accessdenied',

            views: {
                'content@': {
                    templateUrl: 'app/layouts/error/accessdenied.html'
                }
            },

            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader',
                    function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('error');
                        return $translate.refresh();
                    }
                ]
            }
        });
    }
})();
