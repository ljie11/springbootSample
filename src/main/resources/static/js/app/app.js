var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8089/ProductCRUDExample',
    PRODUCT_SERVICE_API : 'http://localhost:8089/ProductCRUDExample/api/product/'

});

app.config(['$stateProvider', '$urlRouterProvider',
            function($stateProvider, $urlRouterProvider) {

                $stateProvider
                    .state('home', {
                        url: '/',
                        templateUrl: 'partials/list',
                        controller:'ProductController',
                        controllerAs:'ctrl',
                        resolve: {
                            products: function ($q, ProductService) {
                                console.log('Load all products');
                                var deferred = $q.defer();
                                ProductService.loadAllProducts().then(deferred.resolve, deferred.resolve);
                                return deferred.promise;
                            }
                        }
                    });
                $urlRouterProvider.otherwise('/');
            }]);

