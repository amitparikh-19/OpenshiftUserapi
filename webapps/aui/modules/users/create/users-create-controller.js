angular
    .module('usersModule')
    .controller('usersCreateController', usersCreateController);

usersCreateController.$inject = ['$state','$translate','userStorage','httpService','USERS_SERVICE_URLS'];

function usersCreateController( $state, $translate, userStorage,httpService,USERS_SERVICE_URLS){
	
	var user = this;
	
	// Properties
	user.entity=new UserModel();
	user.step='step1';
	user.routeState = $state.current;
	
	// Methods
	user.init = init;
	user.createUser=createUser;
	user.stepChange = stepChange;
	user.responseStatus = "NONE";
	user.statusMessage = null;
	user.reset=reset;
	user.cancel=cancel;
	function init() {
	}
	
	function createUser(){
		user.responseStatus = "NONE";
		user.statusMessage = null;
		httpService.create(user.entity, USERS_SERVICE_URLS.BASE, createSuccess, createError);
        
	}
	function cancel(){
		
		$state.go('mains.users.list');
	}
	function reset(){
		user.entity=new UserModel();
	} 
	function createSuccess(data,status,headers,config){
		user.entity = data;
		$translate('general_success_message').then(function (rbMessage) {
        	user.responseStatus = rbMessage;
        });
		$translate('user_create_success_message').then(function (rbMessage) {
        	user.statusMessage = rbMessage;
        });
		console.log("Successfully created the user")
	}
	
	function createError(data,status,headers,config){
		$translate('general_failuer_message').then(function (rbMessage) {
        	user.responseStatus = rbMessage;
        });
		$translate('user_create_failuer_message').then(function (rbMessage) {
        	user.statusMessage = rbMessage;
        });
		console.log("user creation failed")
	}
	
	function stepChange(step) {
		user.step=step;
	}
}