/**
 * Authentication Login Model.js
 **/

var Authentication = function () {
    // Constructor, sets the defaults values of the object
    var authentication = function (attributes) {
        var defaults = {
        		username:null,
        		password:null,
        		authchannel:null
        		
        };
        _.extend(this, defaults, attributes);
    };
    
    // Class Methods
    _.extend(authentication.prototype, { 
        isNew: function () {
            return (this.id === null);
        }, 
        getId: function() {
            return this.id.toString();
        },
        /**
         * Useful method to parse server response and populate the fields.
         */
        populateData: function (data) {
            _.extend(this, data);
        } 
    });
 
    return authentication;
}();