var app = angular.module('commonComponents');

app.component('wizardStep', {

	transclude : true,
	require : {
		stepCtrl : '^wizard'
	},
	bindings : {
		title : '@',
		
	},

	templateUrl : 'components/wizard-widget/template/wizard-step.html',

	controller : function($scope) {
		
		this.$onInit = function() {
			this.stepCtrl.addStep(this);
		};
	}

});
