var app = angular.module('gridBill', ['ngGrid']);
app.controller('gridCtrl', function($scope, $filter, $http, $q) {
    //$scope.billedItems = [{"code":1,"quantity":0,"total":100.0,"item":{"code":null,"name":'Cement',"price":350,"discount":null}},{"code":null,"quantity":0,"total":100.0,"item":{"code":null,"name":'Steel',"price":43,"discount":null}}];
    
    
    init($scope);
	$scope.bill = {
		total : $scope.billTotal,
		date : 1420699756855,
		customer : {
			phoneNo : 0,
			code : null,
			name : null,
			address : null
		},
		items : $scope.billedItems
	};

   
    //var basicCellTemplate = '<div class="ngCellText" ng-class="col.colIndex()" ng-click="editCell(row.entity, row.getProperty(col.field), col.field)"><span class="ui-disableSelection hover">{{row.getProperty(col.field)}}</span></div>';
  

    $scope.gridOptions = {
		data : 'billedItems',
		enableCellSelection : false,
		enableRowSelection : false,
		enableCellEdit : false,
		
		columnDefs : [
				{
					field : 'item.name',
					displayName : 'Name',
					width : '10%',
					enableCellEdit : false,
					cellTemplate : ' <select class="ui-input-text" novalidate="" ng-model="selectedItem" ng-options="item.name for item in items" ng-change="updatePrice(row.entity.item.code,selectedItem.code)"></select>'
				}, {
					field : 'item.price',
					displayName : 'Price',
					width : '20%',
					enableCellEdit : false
				}, {
					field : 'quantity',
					displayName : 'Quantity',
					width : '20%',
					enableCellEdit : false,
					cellTemplate: '<input type="text" name="input" class="ui-input-text" ng-model="selectedItem"  ng-change="updateTotal(row.entity,selectedItem)">'
				
				},{
					field : 'item.tax',
					displayName : 'Tax',
					width : '10%',
					enableCellEdit : false
				},{
					field : 'item.labour',
					displayName : 'Labour',
					width : '20%',
					enableCellEdit : false
				},{
					field : 'total',
					displayName : 'Total',
					width : '20%',
					enableCellEdit : false
				} ]
    };
    
    $scope.items = $http.get('/Inv/svc/item').success(
			function(data) {
				$scope.items = data;
			});
    
    $scope.updatePrice = function(index,code) {
       	$http.get('/Inv/svc/item/'+code).success(
    			function(data) {
    				var itemRow = $scope.billedItems[index];
					itemRow.item.price = data.price-data.discount;
					itemRow.item.tax = data.tax;
					itemRow.item.labour = data.labour;
					
    				itemRow.total = itemRow.item.tax + (itemRow.item.labour*itemRow.quantity) + (itemRow.quantity * itemRow.item.price);
    				$scope.billTotal =  $scope.calculateBillTotal();
    			});
	};
	
	
	$scope.updateTotal = function(entity,selectValue){
		
		$scope.billedItems[entity.item.code].quantity = selectValue;
		
		$scope.billedItems[entity.item.code].total = entity.item.tax + (entity.item.labour*selectValue) +  (selectValue * entity.item.price);
		$scope.billTotal =  $scope.calculateBillTotal();
		
    };
    
    
	$scope.calculateBillTotal = function(){
		var total = 0;
		for (var i = 0; i < $scope.billedItems.length; i++) {
		    total = total + $scope.billedItems[i].total;
		}
		return total;
		
	};
    
	$scope.$on('ngGridEventEndCellEdit', function (data) {
		if(data.targetScope.col.index == 2) {
			var entity = data.targetScope.row.entity;
			var item = entity.item;
			$scope.billedItems[item.code].total = entity.quantity * item.price;
	    }
	});
	
    $scope.addItem = function() {
		$scope.billedItems.push({
			item:{
				code:$scope.index++,
				name:null,
				price:0,
				discount:0,
				labour : 0,
				tax : 0,
			},
			quantity : 0,
			total : 0
			
		});
		$scope.complete = false;
	};
	
	
   $scope.saveBill = function() {
	   //$scope.bill = {"total":null,"date":1420699756855,"customer":{"phoneNo":0,"code":null,"name":null,"address":null},"items":[{"code":null,"quantity":0,"total":100.0,"item":{"code":null,"name":null,"price":null,"labour":null,"tax":null,"discount":null}},{"code":null,"quantity":0,"total":100.0,"item":{"code":null,"name":null,"price":null,"labour":null,"tax":null,"discount":null}}]};
	   $http.post('/Inv/svc/bill/create', $scope.bill).success(function(data) {
		   init($scope);
	   });
   };
   
   $scope.$watch('billTotal', function() {
	   var shItem = $filter('filter')($scope.billedItems, function (item) {return item.quantity ===0;})[0];
	   $scope.complete = $scope.billTotal > 1 && !shItem;
	});
   
  
});

function init($scope) {
	$scope.billedItems = [];
	$scope.billTotal = 0;
	$scope.index = 0;
	$scope.newbill = true;
	$scope.selectedItem = null;
	$scope.complete = false;
}