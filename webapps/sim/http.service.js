/**
 * @author Kopal Darbari
 */

angular
    .module('httpServiceModule', [])
    .factory('httpService', httpService);

httpService.$inject = ['$http']; 
function httpService($http) {

    var httpService = {
        create : create,
        read : read,
        readAll : readAll,
        readAllPageable : readAllPageable,
        update : update,
        deleteEntity : deleteEntity,
        
        formulateRequest : formulateRequest,
        doOperation: doOperation,
        makeRequest: makeRequest
    };

    var METHODS = {
    		  GET    : 'GET',
    		  POST   : 'POST',
    		  PUT    : 'PUT',
    		  DELETE : 'DELETE'
    		};
    
    
    // Not actually necessary, just for dev purpose 
    var baseUrl = '../';

    return httpService;

    
    /**
     * This call will create an entity object 
     *
     * @param entityObj  the obj which needs to be created
     * @param serviceUrl urlObj which will have the url list for the entity. 
     * @param successFn  the call back success function
     * @param errorFn    the call back failure function
     */
    function create(entityObj, serviceUrl, successFn, errorFn) {

        console.log("Starting to create a entity object");
        formulateRequest(entityObj,METHODS.POST, serviceUrl, successFn, errorFn)
        
    }
    
    /**
     * This call will fetch an entity object by id
     *
     * @param entityObj  the obj will have id whose details are needed to be fetched
     * @param serviceUrl urlObj which will have the url list for the entity. 
     * @param successFn  the call back success function
     * @param errorFn    the call back failure function
     */
    function read(entityObj, serviceUrl, successFn, errorFn) {

        console.log("Starting to fetch the entity object");
        formulateRequest(entityObj,METHODS.GET, serviceUrl, successFn, errorFn)
    }
    
    
    /**
     * This call will fetch the list ( non pageable )
     *
     * @param entityObj  the obj to filter to the result
     * @param serviceUrl urlObj which will have the url list for the entity. 
     * @param successFn  the call back success function
     * @param errorFn    the call back failure function
     */
    function readAll(entityObj, serviceUrl, successFn, errorFn) {

        console.log("Starting to fetch list (non pageable)");
        formulateRequest(entityObj,METHODS.GET, serviceUrl, successFn, errorFn)
    }
    
    /**
     * This call will fetch the list ( pageable )
     *
     * @param entityObj  the obj to filter to the result
     * @param serviceUrl urlObj which will have the url list for the entity. 
     * @param successFn  the call back success function
     * @param errorFn    the call back failure function
     */
    function readAllPageable(entityObj, serviceUrl, successFn, errorFn) {

        console.log("Starting to fetch list ( pageable )");
        entityObj.pageable = true;
        formulateRequest(entityObj,METHODS.GET, serviceUrl, successFn, errorFn)
    }
    
    /**
     * This call will update an existing entity object 
     *
     * @param entityObj  the updated object
     * @param serviceUrl urlObj which will have the url list for the entity. 
     * @param successFn  the call back success function
     * @param errorFn    the call back failure function
     */
    function update(entityObj, serviceUrl, successFn, errorFn) {

        console.log("Starting to update a entity object");
        formulateRequest(entityObj,METHODS.PUT, serviceUrl, successFn, errorFn)
    }
    
    
    /**
     * This call will delete an existing entity object 
     *
     * @param entityObj  the obj will have id whose details are needed to be fetched
     * @param serviceUrl urlObj which will have the url list for the entity. 
     * @param successFn  the call back success function
     * @param errorFn    the call back failure function
     */
    function deleteEntity(entityObj, serviceUrl, successFn, errorFn) {

        console.log("Starting to delete a entity object");
        formulateRequest(entityObj,METHODS.DELETE, serviceUrl, successFn, errorFn)
    }
    
    /**
     * @param successFn  the call back success function
     * @param errorFn    the call back failure function
     */
	    function formulateRequest(entityObj, httpMethod, serviceUrl, successFn, errorFn) {

		var request = {};

		request.method = httpMethod;

		switch (httpMethod) {
		case 'GET':

			if(entityObj.id)
			{
				request.url = serviceUrl + "/" + entityObj.id;
			}
			
			else if (entityObj != null || entityObj != undefined) {
				request.params = entityObj;

				if (entityObj.pageable)
					{
						request.url = serviceUrl + "/pageable";
						entityObj.pageable = undefined;
					}
				else{
						request.url = serviceUrl;
					}
			} 

			break;

		case 'POST':

			request.url  = serviceUrl;
			request.data = entityObj;

			break;

		case 'PUT':

			request.url  = serviceUrl + "/" + entityObj.id;
			request.data = entityObj;

			break;

		case 'DELETE':

			// TODO : Add Delete Headers
			request.url  = serviceUrl + "/" + entityObj.id;
			request.data = entityObj;

			break;
		}

		makeRequest(request, successFn, errorFn);
	}
    
    function doOperation(entityObj, httpMethod, serviceUrl, successFn, errorFn) {
   
		var request = {};
    	
    	request.method  = httpMethod;
    	request.url 	= serviceUrl;
		request.data 	= entityObj;
		
		makeRequest(request, successFn, errorFn);
		
    }
    
    function makeRequest(request,successFn, errorFn)
    {
    	request.url = baseUrl + request.url;
        console.log(request);
        
        request.header = {
        		"Access-Control-Allow-Origin": "*",
        };
        
        $http(request)
        .success(function(data, status, headers, config){
            successFn(data,status,headers,config);
        })
        .error(function(data, status, headers, config){
            errorFn(data,status,headers,config);
        });
    }
  
}
