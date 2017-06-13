
angular
.module('dmOpModule', [])

.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	
	  .state('mains.dmop.fundin', {
		url : 'dmop/fundin',
		views: {
			"module": {
				templateUrl: 'modules/topupOp/fundin/fundin.html',
			    controller:  'fundinController',
	            controllerAs : 'fundin'
			}
		}
	   })
	   .state('mains.dmop.txfr', {
	    url : 'dmop/txfr',
	    views: {
				"module": {
					templateUrl: 'modules/topupOp/txfr/txfr.html',
				    controller:  'txfrController',
		            controllerAs : 'txfr'
				}
			}
	   })
	  .state('mains.dmop.topup', {
		    url : 'dmop/topup',
		    views: {
					"module": {
						templateUrl: 'modules/topupOp/topup/topup.html',
					    controller:  'topupController',
			            controllerAs : 'topup'
					}
				}
	   })
	   .state('mains.dmop.self', {
		    url : 'dmop/self',
		    views: {
					"module": {
						templateUrl: 'modules/dmOp/self/self.html',
					    controller:  'selfController',
			            controllerAs : 'self'
					}
				}
	   })
	   .state('mains.dmop.purchase', {
		    url : 'dmop/purchase',
		    views: {
					"module": {
						templateUrl: 'modules/dmOp/purchase/purchase.html',
					    controller:  'purchaseController',
			            controllerAs : 'purchase'
					}
				}
	   })
	   .state('mains.dmop.p2p', {
	    url : 'dmop/p2p',
	    views: {
				"module": {
					templateUrl: 'modules/dmOp/p2p/p2p.html',
				    controller:  'p2pController',
		            controllerAs : 'p2p'
				}
			}
	   })
	   .state('mains.dmop.cashin', {
		    url : 'dmop/cashin',
		    views: {
					"module": {
						templateUrl: 'modules/dmOp/cashin/cashin.html',
					    controller:  'cashinController',
			            controllerAs : 'cashin'
					}
				}
	  })
	  .state('mains.dmop.cashout', {
		    url : 'dmop/cashout',
		    views: {
					"module": {
						templateUrl: 'modules/dmOp/cashout/cashout.html',
					    controller:  'cashoutController',
			            controllerAs : 'cashout'
					}
				}
	  })
	  .state('mains.dmop.fundout', {
		  url : 'dmop/fundout',
		  views: {
			  	"module": {
			  		templateUrl: 'modules/dmOp/fundout/fundout.html',
			  		controller:  'fundoutController',
			  		controllerAs : 'fundout'
			}
		}
       })
	
})

.constant('DMOP_SERVICE_URLS', {
	BASE : 'waltxnapi/waltxns',
	USERLIST : 'userapi/users'
});