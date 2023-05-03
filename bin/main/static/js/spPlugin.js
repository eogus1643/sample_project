var SpPlugin = {
  index: 0,
  success_callback: [],
  failure_callback: [],

  //Android, iOS webview Interface 호출
  callPlugin: function (pluginName, funcName, param, success, failure) {
    //Android, iOS Webview UserAgent 값 가져오기
    var userAgent = navigator.userAgent;
    //userAgent = 'initial (os:iOS, appVersion:{appVersion}, osVersion:{osVersion})';
    //userAgent = 'initial (os:Android, appVersion:{appVersion}, osVersion:{osVersion})';

    if (userAgent.indexOf("mwp") > -1 && userAgent.indexOf("os:iOS") > 0) {
      var callbackId = String(new Date().getTime()) + String(this.index++)
      this.success_callback[callbackId] = success;
      this.failure_callback[callbackId] = failure;
      var str = JSON.stringify(param);
      var encode = btoa(unescape(encodeURIComponent(str)))
        
      var message = JSON.stringify({
        funcName: funcName,
        callbackId: callbackId,
        param: encode,
      });
        
      new Function('window.webkit.messageHandlers.'+pluginName+'.postMessage(\''+message+'\')')();

    } else if (userAgent.indexOf("mwp") > -1 && userAgent.indexOf("os:Android") > 0) {
      var callbackId = String(new Date().getTime()) + String(this.index++)
      this.success_callback[callbackId] = success;
      this.failure_callback[callbackId] = failure;
      var str = JSON.stringify(param);
      var encode = btoa(unescape(encodeURIComponent(str)))
      
        var message = JSON.stringify({
        funcName: funcName,
        param: encode,
        callbackId: callbackId,
      });
      new Function(pluginName+'.postMessage(\''+message+'\')')();

    } else if (userAgent.indexOf("mwp") === -1) {
      return true;
    }
    else {
      alert("잘못된 접근이거나 올바른 요청이 아닙니다.");
    }
  },
  success: function (callbackId, param) {
    var str = decodeURIComponent(escape(atob(param)))
    const obj = JSON.parse(str)
    SpPlugin.success_callback[callbackId](obj);
  },
  failure: function (callbackId, param) {
    var str = decodeURIComponent(escape(atob(param)))
    const obj = JSON.parse(str)
    SpPlugin.failure_callback[callbackId](obj);
  },
};
