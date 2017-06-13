angular
.module('statModule', [])
.constant('STAT_SERVICE_URLS', {
	BASE : 'demoinit/data'
});
angular
    .module('statModule')
    .controller('statController', statController);

statController.$inject = ['$scope','$state','$stateParams','$translate','httpService','STAT_SERVICE_URLS'];

function statController( $scope,$state,$stateParams, $translate, httpService, STAT_SERVICE_URLS){
	
	var stat = this;
	
	// Properties
	stat.entityObject={};
	stat.responseStatus=null;
	
	stat.methodCallback = methodCallback;
	stat.cleanup = cleanup;
	stat.initTopup = initTopup;
	stat.initDm = initDm;
	stat.init = init;
	
	function methodCallback(method) {
		if (method.methodName=="Clean Up")
			stat.cleanup();
		if (method.methodName=="Init Topup")
			stat.initTopup();
		if (method.methodName=="Init DM")
			stat.initDm();
	}
	
	function cleanup() {
		stat.cleanupUrl= STAT_SERVICE_URLS.BASE+"/cleanup";
		httpService.doOperation(null,'GET',stat.cleanupUrl, cleanupSuccess, cleanupError);
	}
	
	function cleanupSuccess(data,status,headers,config){
		if(data==true){
			stat.responseStatus = "System has been cleaned up successfully";
		}
		else
			stat.responseStatus = "There has been some error while cleaning up the System";
	}
	
	function cleanupError(data,status,headers,config){
	}
	
	function initTopup() {
		stat.initTopupUrl= STAT_SERVICE_URLS.BASE+"/inittopup";
		httpService.doOperation(null,'GET', stat.initTopupUrl, initTopupSuccess, initTopupError);
	}
	function initTopupSuccess(data,status,headers,config){
		stat.responseStatus = null;
		stat.users = data;
	}
	
	function initTopupError(data,status,headers,config){

	}
	
	function initDm() {
		stat.initDmUrl= STAT_SERVICE_URLS.BASE+"/initdm";
		httpService.doOperation(null,'GET', stat.initDmUrl, initDmSuccess, initDmError);
	}
	
	function initDmSuccess(data,status,headers,config){
		stat.responseStatus = null;
		stat.users = data;
	}
	
	function initDmError(data,status,headers,config){

	}
	
	$scope.$on('initBalance', function(event, msg) {
		  init();
	});
	
	function init() {
		httpService.read("", STAT_SERVICE_URLS.BASE+"/balance", readSuccess, readError);
	}
	
	function readSuccess(data,status,headers,config){
		console.log("Successfully fetched user details with balance")
		if(data.length!=0)
			stat.users = data;
		else
			stat.responseStatus = "No users found in DB"
	}
	
	function readError(data,status,headers,config){
		console.log("Fetching user details with balance failed")
	}
	
}
