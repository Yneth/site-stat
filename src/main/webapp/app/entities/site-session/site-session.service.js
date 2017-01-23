(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('SiteSession', SiteSession);

    SiteSession.$inject = ['$resource'];

    function SiteSession($resource) {
        return $resource('/api/site/:siteId/session/:sessionId', {}, {
            'query': {method: 'GET', isArray: true, params: {siteId: '@siteId'}},
            'get': {method: 'GET', isArray: false, params: {siteId: '@siteId', sessionId: '@sessionId'}},
            'delete': {method: 'DELETE', isArray: false, params: {siteId: '@siteId', sessionId: '@sessionId'}}
        });
    }
})();