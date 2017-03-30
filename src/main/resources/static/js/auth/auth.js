$(window).on("load",function() {
	$("#inputConfirmPassword").keyup(checkPasswordMatch);
	
	function checkPasswordMatch() {
	    var password = $("#inputPassword").val();
	    var confirmPassword = $("#inputConfirmPassword").val();

	    if (password != confirmPassword)
	        $("#divCheckPasswordMatch").html("Passwords do not match!");
	    else
	        $("#divCheckPasswordMatch").html("Passwords match.");
	}
});