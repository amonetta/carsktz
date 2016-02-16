/**
 * Created by amonetta on 16/02/16.
 */

var hubbub = angular.module('Carsktz',['restangular']);
hubbub.config(
    function(RestangularProvider) {
        RestangularProvider.setBaseUrl('/carsktz/api');
    }
);

carsktz.controller("CarsCtrl",
    function ($scope, Restangular){

        var cars = Restangular.all("cars")

        $scope.allCars = []

        $scope.refreshCars = function() {
            carsApi.getList().then(function(newCarsList) {
                $scope.allPosts = newCarsList;
            }, function(errorResponse) {
                alert("Error on refreshing cars: " + errorResponse.status);
            });
        }

        $scope.refreshCars();

    })