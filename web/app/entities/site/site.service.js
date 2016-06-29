(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('SiteService', SiteService);

    SiteService.$inject = ['$resource'];

    function SiteService($resource) {
        return $resource.get('api/user/network/:id', {}, {
            'query': {method: 'GET', isArray: true },
            'get': {method:'GET', isArray: false }
            // 'update': {method: 'PUT'},
            // 'delete': {method: 'DELETE'}
        });
    }
})();