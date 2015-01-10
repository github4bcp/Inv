
var invApp = angular.module('expense', []);


function expenseController($scope, $http,$filter) {
	$scope.text = '';
	$scope.amount = '';
	$scope.items = [];
	
	
	$scope.edit = true;
	$scope.error = false;
	$scope.complete = true;
	
	$scope.data =  {"debit":false,"text":"","date":null,"amount":0};
	
	
	$scope.getItems=function(){
		  $http.get('/Inv/svc/expense/')
		  .success(function(svcItems) {
			  $scope.items = svcItems;
		  });
	};
	

	

	$scope.editItem = function(index,code) {
	
		$scope.edit = true;
		
		
		if (index == 'new') {
			  $scope.complete = false;
			  $scope.data.text = '';
			  $scope.data.amount = '';
		
		} else {
			$scope.complete = true;
			$http.get('/Inv/svc/expense/'+index)
			  .success(function(svcItem) {
				  $scope.data.text = svcItem.text;
				  $scope.data.amount = svcItem.amount;
				
			  });
			
		}
	};
	
	$scope.saveItem = function(code) {
	    $http.post('/Inv/svc/expense/create',$scope.data).
	        success(function(svcItem) {
	       	 var shItem = $filter('filter')($scope.items, function (item) {return item.text ===svcItem.text;})[0];
		        	
        	if(!shItem){
        		$scope.items.push($scope.data);
        		$scope.data = {};
        	}else{
        		  shItem.text = svcItem.text;
        		  shItem.amount = svcItem.amount;
        	}
        	
        	 $scope.complete = false;
	        	
	        });
	};

	$scope.$watch('data.text', function() {
		$scope.check();
	});
	$scope.$watch('data.amount', function() {
		$scope.check();
	});
	

	$scope.check = function() {
		
	
	
	};

}