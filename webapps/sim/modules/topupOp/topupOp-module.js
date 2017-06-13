
angular
.module('topupOpModule', [])

.config(function($stateProvider, $urlRouterProvider) {
	
	$urlRouterProvider.otherwise('/topupop/fundin');

	$stateProvider
	
	.state('mains.topupop.fundin', {
		url : 'topupop/fundin',
		views: {
			"module": {
				templateUrl: 'modules/topupOp/fundin/fundin.html',
			    controller: 'fundinController',
	            controllerAs : 'fundin'
			}
		}
	})
    .state('mains.topupop.txfr', {
        url : 'topupop/txfr',
		views: {
			"module": {
				templateUrl: 'modules/topupOp/txfr/txfr.html',
			    controller:  'txfrController',
	            controllerAs : 'txfr'
			}
		}	
        })
	.state('mains.topupop.topup', {
		url : 'topupop/topup',
		views: {
			"module": {
				templateUrl: 'modules/topupOp/topup/topup.html',
				controller: 'topupController',
				controllerAs : 'topup'
			}
		}
	})
})

.constant('TOPUPOP_SERVICE_URLS', {
	BASE : 'waltxnapi/waltxns',
	USERLIST : 'userapi/users'
});