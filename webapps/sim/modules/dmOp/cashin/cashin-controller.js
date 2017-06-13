angular
    .module('dmOpModule')
    .controller('cashinController', cashinController);

cashinController.$inject = ['httpService','DMOP_SERVICE_URLS','$scope'];

function cashinController(httpService,DMOP_SERVICE_URLS,$scope){
	var cashin = this;
	
	// Properties
	cashin.showResponseMsg = null;
	cashin.isLoading = false;
	cashin.showResponseErrorMsg = null;
	
	
	//Methods
	cashin.doCashin = doCashin;
	cashin.init = init;
	cashin.gotoCashin = gotoCashin;
	
	function init(){
		httpService.read('', DMOP_SERVICE_URLS.USERLIST+'/userlist/Merchant', readMerchListSuccess, readMerchListError);
		httpService.read('', DMOP_SERVICE_URLS.USERLIST+'/userlist/Customer', readCustListSuccess, readCustListError);
	}
	
	function readMerchListSuccess(data,status,headers,config){
		cashin.merchList = data;
	}
	
	function readMerchListError(data,status,headers,config){
		console.log("fetching merchant list failed")
	}
	
	function readCustListSuccess(data,status,headers,config){
		cashin.custList = data;
	}
	
	function readCustListError(data,status,headers,config){
		console.log("fetching customer list failed")
	}
	
	function doCashin(){
		if($scope.src.username==$scope.dest.username)
			return;
		cashin.isLoading = true;
		var dmEntity = {src: $scope.src.username, dest:$scope.dest.username , amount:$scope.amount };
		httpService.doOperation(dmEntity, 'POST', DMOP_SERVICE_URLS.BASE+'/cashin', successFn, errorFn)
		cashin.showBalanceMsg ="true";
	}
	
	function successFn(data,status,headers,config){
		cashin.isLoading = false;
		cashin.showResponseMsg="Cash-In successful for the amount $" +$scope.amount+  " on " + $scope.dest.name;
		cashin.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		cashin.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			cashin.showResponseErrorMsg = "Error : Insufficient balance to carry out cashout";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			cashin.showResponseErrorMsg = "Error : Cashout transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			cashin.showResponseErrorMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			cashin.showResponseErrorMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			cashin.showResponseErrorMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			cashin.showResponseErrorMsg = "Error : Unable to process Pay for cashout";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			cashin.showResponseErrorMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			cashin.showResponseErrorMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			cashin.showResponseErrorMsg = "Error : Unable to process Cashout transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			cashin.showResponseErrorMsg = "Error : Unable to process Cashout transaction";
		else
			cashin.showResponseErrorMsg = "Error : Unable to do Cashin".
		console.log("Cashin failed")
	}
	
	function gotoCashin(){
		cashin.showResponseErrorMsg = null;
		$scope.src = undefined;
		$scope.dest = undefined;
		$scope.amount = "";
	}
	
	
}