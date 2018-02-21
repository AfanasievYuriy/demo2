(function () {
    angular
        .module('app', [])
        .controller('AppController', AppController);

    function AppController($http) {
        var vm = this;
        vm.fileName = '';
        vm.canShowDataForOneFile = false;
        vm.hasErrorMessage = false;

        vm.hasError = hasError;
        vm.getTime = getTime;
        vm.getDataForOneFile = getDataForOneFile;

        init();

        function init() {
            $http.get('/result/all')
                .then(function (resp) {
                    vm.data = resp.data;
                });
        }
        
        function getDataForOneFile() {
            $http({
                method: 'GET',
                url: '/result',
                params: {date: vm.fileName}
            }).then(function (resp) {
                vm.singleFile = resp.data;
                vm.canShowDataForOneFile = true;
                vm.hasErrorMessage = false;
            }).catch(function (err) {
                vm.hasErrorMessage = true;
                vm.canShowDataForOneFile = false;
            });
        }
        
        function getTime(time) {
            return time.hour + ' h, ' + time.minute + ' m';
        }
        
        function hasError(error) {
            return error ? 'Yes' : 'No';
        }
    }
})();