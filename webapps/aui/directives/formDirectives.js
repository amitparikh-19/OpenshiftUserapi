'use strict';
/* Directives */
var formDirective = angular.module('formDirective', []);  

formDirective.directive('validNumber', function() {
	  return {
	    require: '?ngModel',
	    link: function(scope, element, attrs, ngModelCtrl) {
	      if(!ngModelCtrl) {
	        return;
	      }

	      ngModelCtrl.$parsers.push(function(val) {
	        if (angular.isUndefined(val)) {
	            var val = '';
	        }
	        var clean = val.replace( /[^0-9]+/g, '');
	        if (val !== clean) {
	          ngModelCtrl.$setViewValue(clean);
	          ngModelCtrl.$render();
	        }
	        return clean;
	      });

	      element.bind('keypress', function(event) {
	        if(event.keyCode === 32) {
	          event.preventDefault();
	        }
	      });
	    }
	  };
	});

formDirective.directive('showErrorsImproved', [
    '$timeout', 'showErrorsImprovedConfig', '$interpolate', function($timeout, showErrorsImprovedConfig, $interpolate) {
        var getShowSuccess, getTrigger, linkFn;
        getTrigger = function(options) {
            var trigger;
            trigger = showErrorsImprovedConfig.trigger;
            if (options && (options.trigger != null)) {
                trigger = options.trigger;
            }
            return trigger;
        };
        getShowSuccess = function(options) {
            var showSuccess;
            showSuccess = showErrorsImprovedConfig.showSuccess;
            if (options && (options.showSuccess != null)) {
                showSuccess = options.showSuccess;
            }
            return showSuccess;
        };
        linkFn = function(scope, el, attrs, formCtrl) {
            var blurred, inputEl, inputName, inputNgEl, options, showSuccess, toggleClasses, trigger;
            blurred = false;
            options = scope.$eval(attrs.showErrorsImproved);
            showSuccess = getShowSuccess(options);
            trigger = getTrigger(options);


            inputEl = el[0].querySelector('input[name], select[name], textarea[name]');
            inputNgEl = angular.element(inputEl);
            inputName = $interpolate(inputNgEl.attr('name') || '')(scope);
            if (!inputName) {

                throw "show-errors element has no child input|select|textarea element with a 'name' attribute";
            }

            inputNgEl.bind(trigger, function() {
                blurred = true;
                return toggleClasses(formCtrl[inputName].$invalid);
            });
            scope.$watch(function() {
                return formCtrl[inputName] && formCtrl[inputName].$invalid;
            }, function(invalid) {
                if (!blurred) {
                    return;
                }
                return toggleClasses(invalid);
            });
            scope.$on('show-errors-check-validity', function() {
                return toggleClasses(formCtrl[inputName].$invalid);
            });
            scope.$on('show-errors-reset', function() {
                return $timeout(function() {
                    el.removeClass('has-error');
                    el.removeClass('has-success');
                    return blurred = false;
                }, 0, false);
            });
            return toggleClasses = function(invalid) {
                el.toggleClass('has-error', invalid);
                if(!invalid){
                    blurred = false;
                }

                if (showSuccess) {
                    return el.toggleClass('has-success', !invalid);
                }
            };
        };
        return {
            restrict: 'A',
            require: '^form',
            compile: function(elem, attrs) {
                if (attrs['showErrorsImproved'].indexOf('skipFormGroupCheck') === -1) {
                    if (!(elem.hasClass('form-group') || elem.hasClass('input-group'))) {
                        throw "showErrorsImproved element does not have the 'form-group' or 'input-group' class";
                    }
                }
                return linkFn;
            }
        };
    }

]);