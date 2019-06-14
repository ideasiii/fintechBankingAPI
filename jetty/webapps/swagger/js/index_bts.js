$(function () {
	let lang = localStorage.getItem('lang').toLowerCase(),
		url;

	if(lang == "ch"){
		url = "https://btsapim.azure-api.net/BTS_API_CH/test/apiDoc?language=CH&subscription-key=a2f379bf1eed48b39e6daf8ea6b4dcda";
	}else {
		url = "https://btsapim.azure-api.net/BTS_API_CH/test/apiDoc?language=EN&subscription-key=a2f379bf1eed48b39e6daf8ea6b4dcda";
	}
	const ui = SwaggerUIBundle({
		url: url,
		validatorUrl : null,
		dom_id: '#swagger-ui',
		deepLinking: true,
		onComplete: function () {
			// Default API key
			ui.preauthorizeApiKey("APIKeyHeaderParam", "Bearer " + getkey());
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

