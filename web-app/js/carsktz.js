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

        $scope.refreshCars = function() {
            carsApi.getList({
                from: $scope.carFrom,
                to: $scope.carTo,
                make: $scope.carMake,
                model: $scope.carModel
            }).then(
                // Function on successful
                function (newCarsList) {
                    $scope.allCars = newCarsList;
                }, // Function on error
                function (errorResponse) {
                    alert("Error on refreshing cars: " + errorResponse.status);
                });
        }

        $scope.refreshCars();

    })