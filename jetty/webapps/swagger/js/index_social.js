$(function () {

	let lang = localStorage.getItem('lang').toLowerCase(),
		url;

	if (lang == "ch") {
		url = "https://social.apigoose.com/swagger";
	} else {
		url = "https://social.apigoose.com/swaggerEng";
	}
	const ui = SwaggerUIBundle({
		url: url,
		validatorUrl: null,
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
		docExpansion: "list",
		onComplete: function () {
			// Default API key
			ui.preauthorizeApiKey("api_key_header", "Bearer " + getkey());
		}

	})

	window.ui = ui
})