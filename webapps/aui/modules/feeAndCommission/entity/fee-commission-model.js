/**
 * FeeCommission.js
 **/
var FeeCommission = function () {
    // Constructor, sets the defaults values of the object
    var feeCommission = function (attributes) {
        var defaults = {
				wsp: null,
				chargetype: null,
				txntype: null,
				chargedtotype: null,
				payer: null,
				beneficiarytype: null,
				fixedcharge: null,
				mincharge: null,
				maxcharge: null,
				percentcharge: null,
				startdatetime: null,
				startdatetimestr: null,
				enddatetime: null,
				enddatetimestr: null,
				comment: null,
				id: null,
				created: null,
				createdstr: null      		
        }
        _.extend(this, defaults, attributes);
    };
    
    // Class Methods
    _.extend(feeCommission.prototype, {
        populateData: function (data) {
            _.extend(this, data);
        } 
    });
 
    return feeCommission;
}();