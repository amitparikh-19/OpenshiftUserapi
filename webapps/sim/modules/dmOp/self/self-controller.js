angular
    .module('dmOpModule')
    .controller('selfController', selfController);

selfController.$inject = ['httpService','$scope','DMOP_SERVICE_URLS','TOPUPOP_SERVICE_URLS'];

function selfController(httpService,$scope,DMOP_SERVICE_URLS,TOPUPOP_SERVICE_URLS){
	
	var self = this;
	
	// Properties
	self.showResponseMsg = null;
	self.isLoading = false;
	
	//Methods
	self.doTopup = doTopup;
	self.init = init;
	self.gotoTopup = gotoTopup;
	
	function init(){
		httpService.read('', TOPUPOP_SERVICE_URLS.USERLIST+'/userlist/Wsp', readWspSuccess, readWspError);
		httpService.read('', TOPUPOP_SERVICE_URLS.USERLIST+'/userlist/Customer', readCustListSuccess, readCustListError);
	}
	
	function readWspSuccess(data,status,headers,config){
		self.wspList = data;
	}
	
	function readWspError(data,status,headers,config){
		console.log("fetching wsp list failed")
	}
	
	function readCustListSuccess(data,status,headers,config){
		self.custList = data;
	}
	
	function readCustListError(data,status,headers,config){
		console.log("fetching customer list failed")
	}
	
	function doTopup(){
		self.isLoading = true;
		var topupEntity = {src: $scope.src.username, dest:$scope.dest.username , amount:$scope.amount };
		httpService.doOperation(topupEntity, 'POST', TOPUPOP_SERVICE_URLS.BASE+'/self', successFn, errorFn)
		self.showBalanceMsg ="true";
	}
	
	function successFn(data,status,headers,config){
		self.isLoading = false;
		self.showResponseMsg="Topup successful for the amount $" +$scope.amount+  " on " + $scope.dest.username;
		self.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		self.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			self.showResponseMsg = "Error : Insufficient balance to carry out self";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			self.showResponseMsg = "Error : Self Topup transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			self.showResponseMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			self.showResponseMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			self.showResponseMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			self.showResponseMsg = "Error : Unable to process Pay for Self Topup";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			self.showResponseMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			self.showResponseMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			self.showResponseMsg = "Error : Unable to process Self Topup transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			self.showResponseMsg = "Error : Unable to process Self Topup transaction";
		else if(data.errorKey=='waltxnsrvc.processTxn.subid.notfound')
			self.showResponseMsg = "Error : Unable to process Self Topup transaction as Subcriber ID is not found";
		else
			self.showResponseMsg = "Error : Unable to do Topup";
		console.log("Topup failed")
	}
	
	function gotoTopup(){
		self.showResponseMsg = null;
		$scope.src = "";
		$scope.dest = "";
		$scope.amount = "";
	}
	
	
}