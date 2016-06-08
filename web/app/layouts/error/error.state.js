(function() {
    angular
        .module("socialStatApp")
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('error', {
            parent: 'app',
            url: '/error',

            views: {
                'content@': {
                    templateUrl: 'app/layouts/error/error.html'
                }
            }
        }).state('accessdenied', {
            parent: 'app',
            url: '/accessdenied',

            views: {
                'content@': {
                    templateUrl: 'app/layouts/error/accessdenied.html'
                }
            }
        });
    }
})();
