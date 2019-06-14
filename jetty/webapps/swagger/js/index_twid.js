$(function () {
	const ui = SwaggerUIBundle({
		url: "https://tradedemo2.twca.com.tw/HashGwPxy/api/swagger.json",
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
