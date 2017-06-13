angular
.module('dmOpModule')
.controller('purchaseController', purchaseController);

purchaseController.$inject = ['httpService','DMOP_SERVICE_URLS','$scope'];

function purchaseController(httpService,DMOP_SERVICE_URLS,$scope){

var purchase = this;

// Properties
purchase.showResponseMsg = null;
purchase.showResponseErrorMsg = null;
purchase.isLoading = false;

//Methods
purchase.doPurchase = doPurchase;
purchase.gotoPurchase = gotoPurchase;
purchase.init = init;

function init(){
	httpService.read('', DMOP_SERVICE_URLS.USERLIST+'/userlist/Merchant', readMerchListSuccess, readMerchListError);
	httpService.read('', DMOP_SERVICE_URLS.USERLIST+'/userlist/Customer', readCustListSuccess, readCustListError);
}

function readMerchListSuccess(data,status,headers,config){
	purchase.merchList = data;
}

function readMerchListError(data,status,headers,config){
	console.log("fetching merchant list failed")
}

function readCustListSuccess(data,status,headers,config){
	purchase.custList = data;
}

function readCustListError(data,status,headers,config){
	console.log("fetching customer list failed")
}

function doPurchase(){
	if($scope.src.username==$scope.dest.username)
		return;
	purchase.isLoading = true;
	var dmEntity = {src: $scope.src.username, dest:$scope.dest.username , amount:$scope.amount };
	httpService.doOperation(dmEntity, 'POST', DMOP_SERVICE_URLS.BASE+'/purchase', successFn, errorFn)
	purchase.showBalanceMsg ="true";
}

function successFn(data,status,headers,config){
	purchase.isLoading = false;
	purchase.showResponseMsg="Purchase successful for the amount $" +$scope.amount+  " on " + $scope.dest.name;
	purchase.response = data;
	$scope.$emit("initBalance", 'init');
}

function errorFn(data,status,headers,config){
	purchase.isLoading = false;
	if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
		purchase.showResponseErrorMsg = "Error : Insufficient balance to carry out Purchase";
	else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
		purchase.showResponseErrorMsg = "Error : Purchase transaction unable to start";
	else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
		purchase.showResponseErrorMsg = "Error : Invalid source Id provided";
	else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
		purchase.showResponseErrorMsg = "Error : Invalid destination Id provided";
	else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
		purchase.showResponseErrorMsg = "Error : Invalid amount provided";
	else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
		purchase.showResponseErrorMsg = "Error : Unable to process Pay for Purchase";
	else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
		purchase.showResponseErrorMsg = "Error : Invalid transaction type provided";
	else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
		purchase.showResponseErrorMsg = "Error : Invalid request provided";
	else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
		purchase.showResponseErrorMsg = "Error : Unable to process Purchase transaction as order is fail";
	else if(data.errorKey=='waltxnsrvc.processTxn.fail')
		purchase.showResponseErrorMsg = "Error : Unable to process Purchase transaction";
	else
		purchase.showResponseErrorMsg = "Error : Unable to do purchase";
	console.log("Purchase failed")
}

function gotoPurchase(){
	purchase.showResponseErrorMsg = null;
	$scope.src = undefined;
	$scope.dest = undefined;
	$scope.amount = "";
}


}