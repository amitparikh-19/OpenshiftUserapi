var app = angular.module('commonComponents');

app.component('wizardActionBtns', {
	bindings : {
		reset : '&',
		cancel : '&',
		save: '&'
	},
	
	require : {
		stepCtrl : '^wizard'
	},

	templateUrl : 'components/wizard-widget/template/wizard-btn-group.html',

	controller : function($scope) {
	 	var btn = this;
		this.$onInit = function() {
			$scope.stepInfo();
			
		}
		$scope.stepInfo=function(){
			$scope.steps=this.$ctrl.stepCtrl.getTotalSteps();
			$scope.step=this.$ctrl.stepCtrl.getSelectedStep();
			$scope.index=$scope.steps.indexOf($scope.step)+1;
			$scope.stepLength=$scope.steps.length;
			
			
		}
		$scope.next = function()
		{
			
			this.$ctrl.stepCtrl.nextStep();
			
			$scope.stepInfo();
		}
		
		$scope.previous = function()
		{
			this.$ctrl.stepCtrl.previousStep();
			$scope.stepInfo();
		}
		$scope.save=function(){
			
			this.$ctrl.stepCtrl.nextStep(); 
			$scope.stepInfo();
			btn.save({
				  
			});
		}
		$scope.cancel=function(){
			
			btn.cancel({
				
			});
		}
		$scope.reset=function(){
			$scope.stepInfo();
			btn.reset({

			});
		}
		
	}

});
  
