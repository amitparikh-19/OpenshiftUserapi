
angular
.module('usersModule', [])

.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	
	  .state('mains.users.list', {
		url : 'users',
		views: {
			"module": {
				templateUrl: 'modules/users/list/users-list.html',
			    controller:  'usersListController',
	            controllerAs : 'user'
			}
		}
		})
	  .state('mains.users.details', {
        url : 'users/details/:id',
		views: {
			"module": {
				templateUrl: 'modules/users/details/users-details.html',
			    controller:  'userDetailsController',
	            controllerAs : 'user'
			}
		}
       })
	  .state('mains.users.create', {
	    url : 'users/create',
	    views: {
				"module": {
					templateUrl: 'modules/users/create/users-create.html',
				    controller:  'usersCreateController',
		            controllerAs : 'user'
				}
			}
	   })
	  .state('mains.users.update', {
	    url : 'users/update/:id',
	    views: {
				"module": {
					templateUrl: 'modules/users/update/users-update.html',
				    controller:  'usersUpdateController',
		            controllerAs : 'user'
				}
			}
	   });
})

.constant('USERS_SERVICE_URLS', {
	BASE : 'userapi/users'
});