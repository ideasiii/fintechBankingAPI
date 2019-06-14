$(function () {
	const ui = SwaggerUIBundle({
		url: "https://tsaapi.azurewebsites.net/openapi/Swagger.json",
		// url: "https://tsaapit.azurewebsites.net/openapi/Swagger.json",
		dom_id: '#swagger-ui',
		deepLinking: true,
		onComplete: function () {
			// Default API key
			ui.preauthorizeApiKey("APIKeyHeaderParam", getkey());
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

	let scenario = "";
	scenario += '<a href="../../assets/scenarioFile/tatung情境範例.pdf" class="btn btn-outline-primary btn-lg" role="button" style="margin: 0 5px 0;">API 情境範例</a>';

	$("#Btn").css("display", "flex");
	$("#Btn").css("justify-content", "flex-start");
	$("#Btn").append(scenario);

})
