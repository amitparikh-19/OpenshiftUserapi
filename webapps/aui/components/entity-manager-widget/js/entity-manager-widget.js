/*
 * @author : Kopal Darbari
 * 
 * This component is used for showing editable / non editable forms for the UI template that is transcluded into this  
 * <entity-manager>...</entity-manager>
 * 
 * its takes one paramter :
 * @operation : possible values are :
 * - create 
 * - update
 * - views -> create-view, update-view, details-view, search
 * 
 */
var app = angular.module('commonComponents');

app.component('entityManager', {

	bindings : {
		operation : '@',
	},

	template : '  <div></div>',

	transclude : true,

	controller : function($scope, $element, $transclude) {
		
	 	var self = this;
    	this.$onInit = function() {
    		
    		/* Can be only :
    		 * - create 
    		 * - update
    		 * - views : create-view, update-view, details-view, search
    		 */
    		
    		if(self.operation == 'create' || self.operation == 'create-view' || self.operation == 'details-view' || self.operation == 'update-view' || self.operation == 'update') 
    			console.log("> legal operation, nothing to worry !")
    		else
    			console.log("> illegal operation")
    		
    		 $transclude(function (transEl, transScope) {
 	            transScope.operation = self.operation;
 	            $element.append(transEl);
 	      });
		};
	}

});
