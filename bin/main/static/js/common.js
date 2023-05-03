$(document).ready(function(){


	


});



//팝업 공통
function popStart(){
	var scrolltop = $(window).scrollTop();
	$('<div class="body_temp" />')
	.append($('#wrap')
	.contents())
	.css({
		'position':'fixed',
		'top':'-'+(scrolltop) + 'px',
		'width':'100%',
		'max-width':'720px',
		'margin':'0 auto'
	})
	.appendTo('#wrap');
};

function popEnd(){
	var bodyTemp = $('.body_temp');
	var scrolltop = Math.abs(bodyTemp.position().top);   
	$('#wrap').append(bodyTemp.contents());
	bodyTemp.remove();        
	$('html, body').scrollTop(scrolltop);
};


//레이어팝업
function popupOpen(obj) {
	popStart();
	$('#'+obj).show();
}

function popupClose(obj) {
	popEnd();
	$('#'+obj).hide();
}


//하단 팝업
function botLyrOpen(obj){
	popStart();
	$('#'+obj).before('<div id="popbg"></div>');
	$('#popbg').fadeIn();
	$('#'+obj).animate({bottom:'0'},500);
};

function botLyrClose(obj){
	popEnd();
	$('#popbg').fadeOut('normal', function() {
        $(this).remove();
    });
	$('#'+obj).animate({bottom:'-100%'},500);
};

$(document).on('click','#popbg',function(){
	popEnd();
	$(this).next().animate({bottom:'-100%'},500);
	$(this).fadeOut('normal', function() {
        $(this).remove();
    });
});



//클립복사
function clipCopy(){
	$('#wrap').append();
	$('#clipCopy').fadeIn('fast');
	setTimeout(function() {
		$('#clipCopy').fadeOut('fast');
	}, 3000);
};



//나의자산 상단고정
function fixedTop(){
	var fixTop = $('.bank-detail__top').outerHeight();
	var fixHide = $('.bank-detail__top--hide').outerHeight();
	$('#contents').css({'margin-top':(fixTop-24)});

	$(window).scroll(function(){
		if($(this).scrollTop() > 0) {
			//console.log($(this).scrollTop());
			$('.bank-detail__top--hide').slideUp(150);
			$('.bank-detail__top').addClass('scroll');
			$('#contents').css({'margin-top':(fixTop-fixHide-24)});
		} else {
			$('.bank-detail__top--hide').slideDown(150);
			$('.bank-detail__top').removeClass('scroll');
			$('#contents').css({'margin-top':(fixTop-24)});
		};
	});
};


//자산상세보기
function assetDetail(){
	$('.bank-detail__info--button').click(function(){
		if($(this).is('.on')) {
			$(this).removeClass('on');
			$('.bank-detail__info--content').slideUp('fast');
			$('html, body').animate({scrollTop:0}, '300');
		}else {
			$(this).addClass('on');
			$('.bank-detail__info--content').slideDown('fast');
			$('html, body').animate({scrollTop:0}, '300');
		}
	});	
};


//텍스트박스 리셋
function inputReset(obj){
	$('.'+obj).val('');
};


//숫자만 입력
$('.onlyNumber').on('keyup', function() {
	$(this).val($(this).val().replace(/[^0-9]/g,""));
});




//재무진단 - 요약정보 (아코디언 메뉴)
$('.fp-summary__accordion--button').click(function(){
	if($(this).is('.on')) {
		$(this).removeClass('on');
		$('.fp-summary__accordion--contents').slideUp('fast');
	}else {
		$(this).addClass('on');
		$('.fp-summary__accordion--contents').slideDown('fast');
	}
});




//재무진단 - 스와이프 메뉴
function fpTab(num) {
	var $frame = $('.sly');
	var $wrap  = $frame.parent();
	$('.sly li').each(function(){
		var width= $(this).outerWidth();
		//console.log(width);
		$(this).css({'width':(width+1)});
	});
	$frame.sly({
		horizontal: 1,
		itemNav: 'basic',
		smart: 1,
		activateOn: 'click',
		mouseDragging: 1,
		touchDragging: 1,
		releaseSwing: 1,
		startAt: num-1, //포커싱
		speed: 300,
		elasticBounds: 1,
		easing: 'easeOutExpo', 
		clickBar: 1
	});
};




//재무진단 - 스크롤이벤트
function fpScroll(){

	var didScroll;
	var lastScrollTop = 0;
	var delta = 5;
	var headerH = $('.header').outerHeight(); //header height
	

	$(window).scroll(function(event){
		didScroll = true;
	});

	setInterval(function() {
		if (didScroll) {
			hasScrolled();
			didScroll = false;
		}
	}, 250);

	function hasScrolled() {
		var st = $(this).scrollTop();
		var summaryH = $('.fp-summary').outerHeight() + 24; //summary height + padding-top
		var lineH = $('.fp__line').outerHeight() + 40; //line height + margin-top
		var scrollH = summaryH + lineH;
		var menuH = $('.fp-sub__menu').outerHeight(); //menu height
		
		if(Math.abs(lastScrollTop - st) <= delta)
			return;
		
		if (st > lastScrollTop && st > headerH){
			// Scroll Down
			//console.log('down');
			//console.log('gnb : ' + headerH + '/ smry : ' + summaryH + '/ line : ' + lineH + '/ scroll : ' + scrollH);

			$('.header').removeClass('up').addClass('down');

			if($(window).scrollTop() >= (scrollH-2)){ // break
				//console.log($(window).scrollTop());
				$('.header').css({'position':'absolute', 'top':scrollH});
				$('.fp-scroll__contents').css({'padding-top':menuH});
				$('.fp-sub__menu').addClass('absolute');
			};
			if ($(window).scrollTop() >= (scrollH+menuH-6)){ // break
				$('.fp-sub__menu').removeClass('absolute');
				$('.fp-sub__menu').addClass('fixed');
			};

		} else {
			// Scroll Up
			//console.log('up');
			//console.log('gnb : ' + headerH + '/ smry : ' + summaryH + '/ line : ' + lineH + '/ scroll : ' + scrollH);

			if(st + $(window).height() < $(document).height()) {
				$('.header').removeClass('down').addClass('up');
				if($(window).scrollTop() <= scrollH+8){ // break
					$('.header').css({'position':'fixed', 'top':0});
					$('.fp-sub__menu').removeClass('absolute');
				};
				if ($(window).scrollTop() <= (scrollH+menuH+2)){ // break
					$('.fp-scroll__contents').css({'padding-top':0});
					$('.fp-sub__menu').removeClass('fixed');
				};
			}
		}
		
		lastScrollTop = st;
	}

};





//재무진단 결과 - 스크롤이벤트
function fpScroll2(){

	var didScroll;
	var lastScrollTop = 0;
	var delta = 5;
	var headerH = $('.header').outerHeight(); //header height
	

	$(window).scroll(function(event){
		didScroll = true;
	});

	setInterval(function() {
		if (didScroll) {
			hasScrolled();
			didScroll = false;
		}
	}, 250);

	function hasScrolled() {
		var st = $(this).scrollTop();
		var summaryH = $('.fp-result__summary').outerHeight(); //summary height
		var importH = $('.fp-result__import').outerHeight(); //import height
		var scrollH = summaryH + importH - 48;
		
		if(Math.abs(lastScrollTop - st) <= delta)
			return;
		
		if (st > lastScrollTop && st > headerH){
			// Scroll Down
			$('.header').removeClass('up').addClass('down');
			if($(window).scrollTop() >= (scrollH)){ // break
				$('.header').css({'position':'absolute', 'top':scrollH});
			};
			if ($(window).scrollTop() >= (scrollH + 56)){ // break
				$('.fp-result__spy').removeClass('absolute');
				$('.fp-result__spy').addClass('fixed');
			};

		} else {
			// Scroll Up
			if(st + $(window).height() < $(document).height()) {
				$('.header').removeClass('down').addClass('up');
				if($(window).scrollTop() <= scrollH){ // break
					$('.header').css({'position':'fixed', 'top':0});
				};
				if ($(window).scrollTop() <= (scrollH + 56)){ // break
					$('.fp-scroll__contents').css({'padding-top':0});
					$('.fp-result__spy').removeClass('fixed');
				};
			}
		}
		
		lastScrollTop = st;
	}

};



//피드 - 스크롤이벤트
function fdScroll(){

	var didScroll;
	var lastScrollTop = 0;
	var delta = 5;
	var headerH = $('.header').outerHeight(); //header height
	$('.header').css({'position':'absolute'});
	$('#contents__inner').css({'padding-top':'8rem'});

	$(window).scroll(function(event){
		didScroll = true;
	});

	setInterval(function() {
		if (didScroll) {
			hasScrolled();
			didScroll = false;
		}
	}, 250);

	function hasScrolled() {
		var st = $(this).scrollTop();
		var scrollH = headerH;
		
		if(Math.abs(lastScrollTop - st) <= delta)
			return;
		
		if (st > lastScrollTop && st > headerH){
			// Scroll Down
			if ($(window).scrollTop() >= (scrollH)){ // break
				$('.fd__tab').addClass('fixed');
			};

		} else {
			// Scroll Up
			if(st + $(window).height() < $(document).height()) {
				if ($(window).scrollTop() <= (scrollH)){ // break
					$('.fd__tab').removeClass('fixed');
				};
			}
		}
		
		lastScrollTop = st;
	}

};




//전송요구 설정관리 탭
function sdmTab(obj){
	if (obj == 'all'){
		$('.sdm_list li').show();
	} else {
		$('.sdm_list li').hide();
		$('.sdm_list li.'+obj).show();
	};
};



/* 업데이트 레이어 생성
_sectionID : 업데이트 레이어 생성 위치. ID로 구분지어 사용.
_targetClass : 별도의 update 레이어 생성 위치 설정. 없을 시 sectionID 위치에 생성.
*/
function createUpdate(_sectionID,_targetClass) {
	var html = '';
		html += '<div class="home__update">';
		html += '	<div class="update__spinner"></div>';
		html += '	<div class="update__text">';
		html += '		<strong>업데이트중</strong>';
		html += '	</div>';
		html += '</div>';

	if(_targetClass != undefined){
		$('#'+_sectionID+' .'+_targetClass).append(html);
	}else{ 
		$('#'+_sectionID).append(html);
	}

	$('#'+_sectionID).attr('data-update','yes');
}

function createUpdateDefault(_sectionID,_targetClass) {
	var html = '';
		html += '<div class="home__update">';
		if ('/mydata/pass/home' == $(location).attr('pathname') ) {
			html += '	<div class="update__spinner" style="top:30%;"></div>';	
		} else {
			html += '	<div class="update__spinner"></div>';
		}
		html += '</div>';

	if(_targetClass != undefined){
		$('#'+_sectionID+' .'+_targetClass).append(html);
	}else{ 
		$('#'+_sectionID).append(html);
	}

	$('#'+_sectionID).attr('data-update','yes');
}

function destroyUpdate(_sectionID){
	$('#'+_sectionID+' .home__update').remove();
	$('#'+_sectionID).removeAttr('data-update');
}


