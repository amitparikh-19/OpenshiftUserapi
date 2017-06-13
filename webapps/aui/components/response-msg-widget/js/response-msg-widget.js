/*
 * @author : Kopal Darbari
 * 
 * This component is used for showing success, error, warning and info messages on the UI
 * 
 * The controller has to pass following parameters :
 * @status  - SUCCESS, FAILURE, INFO, WARNING OR NONE
 * @message - the message body that needs to be shown
 * 
 * PS: setting status as none will not display any message, so by default it should be initialized as NONE.
 *  
 */
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