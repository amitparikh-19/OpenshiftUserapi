angular
    .module('topupOpModule')
    .controller('fundinController', fundinController);

fundinController.$inject = ['$scope','httpService','TOPUPOP_SERVICE_URLS'];

function fundinController($scope,httpService,TOPUPOP_SERVICE_URLS){
	
	var fundin = this;
	
	fundin.step = 1;
	fundin.showResponseErrorMsg = null;
	
	fundin.createOrder = createOrder;
	fundin.gotoStep3 = gotoStep3;
	fundin.init = init;
	
	function init(){
		$scope.$emit("initBalance", 'inittopup');
		httpService.read('', TOPUPOP_SERVICE_URLS.USERLIST+'/userlist/Merchant,Wsp', readMerchListSuccess, readMerchListError);
	}

	function readMerchListSuccess(data,status,headers,config){
		fundin.merchList = data;
	}
	
	function readMerchListError(data,status,headers,config){
		console.log("fetching merchant list failed")
	}
	
	function createOrder()
	{
		var fundinEntity = {
				dest : $scope.dest.username,
				amount: $scope.amount
			};
			httpService.doOperation(fundinEntity, 'POST', TOPUPOP_SERVICE_URLS.BASE+'/fundin', successFn, errorFn)

		fundin.step = 2;
	}
	
	function successFn(data,status,headers,config){
		fundin.isLoading = false;
		fundin.showResponseMsg="Fund-in successful for the amount $" +$scope.amount+  " on " + $scope.dest.name;
		fundin.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		fundin.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			fundin.showResponseErrorMsg = "Error : Insufficient balance to carry out Fund In";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			fundin.showResponseErrorMsg = "Error : Fund In transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			fundin.showResponseErrorMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			fundin.showResponseErrorMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			fundin.showResponseErrorMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			fundin.showResponseErrorMsg = "Error : Unable to process Pay for Fund In";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			fundin.showResponseErrorMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			fundin.showResponseErrorMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			fundin.showResponseErrorMsg = "Error : Unable to process Fund In transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			fundin.showResponseErrorMsg = "Error : Unable to process Fund In transaction";
		else
			fundin.showResponseErrorMsg = "Error : Unable to do Fund In";
		console.log("Fund-in failed")
	}
	
	function gotoStep3()
	{
		fundin.step = 3;
	}
	
}