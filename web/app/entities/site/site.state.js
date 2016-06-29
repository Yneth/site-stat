(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('site', {
            parent: 'entity',
            url: '/site?page&sort',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'socialStatApp.site.home.title'
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
                ]
            }
        });
    }
})();