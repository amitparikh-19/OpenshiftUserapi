/*
 * @author : Kopal Darbari
 * 
 * This component is used for showing delete notification to user, it will just display 
 * a confirmation message with tid and send the notification back to 
 * controller to perform a delete http call
 *   
 * @tid : id of the object that is to be delete
 * @entity : entity's name that is top be deleted
 * @deleteCallback : controller fn to which notification is sent when users approves deletion.   
 */
var app = angular.module('commonComponents');

app.component('deleteWidget', {

    templateUrl: 'components/delete-widget/template/delete-widget.html',

    bindings : {
    	entity :  '<',
    	tid: '<',
    	deleteCallback: '&',
    },
    
    controller: function ($scope) {
    	
       	var self = this;	
    	
    	console.log("inside delete entity component");
   		$scope.entityName = self.entity;
   		$scope.targetId = self.tid;
    	
   		
   	   	$scope.deleteEntity = function()
   	   	{
   	 		self.deleteCallback({
				$model : {
					targetId : $scope.targetId
				}
   		}); 
   	   	}
    }
    
    });