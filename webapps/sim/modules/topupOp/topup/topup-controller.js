angular
    .module('topupOpModule')
    .controller('topupController', topupController);

topupController.$inject = ['httpService','$scope','TOPUPOP_SERVICE_URLS'];

function topupController(httpService,$scope,TOPUPOP_SERVICE_URLS){
	
	var topup = this;
	
	// Properties
	topup.showResponseMsg = null;
	topup.isLoading = false;
	topup.showResponseErrorMsg = null;
	
	//Methods
	topup.doTopup = doTopup;
	topup.init = init;
	topup.gotoTopup = gotoTopup;
	
	function init(){
		httpService.read('', TOPUPOP_SERVICE_URLS.USERLIST+'/userlist/Wsp', readWspSuccess, readWspError);
		httpService.read('', TOPUPOP_SERVICE_URLS.USERLIST+'/userlist/Merchant', readMerchListSuccess, readMerchListError);
	}
	
	function readWspSuccess(data,status,headers,config){
		topup.wspList = data;
	}
	
	function readWspError(data,status,headers,config){
		console.log("fetching wsp list failed")
	}
	
	function readMerchListSuccess(data,status,headers,config){
		topup.merchList = data;
	}
	
	function readMerchListError(data,status,headers,config){
		console.log("fetching merchant list failed")
	}
	
	function doTopup(){
		topup.isLoading = true;
		var topupEntity = {src: $scope.src.username, dest:$scope.dest.username , amount:$scope.amount , subscriber:$scope.subscriber };
		httpService.doOperation(topupEntity, 'POST', TOPUPOP_SERVICE_URLS.BASE+'/sale', successFn, errorFn)
		topup.showBalanceMsg ="true";
	}
	
	function successFn(data,status,headers,config){
		topup.isLoading = false;
		topup.showResponseMsg="Topup successful for the amount $" +$scope.amount+  " on " + $scope.subscriber;
		topup.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		topup.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			topup.showResponseErrorMsg = "Error : Insufficient balance to carry out topup";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			topup.showResponseErrorMsg = "Error : Topup transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			topup.showResponseErrorMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			topup.showResponseErrorMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			topup.showResponseErrorMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			topup.showResponseErrorMsg = "Error : Unable to process Pay for Topup";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			topup.showResponseErrorMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			topup.showResponseErrorMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			topup.showResponseErrorMsg = "Error : Unable to process Topup transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			topup.showResponseErrorMsg = "Error : Unable to process Topup transaction";
		else if(data.errorKey=='waltxnsrvc.processTxn.subid.notfound')
			topup.showResponseErrorMsg = "Error : Unable to process Topup transaction as Subcriber ID is not found";
		else
			topup.showResponseErrorMsg = "Error : Unable to do Topup";
		console.log("Topup failed")
	}
	
	function gotoTopup(){
		topup.showResponseErrorMsg = null;
		$scope.src = "";
		$scope.dest = "";
		$scope.amount = "";
		$scope.subscriber = "";
	}
	
}