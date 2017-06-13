
angular
    .module('feeCommissionModule')
    .controller('feeCommissionListController', feeCommissionListController);

feeCommissionListController.$inject = ['$scope','$state','$translate','httpService', 'CHARGE_SERVICE_URLS'];

function feeCommissionListController( $scope, $state, $translate, httpService, CHARGE_SERVICE_URLS ){
	
	var feeCommissions = this;

	// Properties
	feeCommissions.filters = {};
	feeCommissions.entityObject={};
	feeCommissions.state = 'LIST';
	feeCommissions.routeState = $state.current;
	feeCommissions.showButtons={create:true, update:true, deleteb:true,details:true, list:false, search:false };
	
	feeCommissions.tid = null;
	feeCommissions.entity = 'FeeCommission';
	feeCommissions.responseStatus = "NONE";
	feeCommissions.statusMessage = "";
	feeCommissions.filters.firstresult = 0;
	feeCommissions.filters.maxresult = 10;
	feeCommissions.entityObject.id=null;
	feeCommissions.list = null;
	
	// Methods
	feeCommissions.init = init;
	feeCommissions.stateChange = stateChange;
	feeCommissions.deleteEntity = deleteEntity;
	feeCommissions.changePage = changePage;
	feeCommissions.selectedRow = selectedRow;
	
	function deleteEntity(model) {
		httpService.deleteEntity(feeCommissions.entityObject, CHARGE_SERVICE_URLS.BASE, deleteEntitySuccess, deleteEntityError);
	}
	function deleteEntitySuccess(data,status,headers,config){
		$translate('general_success_message').then(function (rbMessage) {
			feeCommissions.responseStatus = rbMessage;
        });
//		if(feeCommissions.entity.chargetype='FEE')
			$translate('fee_delete_success_message_one').then(function (rbMessage) {
				feeCommissions.statusMessage = rbMessage;
			});
//		if(feeCommissions.entity.chargetype='COMMISSION')
//			$translate('commission_delete_success_message_one').then(function (rbMessage) {
//				feeCommissions.statusMessage = rbMessage;
//			});
		$translate('fee_commission_delete_success_message').then(function (rbMessage) {
			feeCommissions.statusMessage = feeCommissions.statusMessage + feeCommissions.entityObject.id + rbMessage;
		});
		feeCommissions.init();
	}
	
	function deleteEntityError(data,status,headers,config){
		$translate('general_failure_message').then(function (rbMessage) {
			feeCommissions.responseStatus = rbMessage;
        });
//		if(feeCommissions.entity.chargetype=="FEE")
			$translate('fee_delete_failure_message').then(function (rbMessage) {
				feeCommissions.statusMessage = rbMessage + feeCommissions.entityObject.id;
			});
//		if(feeCommissions.entity.chargetype=="COMMISSION")
//			$translate('commission_delete_failure_message').then(function (rbMessage) {
//				feeCommissions.statusMessage = rbMessage + feeCommissions.entity.id;
//			});
	}

	
	function stateChange(model) {
		
		if (model.targetId==null & (model.selectedState=="UPDATE" || model.selectedState=="DETAILS" || model.selectedState=="DELETE"))
			{
			feeCommissions.responseStatus = "FAILURE";
			feeCommissions.statusMessage = "A record must be selected inorder to perform this operation.";
	        return;
	        }
		
		if(model.selectedState=="CREATE")
			{$state.go('mains.feeAndCommission.create');}
		else if(model.selectedState=="UPDATE")
			$state.go('mains.feeAndCommission.update' , {id: feeCommissions.entityObject.id});
		else if(model.selectedState=="DETAILS")
			$state.go('mains.feeAndCommission.details' , {id: feeCommissions.entityObject.id});
		else if(model.selectedState=="DELETE")
	        $("#myModal").modal({backdrop: true});
	}
	
	function changePage(pageable) {
		if(pageable=='next')
			feeCommissions.filters.firstresult = feeCommissions.list.number+1;
		else
			feeCommissions.filters.firstresult = feeCommissions.list.number-1;
		feeCommissions.filters.maxresult = 10;
		init();
	}
	
	function init() {
		httpService.readAllPageable(feeCommissions.filters, CHARGE_SERVICE_URLS.BASE, readAllPageableSuccess, readAllPageableError);
	}
	
	function readAllPageableSuccess(data,status,headers,config){
		console.log("Successfully fetched list for fees")
		feeCommissions.list = data;
	}
	
	function readAllPageableError(data,status,headers,config){
		console.log("Fetching list for fees failed")
	}
	
	function selectedRow(feeCommission) {
		if($("#row-"+feeCommission.id).hasClass('selected'))
		{
			feeCommissions.entityObject = {};
			feeCommissions.tid=null;
		    $("#row-"+feeCommission.id).removeClass('selected');
		       
		}
		else
		{
		    $("#row-"+feeCommission.id).addClass('selected').siblings().removeClass('selected');
		    feeCommissions.entityObject.id=feeCommission.id;
		    feeCommissions.tid=feeCommissions.entityObject.id;
		}
		
		clearMessage();
	}
	
	function clearMessage()
	{
		feeCommissions.responseStatus = "NONE";
		feeCommissions.statusMessage = null;
	}
	
}