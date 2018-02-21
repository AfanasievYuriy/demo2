(function () {
    angular
        .module('app', [])
        .controller('AppController', AppController);

    function AppController($http) {
        var vm = this;

        vm.hasError = hasError;
        vm.getTime = getTime;

        init();


        function init() {
            $http.get('/result/all')
                .then(function (resp) {
                    vm.data = resp.data;
                })
        }
        
        function getTime(time) {
            return time.hour + ' h, ' + time.minute + ' m';
        }
        
        function hasError(error) {
            return error ? 'Yes' : 'No';
        }
    }
})();