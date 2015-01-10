
var invApp = angular.module('inventory', []);


function itemController($scope, $http,$filter) {
	$scope.code = '';
	$scope.name = '';
	$scope.price = '';
	$scope.labour = '';
	$scope.tax = '';
	$scope.discount = '';
	$scope.items = [];
	
	
	$scope.edit = true;
	$scope.error = false;
	$scope.complete = false;
	
	$scope.data =  {
			code : $scope.code,
			name : $scope.name ,
			price : $scope.price,
			labour : $scope.labour,
			tax : $scope.tax,
			discount : $scope.discount
	};
	
	
	$scope.getItems=function(){
		  $http.get('/Inv/svc/item')
		  .success(function(svcItems) {
			  $scope.items = svcItems;
		  });
	};
	

	

	$scope.editItem = function(index,code) {
	
		$scope.edit = true;
		
		
		if (index == 'new') {
			  $scope.complete = false;
			  $scope.data.code = '';
			  $scope.data.name = '';
			  $scope.data.price = '';
			  $scope.labour = '';
			  $scope.tax = '';
			  $scope.data.discount = '';
		
		} else {
			$scope.complete = true;
			$http.get('/Inv/svc/item/'+code)
			  .success(function(svcItem) {
				  $scope.data.code = svcItem.code;
				  $scope.data.name = svcItem.name;
				  $scope.data.price = svcItem.price;
				  $scope.data.labour = svcItem.labour;
				  $scope.data.tax = svcItem.tax;
				  $scope.data.discount = svcItem.discount;
				
			  });
			
		}
	};
	
	$scope.saveItem = function(code) {
	    $http.post('/Inv/svc/item/create',JSON.stringify($scope.data) ).
	        success(function(svcItem) {
	       	 var shItem = $filter('filter')($scope.items, function (item) {return item.code ===svcItem.code;})[0];
		        	
        	if(!shItem){
        		$scope.items.push($scope.data);
        		$scope.data = {};
        	}else{
        		 shItem.code = svcItem.code;
				 shItem.name = svcItem.name;
				 shItem.price = svcItem.price;
				 shItem.discount = svcItem.discount; 
				 shItem.labour = svcItem.labour;
				 shItem.tax = svcItem.tax;
        	}
        	
        	 $scope.complete = false;
	        	
	        });
	};

	$scope.$watch('data.price', function() {
		$scope.check();
	});
	$scope.$watch('data.discount', function() {
		$scope.check();
	});
	$scope.$watch('data.code', function() {
		$scope.check();
	});
	$scope.$watch('data.name', function() {
		$scope.check();
	});

	$scope.check = function() {
		
		if ($scope.data.hasOwnProperty('code') && $scope.data.code.length > 0) {
			$scope.error = false;
		}else{
			$scope.error = true;
		}
		
		if ($scope.data.hasOwnProperty('code')
				&& $scope.data.hasOwnProperty('discount')
				&& $scope.data.hasOwnProperty('price')
				&& $scope.data.hasOwnProperty('name')
				&& $scope.data.code.length > 0 && $scope.data.name.length > 0
				&& $scope.data.price > 0
				&& $scope.data.discount >= 0 ) {

			$scope.complete = true;
					
		}else{
			$scope.complete = false;
		}
	
	
	
	};

}