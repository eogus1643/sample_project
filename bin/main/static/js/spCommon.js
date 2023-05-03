var SpCommon = {
    closeWebview : function(success, failure) {
        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpCommonPlugin", "closeWebview", {}, pSuccess, pFailure);
    },
    loadUrlforBrowser : function(url, success, failure) {
        var param = {};
        param['url'] = url;
        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpCommonPlugin", "loadUrlforBrowser", param, pSuccess, pFailure);
    },
    loadUrl : function(url, success, failure) {
        var param = {};
        param['url'] = url;
        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpCommonPlugin", "loadUrl", param, pSuccess, pFailure);
    },
    changeTitle : function(title, hiddenBack, hiddenClose, success, failure) {
        var param = {};
        param['title'] = title;
        param['hiddenBack'] = hiddenBack;
        param['hiddenClose'] = hiddenClose;

        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpCommonPlugin", "changeTitle", param, pSuccess, pFailure);
    },
    loadSubWebview : function(url, type, hiddenBack, hiddenClose, title, success, failure) {
        var param = {};
        param['url'] = url;
        param['type'] = type;
        param['hiddenBack'] = hiddenBack;
        param['hiddenClose'] = hiddenClose;
        param['title'] = title;

        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpCommonPlugin", "loadSubWebview", param, pSuccess, pFailure);
    },
    callApp : function(url, pkg, storeId ,success, failure) {
        var param = {};
        param['deeplink'] = url;
        param['package'] = pkg;
        param['storeId'] = storeId;

        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        SpPlugin.callPlugin("SpCommonPlugin", "callApp", param, pSuccess, pFailure);
    },
     downloadFile : function(url, fileName, fileExtension ,success, failure) {
        var param = {};
        param['url'] = url;
        param['fileName'] = fileName;
        param['fileExtension'] = fileExtension;

        var pSuccess = function (result) {
            success();
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        return SpPlugin.callPlugin("SpCommonPlugin", "downloadFile", param, pSuccess, pFailure);
    },
    loadingProgressBar : function(isShow ,success, failure) {
        var param = {};
        param['isShow'] = isShow;

        var pSuccess = function (result) {
            success()
        }
        var pFailure = function (result) {
            failure(result['message'])
        }
        return SpPlugin.callPlugin("SpCommonPlugin", "loadingProgressBar", param, pSuccess, pFailure);
    }
};
