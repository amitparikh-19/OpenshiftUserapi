angular
    .module('accountsModule')
    .controller('accountDetailsController', accountDetailsController);

accountDetailsController.$inject = ['$state','$translate','userStorage'];

function accountDetailsController( $state, $translate, userStorage){
	
	var accountDetails = this;
	
	accountDetails.user = null;
	
	// Methods
	accountDetails.init = init;
	
	function init() {
		accountDetails.user = JSON.parse(userStorage.retrieve());
		console.log(accountDetails.user);
	}
	
}