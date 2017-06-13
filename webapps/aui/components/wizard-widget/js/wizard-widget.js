var app = angular.module('commonComponents');

app.component('wizard', {

	bindings : {
		title : '@',
		wizardType : '@',
		routeState : '<',
		
	},

	templateUrl : 'components/wizard-widget/template/wizard-widget.html',

	transclude : true,

	controller : function($scope) {

		var title = this.title;
		var wzType = this.wizardType;
		var routeState = this.routeState;

		var steps = this.steps = [];

		this.select = function(step) {

			if(step.pristine == false)
			{
				angular.forEach(steps, function(step) {
					step.selected = false;
				});
				step.selected = true;
				this.selectedStep = step
			}
			else
			{
				console.log("State change not allowed!")
			}
		};
		
		this.getSelectedStep = function()
		{
			return this.selectedStep;
		}
		this.getTotalSteps = function()
		{
			return this.steps;
		}

		this.addStep = function(step) {
			if (steps.length === 0) {
				step.pristine = false;
				this.select(step);
			} else {
				step.pristine = true;
			}

			steps.push(step);
		};

		this.nextStep = function() {
			this.changeState(this.getSelectedStep(), 1, null);
		};

		this.previousStep = function() {
			this.changeState(this.getSelectedStep(), -1, null);
		};

		this.gotoStep = function(target) {
			this.changeState(this.getSelectedStep(), 0, target);
		};

		this.changeState = function(step, direction, target) {
			var targetStep, tstep;
			var currStep = steps.indexOf(step);

			switch (direction) {
			case -1:
				tstep = --currStep;
				targetStep = steps[tstep];
				break;
			case 1:
				tstep = ++currStep;
				targetStep = steps[tstep];
				break;
			case 0:
				tstep = target;
				targetStep = steps[tstep];
				break;
			}

			if (currStep != -1 && targetStep) {
				angular.forEach(steps, function(step) {
					step.selected = false;
				});

				steps[tstep].selected = true;
				steps[tstep].pristine = false;
				this.selectedStep = steps[tstep];

			}
		}
	}

});
