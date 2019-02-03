function sendRegistrationRequest() {
	if($("#newPassword").val() != $("#confirmPassword").val()) {
		$("#dangerAlert").text("Passwords does not match!");
		$("#dangerAlert").show();
		return;
	}
	$.ajax({
	    type: 'POST',
	    url: '/api/registration',
	    data: { 
	    	'login': $("#login").val(),
	        'newPassword': $("#newPassword").val()
	    },
	    success: function(msg){
	    	if(msg == "Success") {
	    		$("#dangerAlert").hide();
		    	$("#successAlert").fadeTo(3000, 500).slideUp(500, function(){
		    	    $("#successAlert").slideUp(500);
		    	});
		    	$("#login").val("");
		    	$("#newPassword").val("");
		    	$("#confirmPassword").val("");
	    	} else {
	    		$("#dangerAlert").text(msg);
				$("#dangerAlert").show();
	    	}
		}
	});
}