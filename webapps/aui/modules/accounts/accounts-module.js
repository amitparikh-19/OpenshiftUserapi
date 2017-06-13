
angular
.module('accountsModule', [])

.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	
	.state('accounts.login', {
		url : '/login',
		views: {
			"mainView": {
				templateUrl: 'modules/accounts/login/accounts-login.html',
			    controller: 'userLoginController',
	            controllerAs : 'loginForm'
			}
		}
	})
    .state('mains.accounts', {
        url : 'accounts/details',
		views: {
			"module": {
				templateUrl: 'modules/accounts/details/accounts-details.html',
			    controller:  'accountDetailsController',
	            controllerAs : 'accountDetails'
			}
		}	
        })
	.state('accounts.logout', {
		url : '/login',
		views: {
			"mainView": {
				templateUrl: 'modules/accounts/login/accounts-login.html',
				controller: 'userLogoutController',
				controllerAs : 'logout'
			}
		}
	})
})

.constant('ACCOUNTS_SERVICE_URLS', {
	LOGIN : 'userapi/auth'
});