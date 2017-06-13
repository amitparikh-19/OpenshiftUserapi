angular
    .module('dmOpModule')
    .controller('cashoutController', cashoutController);

cashoutController.$inject = ['httpService','DMOP_SERVICE_URLS','$scope'];

function cashoutController(httpService,DMOP_SERVICE_URLS,$scope){
	
	var cashout = this;
	
	// Properties
	cashout.showResponseMsg = null;
	cashout.isLoading = false;
	cashout.showResponseErrorMsg = null;
	
	//Methods
	cashout.doCashout = doCashout;
	cashout.init = init;
	cashout.gotoCashout = gotoCashout;
	
	function init(){
		httpService.read('', DMOP_SERVICE_URLS.USERLIST+'/userlist/Merchant', readMerchListSuccess, readMerchListError);
		httpService.read('', DMOP_SERVICE_URLS.USERLIST+'/userlist/Customer', readCustListSuccess, readCustListError);
	}
	
	function readMerchListSuccess(data,status,headers,config){
		cashout.merchList = data;
	}
	
	function readMerchListError(data,status,headers,config){
		console.log("fetching merchant list failed")
	}
	
	function readCustListSuccess(data,status,headers,config){
		cashout.custList = data;
	}
	
	function readCustListError(data,status,headers,config){
		console.log("fetching customer list failed")
	}
	
	function doCashout(){
		if($scope.src.username==$scope.dest.username)
			return;
		cashout.isLoading = true;
		var dmEntity = {src: $scope.src.username, dest:$scope.dest.username , amount:$scope.amount };
		httpService.doOperation(dmEntity, 'POST', DMOP_SERVICE_URLS.BASE+'/cashout', successFn, errorFn)
		cashout.showBalanceMsg ="true";
	}
	
	function successFn(data,status,headers,config){
		cashout.isLoading = false;
		cashout.showResponseMsg="Cash-Out successful for the amount $" +$scope.amount+  " on " + $scope.dest.name;
		cashout.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		cashout.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			cashout.showResponseErrorMsg = "Error : Insufficient balance to carry out cashout";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			cashout.showResponseErrorMsg = "Error : Cashout transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			cashout.showResponseErrorMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			cashout.showResponseErrorMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			cashout.showResponseErrorMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			cashout.showResponseErrorMsg = "Error : Unable to process Pay for cashout";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			cashout.showResponseErrorMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			cashout.showResponseErrorMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			cashout.showResponseErrorMsg = "Error : Unable to process Cashout transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			cashout.showResponseErrorMsg = "Error : Unable to process Cashout transaction";
		else
			cashout.showResponseErrorMsg = "Error : Unable to do Cashout";
		console.log("Cashout failed")
	}
	
	function gotoCashout(){
		cashout.showResponseErrorMsg = null;
		$scope.src = undefined;
		$scope.dest = undefined;
		$scope.amount = "";
	}
	
}