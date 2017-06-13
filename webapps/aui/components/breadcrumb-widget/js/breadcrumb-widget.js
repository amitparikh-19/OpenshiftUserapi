/*
 * @author : Kopal Darbari
 * 
 * This component is used for showing beadcrumbs on the pages, it accepts $state name from the controller 
 * and splits its name to display crumbs on the UI
 * 
 * @state : $state name object
 */
var app = angular.module('commonComponents');

app.component('breadCrumb', {

	bindings : {
		state : '<'
	},

	templateUrl : 'components/breadcrumb-widget/template/breadcrumb-widget.html',

	controller : function($scope,$state) {
		
    	var self = this;
    	$scope.crumbs = null;
    	
    	this.$onInit = function() {
			console.log(self)
    		$scope.crumbs = self.state.name.split("."); 
    		console.log($scope.crumbs);
		};
		
		$scope.home=function(){			
			$state.go('mains.accounts');
		}
		
	}

});
