(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('site', {
                parent: 'entity',
                url: '/site?page&sort',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN'],
                    pageTitle: 'site-view.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/site/sites.html',
                        controller: 'SiteController',
                        controllerAs: 'vm'
                    }
                },
                params: {
                    page: {
                        value: '1',
                    },
                    sort: {
                        value: 'id,asc'
                    }
                },
                resolve: {
                    pagingParams: ['$stateParams', 'PaginationUtil',
                        function ($stateParams, PaginationUtil) {
                            return {
                                page: PaginationUtil.parsePage($stateParams.page),
                                sort: $stateParams.sort,
                                predicate: PaginationUtil.parsePredicate($stateParams.sort),
                                ascending: PaginationUtil.parseAscending($stateParams.sort)
                            };
                        }
                    ],
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader',
                        function ($translate, $translatePartialLoader) {
                            $translatePartialLoader.addPart('site-view');
                            return $translate.refresh();
                        }
                    ]
                }
            })
            .state('site.details', {
                parent: 'site',
                url: '/{id}/details',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/site/site-details.html',
                        controller: 'SiteDetailsController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    pagingParams: ['$stateParams', 'PaginationUtil',
                        function ($stateParams, PaginationUtil) {
                            return {
                                page: PaginationUtil.parsePage($stateParams.page),
                                sort: $stateParams.sort,
                                predicate: PaginationUtil.parsePredicate($stateParams.sort),
                                ascending: PaginationUtil.parseAscending($stateParams.sort)
                            };
                        }
                    ],
                    entity: ['Site', '$stateParams',
                        function (Site, $stateParams) {
                            // TODO: resolve with selected site, not queried one
                            return Site.get({siteId: $stateParams.id}).$promise;
                        }
                    ]
                }
            })
            .state('site.new', {
                parent: 'site',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/site/site-create-update.html',
                        controller: 'SiteCreateUpdateController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    entity: function () {
                        return {
                            id: null,
                            name: null,
                            url: null,
                            siteSessions: null
                        }
                    }
                }
            })
            .state('site.edit', {
                parent: 'site',
                url: '/site/{id}/edit',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/site/site-create-update.html',
                        controller: 'SiteCreateUpdateController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    entity: ['Site', '$stateParams',
                        function (Site, $stateParams) {
                            // TODO: resolve with selected site, not queried one
                            return Site.get({siteId: $stateParams.id});
                        }
                    ]
                }
            })
            .state('site.delete', {
                parent: 'site',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN']
                },
                onEnter: ['$state', '$stateParams', '$uibModal',
                    function ($state, $stateParams, $uibModal) {
                        $uibModal.open({
                            templateUrl: 'app/entities/site/site-delete-modal.html',
                            controller: 'SiteDeleteController',
                            controllerAs: 'vm',
                            resolve: {
                                entity: ['Site',
                                    function (Site) {
                                        // TODO: resolve with selected site, not queried one
                                        return Site.get({siteId: $stateParams.id});
                                    }
                                ]
                            }
                        }).result.then(
                            function () {
                                $state.go('site', null, {reload: true});
                            },
                            function () {
                                $state.go('^');
                            }
                        );
                    }
                ]
            });
    }
})();