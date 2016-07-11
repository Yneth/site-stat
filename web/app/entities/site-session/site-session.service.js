(function () {
    'use strict';
    
    angular
        .module('socialStatApp')
        .factory('SiteSession', SiteSession);
    
    SiteSession.$inject = ['$resource'];
    
    function SiteSession($resource) {
        return $resource('/api/user/site/:siteId');
    }
})();