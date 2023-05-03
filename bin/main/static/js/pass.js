var SpPass = {
    transaction : function(certTxId, reqTxId, success, failure) {
        var param = {};
        param['certTxId'] = certTxId;
        param['reqTxId'] = reqTxId;
​
        var pSuccess = function () {
          success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpPassPlugin", "transaction", param, pSuccess, pFailure);
    },
    mydataSigned : function(certTxId, success, failure) {
        var param = {};
        param['certTxId'] = certTxId;
​
        var pSuccess = function (result) {
          success(result['result'])
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpPassPlugin", "mydataSigned", param, pSuccess, pFailure);
    }
};