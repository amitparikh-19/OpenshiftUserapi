angular
    .module('feeCommissionModule')
    .controller('feeCommissionCreateController', feeCommissionCreateController);

feeCommissionCreateController.$inject = ['$state','$translate','httpService','CHARGE_SERVICE_URLS'];

function feeCommissionCreateController( $state, $translate, httpService,CHARGE_SERVICE_URLS){
	
	var feeCommission = this;
	
	// Properties
	feeCommission.entity=new FeeCommission();
	feeCommission.step='step1';
	feeCommission.routeState = $state.current;
	
	// Methods
	feeCommission.init = init;
	feeCommission.createCharge=createCharge;
	feeCommission.stepChange = stepChange;
	feeCommission.responseStatus = "NONE";
	feeCommission.statusMessage = null;
	feeCommission.reset=reset;
	feeCommission.cancel=cancel;
	function init() {
		httpService.doOperation(feeCommission.entity, 'GET' ,CHARGE_SERVICE_URLS.WALTXN_BASE+'/listType', readTxnTypeSuccess, readTxnTypeError);
		httpService.doOperation(feeCommission.entity, 'GET' ,CHARGE_SERVICE_URLS.BASE+'/payerlist', readPayerSuccess, readPayerError);
		httpService.doOperation(feeCommission.entity, 'GET' ,CHARGE_SERVICE_URLS.BASE+'/typelist', readChargeTypeSuccess, readChargeTypeError);
	}
	
	function readTxnTypeSuccess(data,status,headers,config){
		feeCommission.txntypeList = data;
	}
	
	function readTxnTypeError(data,status,headers,config){
		console.log("Unable to get transaction type list")
	}
	
	function readPayerSuccess(data,status,headers,config){
		feeCommission.payerList = data;
	}
	
	function readPayerError(data,status,headers,config){
		console.log("Unable to get transaction type list")
	}
	
	function readChargeTypeSuccess(data,status,headers,config){
		feeCommission.chargetypeList = data;
	}
	
	function readChargeTypeError(data,status,headers,config){
		console.log("Unable to get transaction type list")
	}
	
	function createCharge(){
		feeCommission.responseStatus = "NONE";
		feeCommission.statusMessage = null;
		httpService.create(feeCommission.entity, CHARGE_SERVICE_URLS.BASE, createSuccess, createError);
        
	}
	function cancel(){
		
		$state.go('mains.feeAndCommission.list');
	}
	function reset(){
		feeCommission.entity=new FeeCommission();
	} 
	function createSuccess(data,status,headers,config){
		feeCommission.entity = data;
		$translate('general_success_message').then(function (rbMessage) {
        	feeCommission.responseStatus = rbMessage;
        });
		if(feeCommission.entity.chargetype == 'FEE')
			$translate('fee_create_success_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		if(feeCommission.entity.chargetype == 'COMMISSION')
			$translate('commission_create_success_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		console.log("Successfully created the Charge")
	}
	
	function createError(data,status,headers,config){
		$translate('general_failure_message').then(function (rbMessage) {
        	feeCommission.responseStatus = rbMessage;
        });
		if(feeCommission.entity.chargetype == 'FEE')
			$translate('fee_create_failure_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		if(feeCommission.entity.chargetype == 'COMMISSION')
			$translate('commission_create_failure_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		console.log("Charge creation failed")
	}
	
	function stepChange(step) {
		feeCommission.step=step;
	}
}