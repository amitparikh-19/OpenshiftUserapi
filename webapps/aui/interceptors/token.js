angular
.module('authenticationModule', [])

.factory('tokenStorage', ['$window', function($window) {

    var storageKey = "dwapi-key-";
    console.log(storageKey);

    return {
        store : function(userid,sessionInfo) {
            console.log("Storing the sessionInfo on local storage =  " + sessionInfo);
            return $window.localStorage.setItem(storageKey+userid, sessionInfo);
        },
        retrieve : function(userid) {
            console.log("Fetching the sessionInfo from local storage");
            return $window.localStorage.getItem(storageKey+userid);
        },
        clear : function(userid) {
            console.log("Removing the sessionInfo from local storage");
            return $window.localStorage.removeItem(storageKey+userid);
        }
    };
}])

.factory('userStorage', ['$window', function($window) {

    var storageKey = "dwapi-user-admin";

    return {
        store : function(userObj) {
            return $window.localStorage.setItem(storageKey, userObj);
        },
        retrieve : function() {
            return $window.localStorage.getItem(storageKey);
        },
        clear : function() {
            return $window.localStorage.removeItem(storageKey);
        }
    };
}])

.factory('tokenAuthInterceptor',
    	    ['$rootScope', '$q','tokenStorage',
    	    function($rootScope, $q, tokenStorage) {
    return {
        request : function(config) {
            var existingUserId = sessionStorage.getItem('userId');
            if (existingUserId) {
            	var token = tokenStorage.retrieve(existingUserId);
            	console.log("Setting the X-Auth-Token on the request = " + token);
                config.headers["X-AUTH-TOKEN"] = token;
            }

            return config;
        },

        response : function(req) {

            return req;
        },

        responseError : function(error) {
            console.log(error);
            	return $q.reject(error);
    }
	
};
}])


.config(function($httpProvider) {
	
	$httpProvider.defaults.headers.common["Cache-Control"] = "no-cache";
    $httpProvider.defaults.headers.common.Pragma = "no-cache";
    $httpProvider.defaults.headers.common["If-Modified-Since"] = "0";

    $httpProvider.interceptors.push('tokenAuthInterceptor');
});   
