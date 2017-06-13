angular
    .module('accountsModule')
    .controller('userLogoutController', userLogoutController);

userLogoutController.$inject = ['$scope','$state','$translate','tokenStorage','userStorage'];

function userLogoutController($scope, $state, $translate,tokenStorage,userStorage){

	var logout = this;
	//properties
	logout.userid=sessionStorage.getItem('userId');
	
	tokenStorage.clear(logout.userid);
	userStorage.clear();
	sessionStorage.removeItem('userId');
}