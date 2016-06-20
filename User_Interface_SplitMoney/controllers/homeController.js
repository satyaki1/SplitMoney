(function () {
    angular.module('splitmoneyapp')
        .controller('HomeController', ['$scope', '$location', function ($scope, $location) {
            $scope.userForm = {};
            $scope.userArray = [];
            $scope.cloneArray = [];
            $scope.totalSpent = 0.0;

            $scope.addUsers = function () {
                var userJson = {};
                $scope.totalSpent = 0.0;

                userJson.name = $scope.userForm.name;
                userJson.amount = $scope.userForm.amount;
                userJson.pay = 0.0;
                userJson.receive = 0.0;
                $scope.cloneArray.push(userJson);

                //calculate total amount
                angular.forEach($scope.cloneArray, function (val, key) {
                    $scope.totalSpent = (parseFloat($scope.totalSpent)) + (parseFloat(val.amount));
                });

                //calculate other amounts
                angular.forEach($scope.cloneArray, function (val, key) {
                    var arrayLength = $scope.cloneArray.length;
                    var equalDivision = parseFloat($scope.totalSpent / arrayLength);
                    val.pay = (parseFloat(val.amount - equalDivision)) < 0 ? -(parseFloat(val.amount - equalDivision)) : 0;
                    val.receive = (parseFloat(val.amount - equalDivision)) > 0 ? (parseFloat(val.amount - equalDivision)) : 0;
                    //$scope.userArray.push(userJson);
                });
            };

            $scope.reset = function () {
                $scope.userForm = {};
                $scope.cloneArray = [];
                var userJson = {};
                $scope.totalSpent = 0.0;
            };

            $scope.goNextPage = function () {
                $location.url('/calculate');
            }
    }]);
})();
