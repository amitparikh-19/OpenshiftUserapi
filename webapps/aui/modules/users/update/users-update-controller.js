angular
    .module('usersModule')
    .controller('usersUpdateController', usersUpdateController);

usersUpdateController.$inject = ['$state','$stateParams','$translate','userStorage','httpService','USERS_SERVICE_URLS'];

function usersUpdateController( $state,$stateParams,$translate, userStorage,httpService,USERS_SERVICE_URLS){
	
	var user = this;
	
	// Properties
	user.entityObject={};
	user.entityObject.id=$stateParams.id;
	user.state = 'UPDATE';
	user.tid = '1';
	user.step='step1';
	user.routeState = $state.current;
	
	// Methods
	user.init = init;
	user.updateUser=updateUser;
	user.stepChange = stepChange;
	user.responseStatus = "NONE";
	user.statusMessage = null;
	user.reset=reset;
	user.cancel=cancel;
	function init() {
		
			httpService.read(user.entityObject, USERS_SERVICE_URLS.BASE, readSuccess, readError);
				
	}
	function readSuccess(data,status,headers,config){
		console.log("Successfully fetched user details")
		user.entity = data;
		user.entity.id = data.username;
	}
	
	function readError(data,status,headers,config){
		console.log("Fetching user details failed")
	}
	
	function updateUser(){
		user.responseStatus = "NONE";
		user.statusMessage = null;
		httpService.update(user.entity, USERS_SERVICE_URLS.BASE, updateSuccess, updateError);

	}
	
	function cancel(){
		$state.go('mains.users.list');
	}
	
	function reset(){
		user.entity = null;
	} 
	
	function updateSuccess(data,status,headers,config){
		console.log("Successfully created the user")
		$translate('general_success_message').then(function (rbMessage) {
        	user.responseStatus = rbMessage;
        });
		$translate('user_update_success_message').then(function (rbMessage) {
        	user.statusMessage = rbMessage;
        });
	}
	
	function updateError(data,status,headers,config){
		console.log("user creation failed")
		$translate('general_failuer_message').then(function (rbMessage) {
        	user.responseStatus = rbMessage;
        });
		$translate('general_failuer_message').then(function (rbMessage) {
        	user.statusMessage = rbMessage;
        });
	}
	
	function stepChange(step) {
		
		user.step=step;
	}
}
