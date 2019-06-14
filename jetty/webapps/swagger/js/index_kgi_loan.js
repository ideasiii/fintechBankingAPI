$(function () {

	let lang = localStorage.getItem('lang').toLowerCase(),
		url;

	if(lang == "ch"){
		url = "https://api.fintechspace.com.tw/swagger/airLoan-V0.1.json";
	}
	// else {
	// 	url = "https://api.fintechspace.com.tw/swagger/AcctLink_Eng.json";
	// }
	const ui = SwaggerUIBundle({
		url: url,
		validatorUrl : null,
		dom_id: '#swagger-ui',
		deepLinking: true,
		onComplete: function () {
			// Default API key
			ui.preauthorizeApiKey("APIKeyQueryParam", getkey());
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

	// let sampleCode = "";
	// sampleCode += '<a href="../../assets/kgi_file/KGI_EPGW_API_Specification_V06(AccountLink).pdf" class="btn btn-outline-info btn-lg" role="button" style="margin: 0 5px 0;">API Specification</a>';

	// $("#Btn").css("display", "flex");
	// $("#Btn").css("justify-content", "flex-start");
	// $("#Btn").append(sampleCode);
})
