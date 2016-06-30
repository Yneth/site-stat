(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('Site', Site);

    Site.$inject = ['$resource'];

    function Site($resource) {
        return $resource('api/user/network/:id', {}, {
            'query': {method: 'GET', isArray: true },
            'get': {method:'GET', isArray: false }
            // 'update': {method: 'PUT'},
            // 'delete': {method: 'DELETE'}
        });
    }
})();