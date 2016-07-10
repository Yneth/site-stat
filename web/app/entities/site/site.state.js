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
            .state('site.new', {
                parent: 'entity',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/site/site-create.html',
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
                parent: 'entity',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN']
                },
                views: {
                    templateUrl: 'app/entities/site/site-edit.html',
                    controller: 'SiteCreateUpdateController',
                    controllerAs: 'vm'
                },
                resolve: {
                    entity: ['Site',
                        function (Site, $stateParams) {
                            return Site.get({id: $stateParams.id});
                        }
                    ]
                }
            })
            .state('site.delete', {
                parent: 'entity',
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
                                        return Site.get({id: $stateParams.id});
                                    }
                                ]
                            }
                        }).result.then(
                            function () {
                                state.go('site-view', null, {reload: true});
                            },
                            function () {
                                state.go('^');
                            }
                        );
                    }
                ]
            });
    }
})();