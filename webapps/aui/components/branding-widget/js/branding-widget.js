/*
 * @author : Kopal Darbari
 * 
 * This component is used for showing branding logo 
 * @align : its alignment can be controlled by sending a parameter via controller - left / right
 */
var app = angular.module('commonComponents');

app.component('brandingWidget', {

	bindings: {
        align : '@'
    },

    templateUrl: 'components/branding-widget/template/branding-widget.html',

    controller: function ($scope,$state) {
    	var self = this;
    	
    	$scope.align = self.align;
    	
    	$scope.home=function(){			
    		$state.go('mains.accounts');
    	}
    }

});