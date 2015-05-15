var app = angular.module('tallyApp', ['ngGrid']);
app.controller('tallyCtrl', function($scope) {
    $scope.tallySelections = [];
    $scope.tallyData = [{name: "Moroni", age: 50},
                     {name: "Tiancum", age: 43},
                     {name: "Jacob", age: 27},
                     {name: "Nephi", age: 29},
                     {name: "Enos", age: 34}];
    $scope.gridOptions = { 
      data: 'tallyData',
      selectedItems: $scope.tallySelections,
      multiSelect: false
    };
});