var SpMyData = {
    goNativeView : function(viewType, extraParam, parentCloseYn, success, failure){
        // 웹에서 네이티브 페이지 호출
        var param = {};
        param['viewType'] = viewType;  // 이동 해야 될 view type
        param['extraParam'] = extraParam;
        param['parentCloseYn'] = parentCloseYn;
 
        var pSuccess = function(result){
            success()
        }
        var pFailure = function(result){
            failure(result['message'])
        }
        return SpPlugin.callPlugin("SpMyDataPlugin", "goNativeView", param, pSuccess, pFailure);
    },
    getProperty: function(key, success, failure){
        var param = {};
        param['key'] = key;
        var pSuccess = function(result){
             success(result['key'], result['value'])
         }
         var pFailure = function(result){
             failure(result['message'])
         }
         
         return SpPlugin.callPlugin("SpMyDataPlugin", "getProperty", param, pSuccess, pFailure);
     },
    setProperty: function(key, value ,success, failure){
        var param = {};
        param['key'] = key;
        param['value'] = value;
        var pSuccess = function(result){
             success()
         }
         var pFailure = function(result){
             failure(result['message'])
         }
         return SpPlugin.callPlugin("SpMyDataPlugin", "setProperty", param, pSuccess, pFailure);
     },
     showToast : function(message ,duration ,success, failure) {
        var param = {};
        param['message'] = message;
        param['duration'] = duration;
        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        return SpPlugin.callPlugin("SpMyDataPlugin", "showToast", param, pSuccess, pFailure);
    },
    clearHistory : function(success, failure) {
        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        return SpPlugin.callPlugin("SpMyDataPlugin", "clearHistory", {}, pSuccess, pFailure);
    },
    requestSignature : function(auth,success, failure) {
        var param = {};
        param['auth'] = auth;
        var pSuccess = function (result) {
            success(result['value'])
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        return SpPlugin.callPlugin("SpMyDataPlugin", "requestSignature", param, pSuccess, pFailure);
    },
    setBackPressed : function(use, onBackPressed, failure) {
        var param = {};
        param['use'] = use;  // use = 'Y' 일때, callback 등록, 'N'일때 callback 등록해제
        var pCallback = function() {
            onBackPressed()
        }

        var pFailure = function() {
            failure(result['message'])
        }

        return SpPlugin.callPlugin("SpMyDataPlugin", "setBackPressed", param, pCallback, pFailure);
    },
    authPin : function(success, failure) {
        var pSuccess = function(result) {
            success(result['success'])
        }

        var pFailure = function() {
            failure(result['message'])
        }

        return SpPlugin.callPlugin("SpMyDataPlugin", "authPin", {}, pSuccess, pFailure);
    },
    goNativeWebView : function(viewType, extraParam, success, failure){
        var param = {};
        param['viewType'] = viewType;
        param['extraParam'] = extraParam;
 
        var pSuccess = function(result){
            success()
        }
        var pFailure = function(result){
            failure(result['message'])
        }
        return SpPlugin.callPlugin("SpMyDataPlugin", "goNativeWebView", param, pSuccess, pFailure);
    },
};