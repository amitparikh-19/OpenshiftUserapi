/*
 * @author : Kopal Darbari
 * 
 * This component is used for showing Login form which accepts user-name / password from the user and on submit 
 * it sends a callback to the controller to handle http call
 * 
 * it accepts 2 paramaters:
 * @isLoading : if set to true shows loader and block any further submit events
 * @onSubmit : callback fn for notifying controller about the subit event. 
 */
var app = angular.module('commonComponents');

app.component('loginWidget', {

	bindings: {
		onSubmit: '&',
		isLoading: '='
    },

    templateUrl: 'components/login-widget/template/login-widget.html',

    controller: function ($scope) {
    	
    	var self = this;
    	
    	$scope.doLogin = function() {
    		
    		if($scope.username!=null && $scope.username!=undefined  && $scope.username!='' && $scope.password!=null && $scope.password!=undefined  && $scope.password!='')
    		{
				self.onSubmit({
					$model : {
						username: $scope.username,
						password : $scope.password
					}
				    });
				self.isLoading = true;
			}
    	}
    }
    });