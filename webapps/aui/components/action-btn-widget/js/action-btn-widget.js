/*
 * @author : Kopal Darbari
 * 
 * This component controls the action buttons displayed on each page which are used to do 
 * operations such as - create, update, delete, details, search
 * 
 * It is controlled by showbuttons parameter which is provided by the controller i.e which buttons should be shown
 * 
 * @changeState is a callback fn which informs UI which operation has been requested by user to component.
 * @tid param stores the id of the record on which some operation is performed, it sent back to
 *  controller with above call back fn
 */

var app = angular.module('commonComponents',[]);

app.component('actionBtnWidget', {

    bindings : {
    	state :  '<',
    	tid: '<',
    	changeState: '&',
    	showbuttons: '='
    	
    },
    
    templateUrl: 'components/action-btn-widget/template/action-btn-widget.html',

   	controller: function ($scope) {
    	
   	var self = this;	
   		
   	$scope.changeState = function(state)
   	{
   		$scope.selectedState = state;
   		$scope.targetId = self.tid;
   		$scope.showbuttons = self.showbuttons;
   		
   		self.changeState({

				$model : {
					selectedState: $scope.selectedState,
					targetId : $scope.targetId
				}
   		});
   	}
    }
    
    });