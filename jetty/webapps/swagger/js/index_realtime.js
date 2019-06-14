$(function () {
	
	const ui = SwaggerUIBundle({
		url: "https://chat.fugle.tw/files/gtfwgmz1b78ctbym6rspbxexje/public?h=2fzCruGVFxv4YvetevBtoDXH-Y7ccziC9UipReKi3P4",
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

	let sampleCode = "";
	sampleCode += '<a href="../../assets/sampleCode/III-stock-chart-api_sample1.py" class="btn btn-outline-primary btn-lg" role="button" style="margin: 0 5px 0;">Sample Code 1</a>';
	sampleCode += '<a href="../../assets/sampleCode/III-stock-chart-api_sample2.py" class="btn btn-outline-primary btn-lg" role="button">Sample Code 2</a>';

	$("#Btn").css("display", "flex");
	$("#Btn").css("justify-content", "flex-start");
	$("#Btn").append(sampleCode);

})

/*
$(function () {
	var url = window.location.search.match(/url=([^&]+)/);
	if (url && url.length > 1) {
		url = decodeURIComponent(url[1]);
	} else {
		url = "https://ser.kong.srm.pw/dashboard/swagger.json";
	}

	hljs.configure({
		highlightSizeThreshold: 5000
	});

	// Pre load translate...
	if (window.SwaggerTranslator) {
		window.SwaggerTranslator.translate();
	}
	window.swaggerUi = new SwaggerUi({
		url: url,
		dom_id: "swagger-ui-container",
		supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
		onComplete: function (swaggerApi, swaggerUi) {
			if (typeof initOAuth == "function") {
				initOAuth({
					clientId: "your-client-id",
					clientSecret: "your-client-secret-if-required",
					realm: "your-realms",
					appName: "your-app-name",
					scopeSeparator: " ",
					additionalQueryStringParams: {}
				});
			}

			if (window.SwaggerTranslator) {
				window.SwaggerTranslator.translate();
			}
		},
		onFailure: function (data) {
			log("Unable to Load SwaggerUI");
		},
		docExpansion: "list",
		jsonEditor: false,
		defaultModelRendering: 'schema',
		showRequestHeaders: false
	});

	window.swaggerUi.load();

	function log() {
		if ('console' in window) {
			console.log.apply(console, arguments);
		}
	}
});
*/