angular
    .module('usersModule')
    .controller('userDetailsController', userDetailsController);

userDetailsController.$inject = ['$state','$stateParams','$translate','userStorage','httpService','USERS_SERVICE_URLS'];

function userDetailsController( $state,$stateParams, $translate, userStorage, httpService, USERS_SERVICE_URLS){
	
	var user = this;
	
	// Properties
	user.entityObject={};
	user.entityObject.id=$stateParams.id;
	user.state = 'DETAILS';
	user.entityName = 'User';
	user.tid = user.entityObject.id;
	user.responseStatus="NONE";
	user.routeState = $state.current;
	user.showButtons={create:true, update:true, deleteb:true,details:false, list:true, search:false };
	
	// Methods
	user.init = init;
	user.stateChange = stateChange;
	user.deleteEntity = deleteEntity;


	function stateChange(model) {
		if(model.selectedState=="LIST")
			$state.go('mains.users.list');
		else if(model.selectedState=="DELETE")
	        $("#myModal").modal({backdrop: true});
		else if(model.selectedState=="DETAILS")
			localtio.href('mains.users.details');
		else if(model.selectedState=="UPDATE")
			$state.go('mains.users.update',{id: user.entityObject.id});
		else if(model.selectedState=="CREATE")
			$state.go('mains.users.create');
		console.log(model);
	}
	
	function deleteEntity(model) {
		user.entity.id=user.entity.username;
		httpService.deleteEntity(user.entity, USERS_SERVICE_URLS.BASE, deleteEntitySuccess, deleteEntityError);
		user.state = 'DELETE';
	}
	function deleteEntitySuccess(data,status,headers,config){
		$translate('general_success_message').then(function (rbMessage) {
        	user.responseStatus = rbMessage;
        });
		$translate('user_delete_success_message_one').then(function (rbMessage) {
        	user.statusMessage = rbMessage;
        });
		$translate('user_delete_success_message').then(function (rbMessage) {
        	user.statusMessage = user.statusMessage + data.entityId + rbMessage;
        });
		user.showButtons={create:false, update:false, deleteb:false,details:false, list:true, search:false };
	}
	
	function deleteEntityError(data,status,headers,config){
		$translate('general_failuer_message').then(function (rbMessage) {
        	user.responseStatus = rbMessage;
        });
		$translate('user_delete_failuer_message').then(function (rbMessage) {
        	user.statusMessage = rbMessage + +data.entityId;
        });
	}
	function init() {
		httpService.read(user.entityObject, USERS_SERVICE_URLS.BASE, readSuccess, readError);
	}
	function readSuccess(data,status,headers,config){
		console.log("Successfully fetched user details")
		user.entity = data;
	}
	
	function readError(data,status,headers,config){
		console.log("Fetching user details failed")
	}
	
}