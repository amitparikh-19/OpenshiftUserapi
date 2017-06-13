'use strict';

// Core Library Modules 
angular
.module('appCoreModule', 
		[
		'i18nModule',
        'ui.bootstrap',
        'ui.router',
        'ngAnimate'
        ]);
angular
.module('sim',
		[
        'appCoreModule',
        'httpServiceModule',
        'statModule',
        'dmOpModule',
        'topupOpModule',
        'commonComponents'
        ])
        
        
.config(configure)

// Sim Modules Routes
configure.$inject = ['$stateProvider', '$urlRouterProvider'];
function configure($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/');
	
	$stateProvider
	
	.state('mains', {
		url : '/',
		templateUrl : 'layouts/index.html',
		controller : 'statController',
		controllerAs: 'stat'
        })
	
     .state('mains.dmop', {
		views: {
			"module": {
				templateUrl: 'modules/dmOp/index.html',
			}
		}	
        })
        
     .state('mains.topupop', {
		views: {
			"module": {
				templateUrl: 'modules/topupOp/index.html',
			}
		}	
        });
                
}