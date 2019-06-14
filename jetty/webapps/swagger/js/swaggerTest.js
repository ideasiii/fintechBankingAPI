$(function () {
	var id = localStorage.getItem("userId");
	var name = localStorage.getItem("name");
	if (id == null && name == null) location.href = '../login.html';
	var url = "";
	var lang = localStorage.getItem('lang').toLowerCase();
	if(sessionStorage.getItem("SwaggerUrl") !== null){
		url = sessionStorage.getItem("SwaggerUrl");
	}else {
		url = "https://ser.kong.srm.pw/dashboard/swagger.json";
	}

	// Build a system
	const ui = SwaggerUIBundle({
		url: url,
		dom_id: '#swagger-ui',
		deepLinking: true,
		presets: [
					SwaggerUIBundle.presets.apis,
					SwaggerUIStandalonePreset
				],
		plugins: [
					SwaggerUIBundle.plugins.DownloadUrl
				],
		layout: "StandaloneLayout"
	})
	
	window.ui = ui
	$(".download-url-button").click(function(){
		sessionStorage.setItem('SwaggerUrl', $(".download-url-input").val());
	});
})
