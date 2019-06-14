$(function () {

	var strUrl = location.search;
	var getSpecies = strUrl.split("?");
	var id = localStorage.getItem("userId"),
		group = localStorage.getItem("groupId");
	
	// if(id == null && group == null){
	// 	window.location.replace("../../index.html");
	// }


	$.get("main_navbar.html?v1", function (data) {
		$('#main_navbar').html(data);
	});

	$('#navbar').load('navbar.html?v1.11', function () {

		
		//navbar li active
		$('#apidocDrop .active').removeClass('active');
		if (getSpecies[1] == undefined) {
			$('#index_realtime').addClass('active');
		}
		$('#' + getSpecies[1]).addClass('active');
		
		//Breadcrumb
		var breadStr = $('#apidocDrop .active a').attr('data-localize'),
			breadStr_span = $('#apidocDrop .active span').attr('data-localize');
		if (breadStr != undefined) {
			$('#breadcrumb').append('<li>API Doc</li><li data-localize="second_navbar.apidoc.' + breadStr.split('.')[2] + '.title"></li><li data-localize="' + breadStr + '"></li>');
		} else if( breadStr_span != undefined ) {
			$('#breadcrumb').append('<li>API Doc</li><li data-localize="' + breadStr_span + '"></li>');
		}else if (getSpecies[1] == "swaggerTest"){
			$('#breadcrumb').append('<li>API Doc</li><li data-localize="second_navbar.apidoc.test"></li>');
		} else {
			$('#breadcrumb').append('<li>API Doc</li><li data-localize="second_navbar.apidoc.payment.title"></li><li data-localize="second_navbar.apidoc.payment.kgi_acclink"></li>');
		}

	});

	$.get("footer.html?v2", function (data) {
		$('#footer').html(data);
	});

	if (getSpecies[1] != undefined) {

		$.getScript("js/" + getSpecies[1] + ".js");

	} else {
		
		$.getScript("js/index_realtime.js");
		
	}


	if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
		if (window.innerHeight > window.innerWidth) {
			swal("請轉置橫向!", "為維護您的觀看品質", "warning");
		}

	}

	var ua = window.navigator.userAgent;
	var msie = ua.indexOf("MSIE ");

	if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) // If Internet Explorer, return version number
	{

		swal({
				title: "此網站不提供IE版本",
				text: "確保您的使用品質，請使用Chrome或Firefox",
				type: "warning",
				confirmButtonColor: "#ea693a"
			},
			function (isConfirm) {
				window.open('', '_self', '');
				window.close();
			});
	}
});
