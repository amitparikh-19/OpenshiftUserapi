var app = angular.module('commonComponents');

app.component('responseMsgWidget', {

    templateUrl: 'components/response-msg-widget/template/response-msg-widget.html',

    bindings : {
    	status :  '<',
    	message: '<',
    },
    
    controller: function ($scope) {
    	
       	var self = this;	
    	
    	console.log("inside response message component");
    	
    	// STATUS CAN BE SUCCESS, FAILURE, INFO, WARNING 
    	// OR NONE 
 
   	  this.$onChanges = function (changesObj) {
   		$scope.status = self.status;
   		$scope.message = self.message;
   	  }
   		
   		
    }
    
    });