angular
    .module('topupOpModule')
    .controller('txfrController', txfrController);

txfrController.$inject = ['httpService','$scope','TOPUPOP_SERVICE_URLS'];

function txfrController(httpService,$scope,TOPUPOP_SERVICE_URLS){
	
	var txfr = this;
	
	// Properties
	txfr.showResponseMsg = null;
	txfr.isLoading = false;
	txfr.showResponseErrorMsg = null;
	
	//Methods
	txfr.doTxfr = doTxfr;
	txfr.init = init;
	txfr.gotoTxfr = gotoTxfr;
	
	function init(){
		httpService.read('', TOPUPOP_SERVICE_URLS.USERLIST+'/userlist/Merchant', readMerchListSuccess, readMerchListError);
	}
	
	function readMerchListSuccess(data,status,headers,config){
		txfr.merchList = data;
	}
	
	function readMerchListError(data,status,headers,config){
		console.log("fetching merchant list failed")
	}
	
	function doTxfr(){
		if($scope.src.username==$scope.dest.username)
			return;
		txfr.isLoading = true;
		var topupEntity = {src: $scope.src.username, dest:$scope.dest.username , amount:$scope.amount };
		httpService.doOperation(topupEntity, 'POST', TOPUPOP_SERVICE_URLS.BASE+'/merchxfer', successFn, errorFn)
		txfr.showBalanceMsg ="true";
	}
	
	function successFn(data,status,headers,config){
		txfr.isLoading = false;
		txfr.showResponseMsg="Merchant Transfer successful for the amount $" +$scope.amount+  " on " + $scope.dest.name;
		txfr.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		$scope.$on('someEvent', function(event, data) { console.log(data); });
		txfr.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			txfr.showResponseErrorMsg = "Error : Insufficient balance to carry out Transfer";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			txfr.showResponseErrorMsg = "Error : Transfer transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			txfr.showResponseErrorMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			txfr.showResponseErrorMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			txfr.showResponseErrorMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			txfr.showResponseErrorMsg = "Error : Unable to process Pay for Transfer";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			txfr.showResponseErrorMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			txfr.showResponseErrorMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			txfr.showResponseErrorMsg = "Error : Unable to process Transfer transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			txfr.showResponseErrorMsg = "Error : Unable to process Transfer transaction";
		else
			txfr.showResponseErrorMsg = "Error : Unable to do Merchant Transfer";
		console.log("Merchant Transfer failed")
	}
	
	function gotoTxfr(){
		txfr.showResponseErrorMsg = null;
		$scope.src = undefined;
		$scope.dest = undefined;
		$scope.amount = "";
	}
	
}