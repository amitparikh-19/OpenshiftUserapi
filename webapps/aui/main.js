'use strict';

// Core Library Modules 
angular
.module('appCoreModule', 
		[
		'i18nModule',
        'ui.bootstrap',
        'ui.router'
        ]);
angular
.module('app',
		[
        'appCoreModule',
        'layoutModule',
        'httpServiceModule',
        'authenticationModule',
        'accountsModule',
        'usersModule',
        'transactionsModule',
        'feeCommissionModule',
        'commonComponents'
        ])
        
        
.config(configure)

// App Modules Routes
configure.$inject = ['$stateProvider', '$urlRouterProvider'];
function configure($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/accounts/login');
	
	$stateProvider
	
	.state('mains', {
		url : '/',
		templateUrl : 'layouts/index.html'
        })
	
	.state('accounts', {
		url : '/accounts',
		templateUrl : 'modules/accounts/index.html'
        })
     .state('mains.users', {
		views: {
			"module": {
				templateUrl: 'modules/users/index.html',
			}
		}	
        })
     .state('mains.transactions', {
		views: {
			"module": {
				templateUrl: 'modules/transactions/index.html',
			}
		}	
        })
    .state('mains.feeAndCommission', {
		views: {
			"module": {
				templateUrl: 'modules/feeAndCommission/index.html',
			}
		}	
        });
                
}