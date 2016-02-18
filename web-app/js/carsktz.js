/**
 * Created by amonetta on 16/02/16.
 */

var carsktz = angular.module('Carsktz',['restangular','smart-table']);
carsktz.config(
    function(RestangularProvider) {
        RestangularProvider.setBaseUrl('/carsktz/api');
    }
);

carsktz.controller("CarsCtrl",
    function ($scope, Restangular){

        var carsApi = Restangular.one("cars")


        $scope.itemsByPageOptions = [10,25,50,100, 500, 1000]

        $scope.itemsByPage=25;

        $scope.selectedItemsByPage = function(selectedItemsByPage) {
            $scope.itemsByPage = selectedItemsByPage
        }

        $scope.safeCollection = []

        $scope.allCars = []

        $scope.plainResponde = {}

        $scope.refreshCars = function() {
            carsApi.get({
                from: $scope.carFrom,
                to: $scope.carTo,
                make: $scope.carMake,
                model: $scope.carModel
            }).then(
                // Function on successful
                function (newCarsList) {
                    $scope.plainResponde = newCarsList.plain()
                    $scope.safeCollection = newCarsList.cars
                }, // Function on error
                function (errorResponse) {
                    alert("Error on refreshing cars: " + errorResponse.status)
                })
        }

        $scope.refreshCars();

    })