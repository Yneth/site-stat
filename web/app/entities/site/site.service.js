(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('Site', Site);

    Site.$inject = ['$resource'];

    function Site($resource) {
        return $resource('api/user/site/:id', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {method: 'GET', isArray: false, params: {id: '@id'}},
            'update': {method: 'PUT', isArray: false, params: {id: '@id'}},
            'delete': {method: 'DELETE', isArray: false, params: {id: '@id'}}
        });
    }
})();