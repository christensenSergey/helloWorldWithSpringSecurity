$(document).ready(function() {
	$("#newPassword").keyup(function() {
		checkPasswordStrength();
		$("#dangerAlert").hide();
	});
});

function checkPasswordStrength() {
	$.ajax({
	    type: 'POST',
	    url: '/api/checkPasswordStrength',
	    data: { 
	        'newPassword': $("#newPassword").val()
	    },
	    success: function(passwordStrengthMsg){
	    	changeProgressBar(passwordStrengthMsg);
		}
	});
}

function changeProgressBar(passwordStrength) {
	switch (passwordStrength) {
	  case "weak":
		$("#progressBar").removeClass().addClass("progress-bar bg-danger").css({'width':'33%'});
		$("#changePasswordButton").prop('disabled', false);
	    break;
	  case "normal":
		$("#progressBar").removeClass().addClass("progress-bar bg-warning").css({'width':'66%'});
		$("#changePasswordButton").prop('disabled', false);
	    break;
	  case "strong":
		$("#progressBar").removeClass().addClass("progress-bar bg-success").css({'width':'100%'});
		$("#changePasswordButton").prop('disabled', false);
	    break;
	  default:
		$("#progressBar").removeClass().addClass("progress-bar").css({'width':'0%'});
		$("#changePasswordButton").prop('disabled', true);
	}
}

function checkAndChangePassword() {
	if($("#newPassword").val() != $("#confirmPassword").val()) {
		$("#dangerAlert").text("Passwords does not match!");
		$("#dangerAlert").show();
		return;
	}
	$.ajax({
	    type: 'POST',
	    url: '/api/changePassword',
	    data: { 
	    	'login': $("#loggedUser").text(),
	        'newPassword': $("#newPassword").val()
	    },
	    success: function(msg){
	    	$("#successAlert").fadeTo(3000, 500).slideUp(500, function(){
	    	    $("#successAlert").slideUp(500);
	    	});
	    	$("#newPassword").val("");
	    	$("#newPassword").keyup();
	    	$("#confirmPassword").val("");
		}
	});
}