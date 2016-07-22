(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('settings', {
                parent: 'account',
                url: '/settings',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/account/settings/settings.html',
                        controller: 'SettingsController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader',
                        function ($translate, $translatePartialLoader) {
                            $translatePartialLoader.addPart('settings');
                            return $translate.refresh();
                        }
                    ],
                    entity: ['Account',
                        function (Account) {
                            return Account.get().$promise;
                        }
                    ]
                }
            });
    }
})();