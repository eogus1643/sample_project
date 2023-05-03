/**
* progressBar
*/
var opts = {
	lines: 17, // The number of lines to draw
	length: 25, // The length of each line
	width: 8, // The line thickness
	radius: 22, // The radius of the inner circle
	scale: 1, 
	corners: 1, // Corner roundness (0..1)
	rotate: 0, // The rotation offset
	direction: 1, // 1: clockwise, -1: counterclockwise
	color: '#39c', // #rgb or #rrggbb or array of colors
	speed: 2.2, // Rounds per second
	trail: 46, // Afterglow percentage
	shadow: false, // Whether to render a shadow
	hwaccel: false, // Whether to use hardware acceleration
	className: 'spinner', // The CSS class to assign to the spinner
	zIndex: 2e9, // The z-index (defaults to 2000000000)
	top: '35%', // Top position relative to parent
	left: '50%' // Left position relative to parent
};
var spinner = new Spinner(opts).spin($(".commonProgressBarDiv")[0]);
function progressShow(){
	$(".commonProgressBarDiv").show();
	spinner.spin($(".commonProgressBarDiv")[0]);
}
function progressHide(){
	$(".commonProgressBarDiv").hide();
	spinner.stop();
}
/**
* FORM 중복 SUBMIT 방지
*/
//$(document).on("submit", function(){
//	progressShow();
//});


/**
* Ajax 공통 모듈
*/
function commAjax(url, data, success){
	$.ajax({
		url				: url
		, type			: "post"
		, data			: data
		, dataType		: "json"
		, contentType	: "application/json; charset=utf-8"
		, beforeSend	: function(req){ 
								// progressShow();
								createUpdateDefault('wrap');
							}
		, success		: success
		, error			: function(xhr, errorName, error) {
			if(xhr.status == "403"){
				//commAlert("세션이 만료되었습니다.<br/>재로그인 후 다시 시도해주세요.", "url", "/mydata/pass/home");
				commAlert("세션이 만료되었습니다.<br/>재로그인 후 다시 시도해주세요.", "fn", "closeMydata");//TODO Pass HOME 으로 변경
			} else {
				commAlert(xhr.responseJSON.err_msg);
			}
			return;
		},complete 		: function() {
	        // progressHide();
	        destroyUpdate('wrap');
    	}
    });
}

/**
 * layer 알림창
 * msg : 메시지
 * type : url - redirectUrl
 * 		  fn - callback 함수
 * parma : 콜백함수 파라미터
 */
function commAlert(msg, type, callback, param){
	$("#alertLayerPop #alertLayerPop-type").val(type);
	$("#alertLayerPop #alertLayerPop-callback").val(callback);
	$("#alertLayerPop #alertLayerPop-param").html(param);
	$("#alertLayerPop .layer-popup__title").html(msg);
	
	popStart();
	$('#alertLayerPop').show();
}
function alertPopupClose(){
	var type = $("#alertLayerPop #alertLayerPop-type").val();
	var callback = $("#alertLayerPop #alertLayerPop-callback").val();
	var param = $("#alertLayerPop #alertLayerPop-param").html();
	popEnd();
	$('#alertLayerPop').hide();

	if(type == "url"){
		location.href = callback;
	} else if(type == "fn"){
		eval(callback+"(param)");
	}

	return;
}

/** 
* 입력값이 NULL인지 체크(undefined, 공백 포함)
*/ 
function isNull(input) { 
	if (input.value == null || input.value == "" || typeof input.value === 'undefined' || input.value.replace(/ /gi,"") == "") { 
		return true; 
	} 
	return false; 
} 

/** 
* 입력값에 특정 문자(chars)가 있는지 체크 
* 특정 문자를 허용하지 않으려 할 때 사용 
* ex) if (containsChars(form.name,"!,*&^%$#@~;")) { 
* alert!("이름 필드에는 특수 문자를 사용할 수 없습니다."); 
* } 
*/ 
function containsChars(input,chars) { 
	for (var inx = 0; inx < input.value.length; inx++) { 
		if (chars.indexOf(input.value.charAt(inx)) != -1){
			return true; 
		}
	} 
	return false; 
} 

/** 
* 입력값이 특정 문자(chars)만으로 되어있는지 체크 
* 특정 문자만 허용하려 할 때 사용 
* ex) if (!containsCharsOnly(form.blood,"ABO")) { 
* alert!("혈액형 필드에는 A,B,O 문자만 사용할 수 있습니다."); 
* } 
*/ 
function containsCharsOnly(input,chars) { 
	for (var inx = 0; inx < input.value.length; inx++) { 
		if (chars.indexOf(input.value.charAt(inx)) == -1){
			return false; 
		} 
	} 
	return true; 
} 

/** 
* 입력값이 알파벳인지 체크 
* 아래 isAlphabet() 부터 isNumComma()까지의 메소드가 
* 자주 쓰이는 경우에는 var chars 변수를 
* global 변수로 선언하고 사용하도록 한다. 
* ex) var uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
* var lowercase = "abcdefghijklmnopqrstuvwxyz"; 
* var number = "0123456789"; 
* function isAlphaNum(input) { 
* var chars = uppercase + lowercase + number; 
* return containsCharsOnly(input,chars); 
* } 
*/ 
function isAlphabet(input) { 
	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; 
	return containsCharsOnly(input,chars); 
} 

/** 
* 입력값이 알파벳 대문자인지 체크 
*/ 
function isUpperCase(input) { 
	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	return containsCharsOnly(input,chars); 
} 

/** 
* 입력값이 알파벳 소문자인지 체크 
*/ 
function isLowerCase(input) { 
	var chars = "abcdefghijklmnopqrstuvwxyz"; 
	return containsCharsOnly(input,chars); 
} 

/** 
* 입력값에 숫자만 있는지 체크 
*/ 
function isNumber(input) { 
	var chars = "0123456789"; 
	return containsCharsOnly(input,chars); 
} 

/** 
* 입력값이 알파벳,숫자로 되어있는지 체크 
*/ 
function isAlphaNum(input) { 
	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
	return containsCharsOnly(input,chars); 
} 

/** 
* 입력값이 숫자,대시(-)로 되어있는지 체크 
*/ 
function isNumDash(input) { 
	var chars = "-0123456789"; 
	return containsCharsOnly(input,chars); 
} 

/** 
* 입력값이 숫자,콤마(,)로 되어있는지 체크 
*/ 
function isNumComma(input) { 
	var chars = ",0123456789"; 
	return containsCharsOnly(input,chars); 
} 

/** 
* 입력값이 사용자가 정의한 포맷 형식인지 체크 
* 자세한 format 형식은 자바스크립트의 'regular expression!'을 참조 
*/ 
function isValidFormat(input,format) { 
	if (input.value.search(format) != -1) { 
		return true; //올바른 포맷 형식 
	} 
	return false; 
} 

/** 
* 입력값이 이메일 형식인지 체크 
* ex) if (!isValidEmail(form.email)) { 
* alert!("올바른 이메일 주소가 아닙니다."); 
* } 
*/ 
function isValidEmail(input) { 
	var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/; 
	return isValidFormat(input,format); 
} 

/** 
* 입력값이 전화번호 형식(숫자-숫자-숫자)인지 체크 
*/ 
function isValidPhone(input) { 
	var format = /^(\d+)-(\d+)-(\d+)$/; 
	return isValidFormat(input,format); 
} 

/** 
* 입력값의 바이트 길이를 리턴 
* ex) if (getByteLength(form.title) > 100) { 
* alert!("제목은 한글 50자(영문 100자) 이상 입력할 수 없습니다."); 
* } 
* Author : Wonyoung Lee 
*/ 
function getByteLength(input) { 
	var byteLength = 0; 
	for (var inx = 0; inx < input.value.length; inx++) { 
		var oneChar = escape(input.value.charAt(inx)); 
		if ( oneChar.length == 1 ) { 
			byteLength ++; 
		} else if (oneChar.indexOf("%u") != -1) { 
			byteLength += 2; 
		} else if (oneChar.indexOf("%") != -1) { 
			byteLength += oneChar.length/3; 
		} 
	} 
	return byteLength; 
} 

/** 
* 입력값에서 콤마를 없앤다. 
*/ 
function removeComma(input) { 
	return input.value.replace(/,/gi,""); 
} 

/** 
* 선택된 라디오버튼이 있는지 체크 
*/ 
function hasCheckedRadio(input) { 
	if (input.length > 1) { 
		for (var inx = 0; inx < input.length; inx++) { 
			if (input[inx].checked){
				return true; 
			} 
		} 
	} else { 
		if (input.checked) {
			return true; 
		}
	} 
	return false; 
} 

/** 
* 선택된 체크박스가 있는지 체크 
*/ 
function hasCheckedBox(input) { 
	return hasCheckedRadio(input); 
}

//토스트 메시지
function toast(string) {
	let removeToast;
	
    const toast = document.getElementById("toast");

    toast.classList.contains("reveal") ?
        (clearTimeout(removeToast), removeToast = setTimeout(function () {
            document.getElementById("toast").classList.remove("reveal")
        }, 2000)) :
        removeToast = setTimeout(function () {
            document.getElementById("toast").classList.remove("reveal")
        }, 2000)
    toast.classList.add("reveal"),
        toast.innerText = string
} 


//오늘 날짜를 문자열로 반환
function today() {
  var d = new Date();
  return getDateToStr(d);
}

//일주일 후 날짜를 문자열로 반환
function oneWeek() {
  var d = new Date();
  d.setDate(d.getDate() + 7);
  return getDateToStr(d);
}

//날짜 객체 받아서 문자열로 리턴
function getDateToStr(date){
	var year = date.getFullYear();
	var month = (date.getMonth() + 1);
	var day = date.getDate();
	
	month = (month < 10) ? "0" + String(month) : month;
	day = (day < 10) ? "0" + String(day) : day;
	
	return  year + '-' + month + '-' + day;
}

//년도계산 (+,-)
function yearCal(dt, n) {
	dt.setFullYear(dt.getFullYear() + n);
	return dt;
}
//월계산 (+,-)
function monthCal(dt, n) {
	dt.setMonth(dt.getMonth() + n);
	return dt;
}
//일계산 (+,-)
function dayCal(dt, n) {
	dt.setDate(dt.getDate() + n);
	return dt;
}

//배열 빈값 체크 로직
function isEmpty(val){
	if(val === '' || val === null || val === undefined 
    	|| (val !== null && typeof(val) === 'object' && !Object.keys(val).length)){
       	return true;
    } else{
    	return false;
    }
}

//배열 빈값 체크 로직
function isEmptyValue(val){
	if(val === '' || val === null || val === undefined 
    	|| (val !== null && typeof(val) === 'object' && !Object.keys(val).length)){
       	return '';
    } else{
    	return val;
    }
}

// 세자리 콤마
function moneyComma (val) {
	var resultval = val.toString(); 
	return resultval.replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
}

//length의 길이 만큼 글자를 자른다.
function chunkString(str, length) {
	return str.match(new RegExp('.{1,' + length + '}', 'g'));// 정규식으로 length의 길이만 큼 자라서 배열로 return 합니다.
}

//현재 시간구하기(HHMM)
function nowTime() {
	var date = new Date();
	
	var hours = date.getHours();
	var minutes = date.getMinutes();
	
	hours = (hours < 10) ? "0" + String(hours) : hours;
	minutes = (minutes < 10) ? "0" + String(minutes) : minutes;
	
	return String(hours) + String(minutes);
}

function closeMydata(){
	try {
		SpCommon.closeWebview(function(){}, function(){});
	} catch(e){
		console.log(e);
	}
}

function mod(val, zoomLevel) {
	
	/*
	var value = 0;
	switch (zoomLevel) {
		case 20 : value = 1; break;
		case 19 : value = 2; break;
		case 18 : value = 3; break;
		case 17 : value = 4; break;
		case 16 : value = 5; break;
		case 15 : value = 6; break;
		case 14 : value = 7; break;
		case 13 : value = 8; break;
		case 12 : value = 9; break;
		case 11 : value = 10; break;
		case 10 : value = 11; break;
		case 9 : value = 12; break;
		case 8 : value = 13; break;
		case 7 : value = 14; break;
		case 6 : value = 15; break;
		default : value = 0; break;
	}
	*/
	
	return val % 100;
	
}