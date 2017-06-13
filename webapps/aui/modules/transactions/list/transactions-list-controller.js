
angular
    .module('transactionsModule')
    .controller('transactionsListController', transactionsListController);

transactionsListController.$inject = ['$scope','$state','$translate','userStorage','httpService', 'TRANSACTIONS_SERVICE_URLS'];

function transactionsListController( $scope,$state, $translate, userStorage, httpService, TRANSACTIONS_SERVICE_URLS ){
	
	var transactions = this;

	// Properties
	transactions.filters = {};
	transactions.entityObject={};
	transactions.state = 'LIST';
	transactions.routeState = $state.current;
	transactions.showButtons={create:false, update:false, deleteb:false,details:true, list:false, search:false };
	
	transactions.tid = null;
	transactions.entity = 'Transactions';
	transactions.responseStatus = "NONE";
	transactions.statusMessage = "";
	transactions.filters.firstresult = 0;
	transactions.filters.maxresult = 10;
	transactions.entityObject.id=null;
	transactions.list = null;
	
	// Methods
	transactions.init = init;
	transactions.stateChange = stateChange;
	transactions.changePage = changePage;
	transactions.selectedRow = selectedRow;
	
	
	function stateChange(model) {
		if (model.targetId==null & (model.selectedState=="DETAILS"))
			{
				transactions.responseStatus = "FAILURE";
				transactions.statusMessage = "A record must be selected inorder to perform this operation.";
				return;
	        }

		$state.go('mains.transactions.details' , {id: transactions.entityObject.id});
		
	}
	
	function changePage(pageable) {
		if(pageable=='next')
			transactions.filters.firstresult = transactions.list.number+1;
		else
			transactions.filters.firstresult = transactions.list.number-1;
		transactions.filters.maxresult = 10;
		init();
	}
	
	function init() {
		httpService.readAllPageable(transactions.filters, TRANSACTIONS_SERVICE_URLS.BASE, readAllPageableSuccess, readAllPageableError);
	}
	
	function readAllPageableSuccess(data,status,headers,config){
		console.log("Successfully fetched list for transactions")
		transactions.list = data;
	}
	
	function readAllPageableError(data,status,headers,config){
		console.log("Fetching list for transactions failed")
	}
	
	function selectedRow(transaction) {
		if($("#row-"+transaction.txnid).hasClass('selected'))
		{
			   transactions.entityObject = {};
			   transactions.tid=null;
		       $("#row-"+transaction.txnid).removeClass('selected');
		       
		}
		else
		{
		       $("#row-"+transaction.txnid).addClass('selected').siblings().removeClass('selected');
		       transactions.entityObject.id=transaction.txnid;
			   transactions.tid=transactions.entityObject.id;
		}
		
		clearMessage();
	}
	
	function clearMessage()
	{
		transactions.responseStatus = "NONE";
		transactions.statusMessage = null;
	}
	
}