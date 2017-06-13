angular
    .module('feeCommissionModule')
    .controller('feeCommissionUpdateController', feeCommissionUpdateController);

feeCommissionUpdateController.$inject = ['$state','$stateParams','$translate','httpService','CHARGE_SERVICE_URLS'];

function feeCommissionUpdateController( $state,$stateParams,$translate,httpService,CHARGE_SERVICE_URLS){
	
	var feeCommission = this;
	
	// Properties
	feeCommission.entityObject={};
	feeCommission.entityObject.id=$stateParams.id;
	feeCommission.state = 'UPDATE';
	feeCommission.tid = '1';
	feeCommission.step='step1';
	feeCommission.routeState = $state.current;
	
	// Methods
	feeCommission.init = init;
	feeCommission.updateCharge=updateCharge;
	feeCommission.stepChange = stepChange;
	feeCommission.responseStatus = "NONE";
	feeCommission.statusMessage = null;
	feeCommission.reset=reset;
	feeCommission.cancel=cancel;
	function init() {
		
			httpService.read(feeCommission.entityObject, CHARGE_SERVICE_URLS.BASE, readSuccess, readError);
			httpService.doOperation(feeCommission.entity, 'GET' ,CHARGE_SERVICE_URLS.WALTXN_BASE+'/listType', readTxnTypeSuccess, readTxnTypeError);
			httpService.doOperation(feeCommission.entity, 'GET' ,CHARGE_SERVICE_URLS.BASE+'/payerlist', readPayerSuccess, readPayerError);
			httpService.doOperation(feeCommission.entity, 'GET' ,CHARGE_SERVICE_URLS.BASE+'/typelist', readChargeTypeSuccess, readChargeTypeError);			
				
	}
	function readSuccess(data,status,headers,config){
		console.log("Successfully fetched charges details")
		feeCommission.entity = data;
		feeCommission.entity.id = data.id;
	}
	
	function readError(data,status,headers,config){
		console.log("Fetching charges details failed")
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
	
	function updateCharge(){
		feeCommission.responseStatus = "NONE";
		feeCommission.statusMessage = null;
		httpService.update(feeCommission.entity, CHARGE_SERVICE_URLS.BASE, updateSuccess, updateError);

	}
	
	function cancel(){
		$state.go('mains.feeAndCommission.list');
	}
	
	function reset(){
		feeCommission.init();
	} 
	
	function updateSuccess(data,status,headers,config){
		console.log("Successfully created the user")
		$translate('general_success_message').then(function (rbMessage) {
			feeCommission.responseStatus = rbMessage;
        });
		if(feeCommission.chargetype='FEE'){
			$translate('fee_update_success_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		}
		if(feeCommission.chargetype='COMMISSION'){
			$translate('commission_update_success_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		}
	}
	
	function updateError(data,status,headers,config){
		console.log("user creation failed")
		$translate('general_failuer_message').then(function (rbMessage) {
			feeCommission.responseStatus = rbMessage;
        });
		if(feeCommission.chargetype == 'FEE'){
			$translate('fee_update_error_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		}
		if(feeCommission.chargetype == 'COMMISSION'){
			$translate('commission_update_error_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		}
	}
	
	function stepChange(step) {
		
		feeCommission.step=step;
	}
}
