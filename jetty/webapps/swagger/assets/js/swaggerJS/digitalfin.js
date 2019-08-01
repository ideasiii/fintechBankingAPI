$(function () {
	
	url = "https://www.fintechersapi.com/swaggerJson/digitalfin_v4.json";
	
	const ui = SwaggerUIBundle({
		url: url,
		validatorUrl : null,
		dom_id: '#swagger-ui',
		deepLinking: true,
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

