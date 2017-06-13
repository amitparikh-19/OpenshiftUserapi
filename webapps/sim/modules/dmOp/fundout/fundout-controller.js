angular
    .module('topupOpModule')
    .controller('fundoutController', fundoutController);

fundoutController.$inject = ['$scope','httpService','TOPUPOP_SERVICE_URLS'];

function fundoutController($scope, httpService, TOPUPOP_SERVICE_URLS){
	
	var fundout = this;

	// Properties
	fundout.showResponseMsg = null;
	fundout.isLoading = false;
	fundout.step = 1;
	fundout.showResponseErrorMsg = null;
	
	//Methods
	fundout.dofundOut = dofundOut;
	fundout.init = init;
	
	function dofundOut()
	{
		fundout.step = 2;
		
		var fundoutEntity = {
			src : $scope.src.username,
			amount: $scope.amount
		};
		httpService.doOperation(fundoutEntity, 'POST', TOPUPOP_SERVICE_URLS.BASE+'/fundout', successFn, errorFn)

	}
	
	function init(){
		httpService.read('', TOPUPOP_SERVICE_URLS.USERLIST+'/userlist/Merchant', readMerchListSuccess, readMerchListError);
	}
	
	function readMerchListSuccess(data,status,headers,config){
		fundout.merchList = data;
	}
	
	function readMerchListError(data,status,headers,config){
		console.log("fetching merchant list failed")
	}
	
	function successFn(data,status,headers,config){
		fundout.isLoading = false;
		fundout.showResponseMsg="Fund Out is successful for the amount " +$scope.amount+  " on " + $scope.src.name;
		fundout.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		fundout.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			fundout.showResponseErrorMsg = "Error : Insufficient balance to carry out fundout";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			fundout.showResponseErrorMsg = "Error : Fund Out transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			fundout.showResponseErrorMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			fundout.showResponseErrorMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			fundout.showResponseErrorMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			fundout.showResponseErrorMsg = "Error : Unable to process Pay for Fund Out";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			fundout.showResponseErrorMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			fundout.showResponseErrorMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			fundout.showResponseErrorMsg = "Error : Unable to process Fund Out transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			fundout.showResponseErrorMsg = "Error : Unable to process Fund Out transaction";
		else
			fundout.showResponseErrorMsg = "Error : Unable to do Fund Out";
		console.log("Fund-out failed")
	}
	
}