var app = angular.module('commonComponents',[]);

app.component('brandingWidget', {

	bindings: {
        align : '@'
    },

    templateUrl: 'components/branding-widget/template/branding-widget.html',

    controller: function ($scope) {
    	var self = this;
    	
    	$scope.align = self.align;
    }

});