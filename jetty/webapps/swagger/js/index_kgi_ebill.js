$(function () {
	let lang = localStorage.getItem('lang').toLowerCase(),
		url;

	if(lang == "ch"){
		url = "https://api.fintechspace.com.tw/swagger/eBill-V1.1.json";
	}else {
		url = "https://api.fintechspace.com.tw/swagger/eBill_Eng.json";
	}
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

	let sampleCode = "",
		noticText = "";
	sampleCode += '<a href="../../assets/sampleCode/awsSign_sample.py" class="btn btn-outline-primary btn-lg" role="button" style="margin: 0 5px 0;">Sample Code 1</a>';
	sampleCode += '<a href="../../assets/kgi_file/KGI_繳費管理平台-繳費網頁_外部界接作業規格v1.61.pdf" class="btn btn-outline-info btn-lg" role="button" style="margin: 0 5px 0;">外部界接作業規格</a>';
	sampleCode += '<a href="../../assets/kgi_file/技術測試注意事項(含AWS Signing範例).docx" class="btn btn-outline-info btn-lg" role="button" style="margin: 0 5px 0;">AWS-Signing申請說明書</a>';

	$("#Btn").css("display", "flex");
	$("#Btn").css("justify-content", "flex-start");
	$("#Btn").append(sampleCode);

	noticText += '<div class="row"><div class="col-md-12">';
	// noticText += '<h5><span class="icon-notification2"></span> 取得 Authorize Token : 帳戶設定 > 複製 Query Token</h5>';
	noticText += '<h5><span class="icon-notification2"></span> 此 API 無法使用 Swagger 做測試，請下載 Sample Code 進行測試</h5>'
	noticText += '</div></div>';
	$("#notic").css("display", "block");
	$("#notic").append(noticText);
})

