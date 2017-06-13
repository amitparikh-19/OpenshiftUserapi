/**
 * WalTxnModel.js
 **/
var WalTxn = function () {
    // Constructor, sets the defaults values of the object
    var waltxn = function (attributes) {
        var defaults = {
        		txnid: null,
				parenttxnid: null,
				wsp: null,
				adsp: null,
				odsp: null,
				channel: null,
				ipaddress: null,
				reqsrc: null,
				txntype: null,
				product: null,
				srctype: null,
				srcid: null,
				txnorigsrc: null,
				parentsrcid: null,
				rootsrcid: null,
				srcdeviceid: null,
				odspsubsid: null,
				odspsubsaccnt: null,
				desttype: null,
				destid: null,
				parentdestid: null,
				rootdestid: null,
				amount: null,
				fee: null,
				acommission: null,
				tax: null,
				srcwalbal: null,
				destwalbal: null,
				odspsubsbal: null,
				odspwspbal: null,
				currency: null,
				camount: null,
				wspcommission: null,
				ocrate: null,
				ccurrency: null,
				status: null,
				txndatetime: null,
				txndatetimestr: null,
				ptxndatetime: null,
				ptxndatetimestr: null,
				paysrc: null,
				apayref: null,
				opayref: null,
				agenttxnid: null,
				adspref: null,
				odspref: null,
				comment: null,
				created: null,
				createdstr: null      		
        }
        _.extend(this, defaults, attributes);
    };
    
    // Class Methods
    _.extend(waltxn.prototype, {
        populateData: function (data) {
            _.extend(this, data);
        } 
    });
 
    return waltxn;
}();