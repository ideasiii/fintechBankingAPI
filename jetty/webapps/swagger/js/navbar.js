$(function () {

	var id = localStorage.getItem("userId");
	var name = localStorage.getItem("name");
	var group = localStorage.getItem("groupId");
	var owner = localStorage.getItem("isOwner");


	if (id == null && name == null) {
		$('#api_flow').hide();
		$('#user_admin').hide();
		$('#user_owner').hide();
		$('#swaggerTest').hide();
		$('#api_permission').hide();
	}

	if (group == 14 && owner != "true") {
		$('#api_flow').hide();
		$('#user_admin').hide();
		$('#user_owner').hide();
		$('#swaggerTest').hide();
		$('#api_permission').hide();
	} else if (group == 14 && owner == "true") {
		$('#api_flow').hide();
		$('#user_admin').hide();
		$('#swaggerTest').hide();
		$('#api_permission').hide();
	} else if (group == 13) {
		$('#user_owner').hide();
	}
	// if (owner == "true") {
	// 	$('#swaggerTest').show();
	// }

	$('.disabled a').attr("href", "javascript:void(0)");

	//special id
	if (id == '391') {
		$("#apidocDrop li:not(:first-child)").hide();
	} else if (id == '548') {
		for (var i = 3; i < 7; i++) {
			$("#apidocDrop > li:eq(" + i + ")").hide();
		}

	}
});


$('.dropdown-menu li a').click(function () {
	$('#apidocDrop .active').removeClass('active');
	$('.dropdown-menu li .active').removeClass('active');
	$(this).closest('li').addClass('active');
});