/**
 * Created by amonetta on 16/02/16.
 */

var carsktz = angular.module('Carsktz',['restangular']);
carsktz.config(
    function(RestangularProvider) {
        RestangularProvider.setBaseUrl('/carsktz/api');
    }
);

carsktz.controller("CarsCtrl",
    function ($scope, Restangular){

        var carsApi = Restangular.all("cars")

        $scope.allCars = []

        //$scope.carFrom = 1940

        $scope.refreshCars = function() {
            if (angular.isUndefined($scope.carFrom) || angular.isNumber($scope.carFrom)) {
                carsApi.getList({from: $scope.carFrom}).then(
                    // Function on successful
                    function (newCarsList) {
                        $scope.allCars = newCarsList;
                    }, // Function on error
                    function (errorResponse) {
                        alert("Error on refreshing cars: " + errorResponse.status);
                    });
            } else
                alert("Initial year is n a number: " + $scope.carFrom);
        }

        $scope.refreshCars();

    })