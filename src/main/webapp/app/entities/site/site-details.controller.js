(function () {
    'use strict';

    angular
        .module('socialStatApp')
        .controller('SiteDetailsController', SiteDetailsController);

    SiteDetailsController.$inject = ['$state', '$scope', 'entity', 'SiteSession', 'pagingParams', 'paginationConstants', 'ParseLinks'];

    function SiteDetailsController($state, $scope, entity, SiteSession, pagingParams, paginationConstants, ParseLinks) {
        var vm = this;

        vm.from = {
            date: new Date(),
            datepickerOptions: {}
        };
        vm.to = {
            date: new Date(),
            datepickerOptions: {
                maxDate: new Date()
            }
        };

        vm.site = entity;

        vm.openCalendar = openCalendar;
        vm.loadAll = loadAll;
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;

        var onDateChanged = $scope.$watch(
            function () {
                return [vm.from.date, vm.to.date];
            },
            function () {
                vm.from.datepickerOptions.maxDate = vm.to.date;
                vm.to.datepickerOptions.minDate = vm.from.date;

                vm.loadAll();
            },
            true
        );

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
                var month = pad(+dateArray[0]);
                var day = pad(+dateArray[1]);
                return [year, month, day].join(separator);

                function pad(num) {
                    return num < 10 ? "0" + num : num;
                }
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