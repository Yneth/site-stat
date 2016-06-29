(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .factory('PaginationUtil', PaginationUtil);

    PaginationUtil.$inject = [];

    function PaginationUtil() {
        return {
            parsePage: parsePage,
            parsePredicate: parsePredicate,
            parseAscending: parseAscending
        };

        function parsePage(page) {
            return parseInt(page);
        }

        function parsePredicate (sort) {
            var sortArray = sort.split(',');
            if (sortArray.length > 1){
                sortArray.pop();
            }
            return sortArray.join(',');
        }

        function parseAscending(sort) {
            var sortArray = sort.split(',');
            if (sortArray.length > 1){
                return sort.split(',').slice(-1)[0] === 'asc';
            } else {
                // default to true if no sort defined
                return true;
            }
        }
    }

})();