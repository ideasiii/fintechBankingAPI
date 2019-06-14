/*jslint browser: true*/
/*global $, jQuery, alert, console*/

var timer;
function refresh() {
	'use strict';
	var id = localStorage.getItem('userId'),
		refreshToken = localStorage.getItem('refreshToken'),
		token = localStorage.getItem('accessToken');


	$.ajax({
		url: 'https://' + domain + '/fintech/token/refresh',
		type: "POST",
		contentType: "application/json",
		dataType: 'json',
		data: JSON.stringify({
			'userId': id,
			'value': refreshToken
		}),
		success: function (msg) {
			
			localStorage.setItem("accessToken", msg.accessToken);
			localStorage.setItem("refreshToken", msg.refreshToken);
			localStorage.setItem("startTime", new Date().getTime());
			location.reload();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);

		}
	});
}

function logout() {
	
	localStorage.clear();
	location.href = '../login.html';

}

function timeout() {
	'use strict';
	timer = setTimeout(function () {
		//refresh();
		logout();
		timeout();
	}, 5400000);


}
timeout();

$(function () {
	var startTime = localStorage.getItem("startTime");
	var now = new Date();
	var Date_C = new Date(now.getTime() - startTime);
	var diff = Math.floor(Date_C.getTime() / 3600000);
	
	
	if (diff > 12) {
		logout();
	}
	
	$('body').on('mouseenter', function(){
		
		clearTimeout(timer);
		timeout();
	});
});
