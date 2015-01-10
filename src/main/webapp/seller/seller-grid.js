var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
	editableOptions.theme = 'bs3';
});

app.run(function(editableOptions, editableThemes) {
	editableThemes.bs3.inputClass = 'input-sm';
	editableThemes.bs3.buttonsClass = 'btn-sm';
	editableOptions.theme = 'bs3';
});

app.controller('EditableTableCtrl', function($scope, $filter, $http, $q) {
	$scope.billedItems = [{"code":1,"quantity":0,"total":100.0,"item":{"code":null,"name":'Cement',"price":350,"discount":null}},{"code":null,"quantity":0,"total":100.0,"item":{"code":null,"name":'Steel',"price":43,"discount":null}}];
	
	//$scope.billedItems = [];
	
	
	$scope.editmode= false;
	
	
	$scope.itemGroups = [];
	
	
	$scope.onItemCodeChange = function(selected) {
		
		alert($scope.itemGroups[selected.value].price);
		return $scope.itemGroups[selected.value].code;
		
	};
	
	$scope.itemCodeExists = function(index) {
		if($scope.billedItems.length>0){
			//alert($scope.billedItems[index].code!=null && $scope.billedItems[index].item.code!=null);
			return $scope.billedItems[index].code!=null && $scope.billedItems[index].item.code!=null;
			//return true;
		
		}
		return true;
	};
	
	
	$scope.loadItems = function() {
		return $scope.itemGroups.length ? null : $http.get('http://localhost:8010/Inv/svc/item').success(
				function(data) {
					$scope.itemGroups = data;
				});
	};

	$scope.showItems = function(billedItem) {
		if(billedItem){
			var item = billedItem.item;
			if (item.name && $scope.itemGroups.length) {
				var selected = $filter('filter')($scope.itemGroups, {
					name : item.name
				});
				
				return selected.length ? selected[0].name : billedItem.name;
			} else {
				return item.name || billedItem.name;
			}
		}
		
	};

	$scope.checkName = function(data, id) {
		if (id === 2 && data !== 'awesome') {
			return "Username 2 should be `awesome`";
		}
	};

	// filter items to show
	$scope.filterUser = function(user) {
		return user.isDeleted !== true;
	};

	// mark user as deleted
	$scope.deleteItem = function(index) {
		$scope.billedItems.splice(index, 1);
	};

	// add user
	$scope.addItem = function() {
		$scope.billedItems.push({
			item:{
				code:null,
				name:null,
				price:null,
				discount:null
			}
			
		});
	};

	// cancel all changes
	$scope.cancel = function() {
		for (var i = $scope.billedItems.length; i--;) {
			var user = $scope.billedItems[i];
			// undelete
			if (user.isDeleted) {
				delete user.isDeleted;
			}
			// remove new
			if (user.isNew) {
				$scope.billedItems.splice(i, 1);
			}
		}
		;
	};

	// save edits
	$scope.saveTable = function() {
		var results = [];
		for (var i = $scope.billedItems.length; i--;) {
			var user = $scope.billedItems[i];
			// actually delete user
			if (user.isDeleted) {
				$scope.billedItems.splice(i, 1);
			}
			// mark as not new
			if (user.isNew) {
				user.isNew = false;
			}

			// send on server
			//results.push($http.post('/saveUser', user));
		}

		return $q.all(results);
	};
});

