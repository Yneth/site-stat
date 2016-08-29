(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('SiteDetailsController', SiteDetailsController);

    SiteDetailsController.$inject = ['$state', 'entity', 'SiteSession', 'pagingParams', 'paginationConstants', 'ParseLinks'];

    function SiteDetailsController($state, entity, SiteSession, pagingParams, paginationConstants, ParseLinks) {
        var vm = this;

        vm.from = {
            date: new Date()
        };
        vm.to = {
            date: new Date()
        };

        vm.site = entity;

        vm.openCalendar = openCalendar;
        vm.loadAll = loadAll;
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.loadAll();

        function openCalendar(e, name) {
            vm[name].open = true;
        }

        function loadAll() {
            SiteSession.query({
                    siteId: vm.site.id,
                    from: toDateString(vm.from.date, "-"),
                    to: toDateString(vm.to.date, "-"),
                    page: pagingParams.page - 1,
                    size: paginationConstants.itemsPerPage,
                    sort: sort()
                },
                onSuccess, onError
            );

            function toDateString(date, separator) {
                var dateArray = date.toLocaleString().split(",")[0].split("/");
                var year = dateArray[2];
                var month = +dateArray[0] < 10 ? "0" + dateArray[0] : dateArray[0];
                var day = dateArray[1];
                return [year, month, day].join(separator);
            }

            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.site.sessions = data;
                vm.page = pagingParams.page;
            }

            function onError(error) {
            }
        }

        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')
            });
        }
    }
})();