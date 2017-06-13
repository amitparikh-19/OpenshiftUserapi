angular
    .module('transactionsModule')
    .controller('transactionsDetailsController', transactionsDetailsController);

transactionsDetailsController.$inject = ['$state','$stateParams','$translate','userStorage','httpService','TRANSACTIONS_SERVICE_URLS'];

function transactionsDetailsController( $state,$stateParams, $translate, userStorage, httpService, TRANSACTIONS_SERVICE_URLS){
	
	var transactions = this;
	
	// Properties
	transactions.entityObject={};
	transactions.entityObject.id=$stateParams.id;
	transactions.state = 'DETAILS';
	transactions.entityName = 'Transactions';
	transactions.tid = transactions.entityObject.id;
	transactions.responseStatus="NONE";
	transactions.routeState = $state.current;
	transactions.showButtons={create:false, update:false, deleteb:false,details:false, list:true, search:false };
	
	// Methods
	transactions.init = init;
	transactions.stateChange = stateChange;


	function stateChange(model) {
		if(model.selectedState=="LIST")
			$state.go('mains.transactions.list');
		else if(model.selectedState=="DETAILS")
			localtio.href('mains.transactions.details');
	}
	
	function init() {
		httpService.read(transactions.entityObject, TRANSACTIONS_SERVICE_URLS.BASE, readSuccess, readError);
	}
	
	function readSuccess(data,status,headers,config){
		console.log("Successfully fetched transactions details")
		transactions.entity = data;
		
	}
	
	function readError(data,status,headers,config){
		console.log("Fetching transactions details failed")
	}
	
}