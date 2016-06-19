(function () {
    angular.module("splitmoneyapp", [
        'ngRoute',
        'ngAnimate',
        'ngMessages',
        'ngAria'
    ])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'view/home.html',
                    controller: 'HomeController'
                })
                .when('/calculate', {
                    templateUrl: 'view/dashboard.html',
                    controller: 'HomeController'
                })
                .otherwise({
                    redirectTo: '/'
                })
    }])
})();
