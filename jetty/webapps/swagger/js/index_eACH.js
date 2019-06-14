$(function () {
	let lang = localStorage.getItem('lang').toLowerCase(),
		url;

	if(lang == "ch"){
		url = "https://api.fintechspace.com.tw/ctbc_swagger/index.php?getSwagger=Other-eACH";
	}else {
		url = "https://api.fintechspace.com.tw/ctbc_swagger/index.php?getESwagger=Other-eACH";
	}
	const ui = SwaggerUIBundle({
		url: url,
		validatorUrl : null,
		dom_id: '#swagger-ui',
		deepLinking: true,
		onComplete: function () {
			// Default API key
			ui.preauthorizeApiKey("APIKeyHeader", "Bearer " + getkey());
		},
		presets: [
					SwaggerUIBundle.presets.apis,
					SwaggerUIStandalonePreset
				],
		plugins: [
					SwaggerUIBundle.plugins.DownloadUrl,
					HideTopbarPlugin,
					HideInfoUrlPlugin
				],
		layout: "StandaloneLayout",
		docExpansion: "list"
		
	})

	window.ui = ui
})

