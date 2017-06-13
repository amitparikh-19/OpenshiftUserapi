
angular
.module('feeCommissionModule', [])

.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	
	  .state('mains.feeAndCommission.list', {
		url : 'fee-commission',
		views: {
			"module": {
				templateUrl: 'modules/feeAndCommission/list/fee-commission-list.html',
			    controller:  'feeCommissionListController',
	            controllerAs : 'feeCommissions'
			}
		}
		})
	  .state('mains.feeAndCommission.details', {
        url : 'fee-commission/details/:id',
		views: {
			"module": {
				templateUrl: 'modules/feeAndCommission/details/fee-commission-details.html',
			    controller:  'feeCommissionDetailsController',
	            controllerAs : 'feeCommission'
			}
		}
       })
	  .state('mains.feeAndCommission.create', {
	    url : 'fee-commission/create',
	    views: {
				"module": {
					templateUrl: 'modules/feeAndCommission/create/fee-commission-create.html',
				    controller:  'feeCommissionCreateController',
		            controllerAs : 'feeCommission'
				}
			}
	   })
	  .state('mains.feeAndCommission.update', {
	    url : 'fee-commission/update/:id',
	    views: {
				"module": {
					templateUrl: 'modules/feeAndCommission/update/fee-commission-update.html',
				    controller:  'feeCommissionUpdateController',
		            controllerAs : 'feeCommission'
				}
			}
	   });
})

.constant('CHARGE_SERVICE_URLS', {
	BASE : 'chargeapi/charges',
	USER_BASE : 'userapi/users',
	WALTXN_BASE : 'waltxnapi/waltxns'
	});