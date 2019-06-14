$(function () {


	var id = localStorage.getItem("userId");
	var name = localStorage.getItem("name");
	var lang = localStorage.getItem('lang');
	//console.log(lang);

	if (id == null && name == null) {
		$('#user').hide();
		$("#sample_code").hide();
	} else {
		$('#navbar-unlogin').hide();
		$('#name').html(name);
		// $.getScript('js/tokenValidate.js');
	}
	
	if(id == '548'){
		console.log(id);
		$('.navbar-header a').html('O2O API');
	}

	getlang(lang);

	$('#myHref').on('click', function () {
		localStorage.clear();
		getlang('CH');
		location.href = "../login.html";
	});
	
	

});

function getlang(l) {
	
	if (l == null) {
		l = 'CH';
	}
	
	if (window.localStorage) {
		try {
			localStorage.setItem("lang", l);
		} catch (e) {
			alert("你處於無痕瀏覽，資料無法儲存，請跳出無痕模式");
		}
	} 

	$('#' + l).addClass('active');
	if (l == 'CH') {
		$('#EN').removeClass('active');
	} else if (l == 'EN') {
		$('#CH').removeClass('active');
	}
}