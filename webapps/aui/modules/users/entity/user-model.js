var UserModel = function () {
    // Constructor, sets the defaults values of the object
    var userModel = function (attributes) {
        var defaults = {
        		username: null,
				password: null,
				tpin: null,
				name: null,
				email: null,
				mobile: null,
				brand: null,
				type: null,
				subtype: null,
				jobtitle: null,
				level: null,
				parent: null,
				root: null,
				state: null,
				enabled: null,
				accountNonExpired: null,
				accountNonLocked: null,
				credentialsNonExpired: null,
				expires: null,
				newpassword: null,
				newtpin: null,
				authchannel: null,
				authorities: null,
				created: null,
				createdstr: null         		
        };
        _.extend(this, defaults, attributes);
    };
    
    // Class Methods
    _.extend(userModel.prototype, {
        populateData: function (data) {
            _.extend(this, data);
        } 
    });
 
    return userModel;
}();