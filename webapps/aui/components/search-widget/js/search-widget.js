var app = angular.module('commonComponents');

app.component('searchWidget', {

    templateUrl: 'components/search-widget/template/search-widget.html',

    bindings : {
    	src : '@',
    	searchCallback: '&'
    },
    
	transclude : true,

    controller: function ($scope) {
    	
       	var self = this;	
    	$scope.operation = 'search';
    	console.log("inside search entity component");
    }
    
    });