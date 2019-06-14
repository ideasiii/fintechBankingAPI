$(function () {

	let lang = localStorage.getItem('lang').toLowerCase(),
		url;

	if (lang == "ch") {
		url = "https://api.tej.com.tw/fintechspace_tejapi_ch.json";
	} else {
		url = "https://api.tej.com.tw/fintechspace_tejapi_en.json";
	}

	const ui = SwaggerUIBundle({
		url: url,
		dom_id: '#swagger-ui',
		deepLinking: true,
		onComplete: function () {
			// Default API key
			ui.preauthorizeApiKey("APIMKeyAuth", "Bearer " + getkey());
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


	// window.ui = ui

	// let scenario = "";
	// scenario += '<a href="../../assets/scenarioFile/infotime情境範例.pdf" class="btn btn-outline-primary btn-lg" role="button" style="margin: 0 5px 0;">API 情境範例</a>';

	// $("#Btn").css("display", "flex");
	// $("#Btn").css("justify-content", "flex-start");
	// $("#Btn").append(scenario);


})