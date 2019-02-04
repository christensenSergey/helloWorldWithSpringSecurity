$(document).ready(function() {
    var results = new RegExp('[\?&]' + 'error' + '=([^&#]*)').exec(window.location.href);
    if (results!=null) {
    	$("#dangerAlert").show();
    }
});