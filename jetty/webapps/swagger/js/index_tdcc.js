$(function () {
	const ui = SwaggerUIBundle({
		url: "https://gist.githubusercontent.com/thstarshine/97ae532a27061659464a1057c5526a4a/raw/214972817a90623fddd9f3cffc9dd680330ef93f/swagger.json",
		dom_id: '#swagger-ui',
		deepLinking: true,
		onComplete: function () {
			// Default API key
			ui.preauthorizeApiKey("bearerAuth", getkey());
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
