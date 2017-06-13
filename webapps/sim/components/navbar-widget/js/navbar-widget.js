var app = angular.module('commonComponents');

app.component('navbarWidget', {

    templateUrl: 'components/navbar-widget/template/navbar-widget.html',

    bindings : {
    	entity :  '<',
    	tid: '<',
    	methodCallback: '&',
    },
    
    controller: function ($scope) {
    	var self=this;   	
    	$(function () {
    	    $('.navbar-toggler').on('click', function(event) {
    			event.preventDefault();
    			$(this).closest('.navbar-minimal').toggleClass('open');
    		})
    	});
    	
   	   	$scope.methodCallback = function(methodName)
   	   	{
   	 		self.methodCallback({
				$model : {
					'methodName' : methodName
				}
   		}); 
   	   	}

    	$scope.navItems = [
	               {
	                   id: 1,
	                   navItem: 'Clean Up',
	                   glyph: 'glyphicon-trash'
	               },
	               {
	                   id: 1,
	                   navItem: 'Init Topup',
	                   glyph: 'glyphicon-certificate'
	               },
	               {
	                   id: 3,
	                   navItem: 'Init DM',
	                   glyph: 'glyphicon-certificate'
	               },
	               {
	                   id: 4,
	                   navItem: 'Topup OPs',
	                   glyph: 'glyphicon-phone',
	                   state: 'mains.topupop.fundin'
	               },
	               {
	                   id: 5,
	                   navItem: 'DM OPs',
	                   glyph: 'glyphicon-inbox',
	                   state: 'mains.dmop.fundin'
	               }
	           ];
    	
    }
    });
