angular
    .module('feeCommissionModule')
    .controller('feeCommissionDetailsController', feeCommissionDetailsController);

feeCommissionDetailsController.$inject = ['$state','$stateParams','$translate','httpService','CHARGE_SERVICE_URLS'];

function feeCommissionDetailsController( $state,$stateParams, $translate, httpService, CHARGE_SERVICE_URLS){
	
	var feeCommission = this;
	
	// Properties
	feeCommission.entityObject={};
	feeCommission.entityObject.id=$stateParams.id;
	feeCommission.state = 'DETAILS';
	feeCommission.entityName = 'User';
	feeCommission.tid = feeCommission.entityObject.id;
	feeCommission.responseStatus="NONE";
	feeCommission.routeState = $state.current;
	feeCommission.showButtons={create:true, update:true, deleteb:true,details:false, list:true, search:false };
	
	// Methods
	feeCommission.init = init;
	feeCommission.stateChange = stateChange;
	feeCommission.deleteEntity = deleteEntity;


	function stateChange(model) {
		if(model.selectedState=="LIST")
			$state.go('mains.feeAndCommission.list');
		else if(model.selectedState=="DELETE")
	        $("#myModal").modal({backdrop: true});
		else if(model.selectedState=="DETAILS")
			localtio.href('mains.feeAndCommission.details');
		else if(model.selectedState=="UPDATE")
			$state.go('mains.feeAndCommission.update',{id: feeCommission.entityObject.id});
		else if(model.selectedState=="CREATE")
			$state.go('mains.feeAndCommission.create');
		console.log(model);
	}
	
	function deleteEntity(model) {
		feeCommission.entity.id=feeCommission.entity.id;
		httpService.deleteEntity(feeCommission.entity, CHARGE_SERVICE_URLS.BASE, deleteEntitySuccess, deleteEntityError);
		feeCommission.state = 'DELETE';
	}
	function deleteEntitySuccess(data,status,headers,config){
		$translate('general_success_message').then(function (rbMessage) {
        	feeCommission.responseStatus = rbMessage;
        });
		if(feeCommission.entity.chargetype='FEE')
			$translate('fee_delete_success_message_one').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		if(feeCommission.entity.chargetype='COMMISSION')
			$translate('commission_delete_success_message_one').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage;
			});
		$translate('fee_commission_delete_success_message').then(function (rbMessage) {
			feeCommission.statusMessage = feeCommission.statusMessage + data.entityId + rbMessage;
		});
		feeCommission.showButtons={create:false, update:false, deleteb:false,details:false, list:true, search:false };
	}
	
	function deleteEntityError(data,status,headers,config){
		$translate('general_failure_message').then(function (rbMessage) {
        	feeCommission.responseStatus = rbMessage;
        });
		if(feeCommissions.entity.chargetype=="FEE")
			$translate('fee_delete_failure_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage + feeCommission.entity.id;
			});
		if(feeCommissions.entity.chargetype=="COMMISSION")
			$translate('commission_delete_failure_message').then(function (rbMessage) {
				feeCommission.statusMessage = rbMessage + feeCommission.entity.id;
			});
	}
	function init() {
		httpService.read(feeCommission.entityObject, CHARGE_SERVICE_URLS.BASE, readSuccess, readError);
	}
	function readSuccess(data,status,headers,config){
		console.log("Successfully fetched fee details")
		feeCommission.entity = data;
	}
	
	function readError(data,status,headers,config){
		console.log("Fetching fee details failed")
	}
	
}