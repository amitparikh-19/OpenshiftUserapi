/**
 * Authentication Login Model.js
 **/

var Accounts = function () {
    // Constructor, sets the defaults values of the object
    var accounts = function (attributes) {
        var defaults = {
        		email:null,
        		mobileno:null,
        		notes:null
        		
        };
        _.extend(this, defaults, attributes);
    };
    
    // Class Methods
    _.extend(accounts.prototype, { 
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
 
    return accounts;
}();