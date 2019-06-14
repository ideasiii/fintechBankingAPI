$(function(){

	var lang = localStorage.getItem("lang");
	var opts = { language: lang, pathPrefix: "../assets/locales"};  
    $("[data-localize]").localize("more", opts);
	
});