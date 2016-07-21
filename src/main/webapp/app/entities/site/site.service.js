(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('Site', Site);

    Site.$inject = ['$resource'];

    function Site($resource) {
        return $resource('/api/user/site/:siteId', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {method: 'GET', isArray: false, params: {siteId: '@siteId'}},
            'update': {method: 'PUT', isArray: false, params: {siteId: '@siteId'}},
            'delete': {method: 'DELETE', isArray: false, params: {siteId: '@siteId'}}
        });
    }
})();