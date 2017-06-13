angular
    .module('accountsModule')
    .controller('userLoginController', userLoginController);

userLoginController.$inject = ['$scope','$state','$translate','tokenStorage','userStorage','ACCOUNTS_SERVICE_URLS','httpService'];

function userLoginController($scope, $state, $translate,tokenStorage,userStorage,ACCOUNTS_SERVICE_URLS,httpService){

var loginForm = this;

// Properties 
loginForm.user = new Authentication();
loginForm.isLoading = false;
loginForm.statusMessage = null;
loginForm.responseStatus = 'NONE';

// Methods
loginForm.login = login;

function login(user) {
		httpService.doOperation(user,'POST', ACCOUNTS_SERVICE_URLS.LOGIN, loginSuccess, loginFailure);
	};
	
	function loginSuccess(data,status,headers,config){
		
		var token = headers()["x-auth-token"];
		var tokenArr = token.split(".");
		
		var loggedUser = JSON.parse(atob(tokenArr[0]));
		var authToken = tokenArr[1];

		sessionStorage.setItem('userId', loggedUser.username);
		
		tokenStorage.store(loggedUser.username,authToken);
		userStorage.store(JSON.stringify(loggedUser));

		$state.go('mains.accounts');
		loginForm.isLoading = false;
	}

	function loginFailure(data,status,headers,config){
		console.log("failed to login")
		loginForm.isLoading = false;
		if(status == '403') {
			loginForm.statusMessage = 'Invalid Userid / Password';
			loginForm.responseStatus = 'FAILURE';
		}
	}
	
}