function alert_server_error(c) {
	swal({
			title: "Oops... API出錯!!",
			text: "我們會盡快修復，請稍待片刻",
			confirmButtonColor: "#EF5350",
			type: "error"
		},
		function (isConfirm) {
			/*$.getScript('../assets/js/api-pages/sendMail.js', function () {
				sendAlertMail(c);

			});*/

		});

}
