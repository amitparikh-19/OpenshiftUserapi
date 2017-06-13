
angular
    .module('usersModule')
    .controller('usersListController', usersListController);

usersListController.$inject = ['$scope','$state','$translate','userStorage','httpService', 'USERS_SERVICE_URLS'];

function usersListController( $scope,$state, $translate, userStorage, httpService, USERS_SERVICE_URLS ){
	
	var user = this;

	// Properties
	user.filters = {};
	user.entityObject={};
	user.entity={};
	user.state = 'LIST';
	user.routeState = $state.current;
	user.showButtons={create:true, update:true, deleteb:true,details:true, list:false, search:true };
	
	user.tid = null;
	user.responseStatus = "NONE";
	user.sortField = null;
	user.sortDir = 'ASC';
	user.statusMessage = "";
	user.filters.firstresult = 0;
	user.filters.maxresult = 10;
	user.entityObject.id=null;
	user.list = null;
	
	// Methods
	user.init = init;
	user.stateChange = stateChange;
	user.deleteEntity = deleteEntity;
	user.changePage = changePage;
	user.selectedRow = selectedRow;
	user.search = search;
	user.sort = sort;

	function deleteEntity(model) {
		httpService.deleteEntity(user.entityObject, USERS_SERVICE_URLS.BASE, deleteEntitySuccess, deleteEntityError);
	}

	function deleteEntitySuccess(data,status,headers,config){
		$translate('general_success_message').then(function (rbMessage) {
        	users.responseStatus = rbMessage;
        });
		$translate('user_delete_success_message_one').then(function (rbMessage) {
        	users.statusMessage = rbMessage;
        });
		$translate('user_delete_success_message').then(function (rbMessage) {
        	users.statusMessage = users.statusMessage + data.entityId + rbMessage;
        });
		user.init();
	}
	
	function deleteEntityError(data,status,headers,config){
		$translate('general_failuer_message').then(function (rbMessage) {
        	users.responseStatus = rbMessage;
        });
		$translate('user_delete_failuer_message').then(function (rbMessage) {
        	users.statusMessage = rbMessage + data.entityId;
        });
	}

	
	function stateChange(model) {
		
		if (model.targetId==null & (model.selectedState=="UPDATE" || model.selectedState=="DETAILS" || model.selectedState=="DELETE"))
			{
			user.responseStatus = "FAILURE";
			user.statusMessage = "A record must be selected inorder to perform this operation.";
	        return;
	        }
		
		if(model.selectedState=="CREATE")
			{$state.go('mains.users.create');}
		else if(model.selectedState=="UPDATE")
			$state.go('mains.users.update' , {id: user.entityObject.id});
		else if(model.selectedState=="DETAILS")
			$state.go('mains.users.details' , {id: user.entityObject.id});
		else if(model.selectedState=="DELETE")
	        $("#myModal").modal({backdrop: true});
		else if (model.selectedState=="SEARCH")
			{
				$scope.operation = 'search';
				$("#searchModal").modal({backdrop: true});
			}
	}
	
	function changePage(pageable) {
		if(pageable=='next')
			user.filters.firstresult = user.list.number+1;
		else
			user.filters.firstresult = user.list.number-1;
		user.filters.maxresult = 10;
		init();
	}
	
	function search() {
		user.filters.entity = user.entity;
		httpService.readAllPageable(user.filters, USERS_SERVICE_URLS.BASE, readAllPageableSuccess, readAllPageableError);
	}
	
	function sort(field)
	{
		
		user.sortField = field;
		user.filters.firstresult = 0;
		
		if(user.sortDir == 'ASC')
		user.sortDir = 'DESC';
		else
		user.sortDir = 'ASC';	
		
		user.filters.sortfield = user.sortField;
		user.filters.sortdir = user.sortDir;

		httpService.readAllPageable(user.filters, USERS_SERVICE_URLS.BASE, readAllPageableSuccess, readAllPageableError);
	}
	
	function init() {
		httpService.readAllPageable(user.filters, USERS_SERVICE_URLS.BASE, readAllPageableSuccess, readAllPageableError);
	}
	
	function readAllPageableSuccess(data,status,headers,config){
		console.log("Successfully fetched list for users")
		user.list = data;
	}
	
	function readAllPageableError(data,status,headers,config){
		console.log("Fetching list for users failed")
	}
	
	function selectedRow(userRec) {
		if($("#row-"+userRec.username).hasClass('selected'))
		{
			   user.entityObject = {};
			   user.tid=null;
		       $("#row-"+userRec.username).removeClass('selected');
		       
		}
		else
		{
		       $("#row-"+userRec.username).addClass('selected').siblings().removeClass('selected');
		       user.entityObject.id=userRec.username;
			   user.tid=user.entityObject.id;
		}
		
		clearMessage();
	}
	
	function clearMessage()
	{
		user.responseStatus = "NONE";
		user.statusMessage = null;
	}
	
}
