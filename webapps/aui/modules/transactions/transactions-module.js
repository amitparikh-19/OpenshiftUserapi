
angular
.module('transactionsModule', [])

.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	
	  .state('mains.transactions.list', {
		url : 'transactions',
		views: {
			"module": {
				templateUrl: 'modules/transactions/list/transactions-list.html',
			    controller:  'transactionsListController',
	            controllerAs : 'transactions'
			}
		}
		})
	  .state('mains.transactions.details', {
        url : 'transactions/details/:id',
		views: {
			"module": {
				templateUrl: 'modules/transactions/details/transactions-details.html',
			    controller:  'transactionsDetailsController',
	            controllerAs : 'transactions'
			}
		}
       });
})

.constant('TRANSACTIONS_SERVICE_URLS', {
	BASE : 'waltxnapi/waltxns'
});