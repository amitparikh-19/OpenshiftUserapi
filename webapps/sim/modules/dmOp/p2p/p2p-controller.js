angular
    .module('dmOpModule')
    .controller('p2pController', p2pController);

p2pController.$inject = ['httpService','$scope','DMOP_SERVICE_URLS'];

function p2pController(httpService,$scope,DMOP_SERVICE_URLS){
	
	var p2p = this;
	
	// Properties
	p2p.showResponseMsg = null;
	p2p.showResponseErrorMsg = null;
	p2p.isLoading = false;
	
	//Methods
	p2p.doP2p = doP2p;
	p2p.init = init;
	p2p.gotoP2P = gotoP2P;

	
	function init(){
		httpService.read('', DMOP_SERVICE_URLS.USERLIST+'/userlist/Customer', readCustListSuccess, readCustListError);
	}
	
	
	function readCustListSuccess(data,status,headers,config){
		p2p.custList = data;
	}
	
	function readCustListError(data,status,headers,config){
		console.log("fetching customer list failed")
	}
	
	function doP2p(){
		if($scope.src.username==$scope.dest.username)
					return;		
		p2p.isLoading = true;
		var topupEntity = {src: $scope.src.username, dest:$scope.dest.username , amount:$scope.amount};
		httpService.doOperation(topupEntity, 'POST', DMOP_SERVICE_URLS.BASE+'/send', successFn, errorFn)
		p2p.showBalanceMsg ="true";
	}
	
	function successFn(data,status,headers,config){
		p2p.isLoading = false;
		p2p.showResponseMsg=" P2P is successful for the amount $" +$scope.amount;
		p2p.response = data;
		$scope.$emit("initBalance", 'init');
	}
	
	function errorFn(data,status,headers,config){
		p2p.isLoading = false;
		if(data.errorKey=='waltxnsrvc.processTxn.balance.insufficient')
			p2p.showResponseErrorMsg = "Error : Insufficient balance to carry out P2P";
		else if(data.errorKey=='waltxnsrvc.processtxn.start.fail')
			p2p.showResponseErrorMsg = "Error : P2P transaction unable to start";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.srcinvalid')
			p2p.showResponseErrorMsg = "Error : Invalid source Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.destinvalid')
			p2p.showResponseErrorMsg = "Error : Invalid destination Id provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.validate.amtinvalid')
			p2p.showResponseErrorMsg = "Error : Invalid amount provided";
		else if(data.errorKey=='waltxnsrvc.processtxn.pay.fail')
			p2p.showResponseErrorMsg = "Error : Unable to process Pay for P2P";
		else if(data.errorKey=='waltxnsrvc.txnresolver.txntype.invalid')
			p2p.showResponseErrorMsg = "Error : Invalid transaction type provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.request.invalid')
			p2p.showResponseErrorMsg = "Error : Invalid request provided";
		else if(data.errorKey=='waltxnsrvc.processTxn.order.fail')
			p2p.showResponseErrorMsg = "Error : Unable to process P2P transaction as order is fail";
		else if(data.errorKey=='waltxnsrvc.processTxn.fail')
			p2p.showResponseErrorMsg = "Error : Unable to process P2P transaction";
		else if(data.errorKey=='waltxnsrvc.processTxn.subid.notfound')
			p2p.showResponseErrorMsg = "Error : Unable to process P2P transaction as Subcriber ID is not found";
		else
			p2p.showResponseErrorMsg = "Error : Unable to do P2P";
		console.log("P2p failed")
	}
	
	function gotoP2P(){
		p2p.showResponseErrorMsg = null;
		$scope.src = undefined;
		$scope.dest = undefined;
		$scope.amount = "";
		
	}
	
}