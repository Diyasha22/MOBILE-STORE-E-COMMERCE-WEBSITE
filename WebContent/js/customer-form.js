$(document).ready(function(){
		
		$("#customerForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				fullname: "required",
				password: "required",
				confirmPassword: {
					required:true,
					equalTo: "#password"
				},
				phone: "required",
				address: "required",
				city: "required",
				zipCode: "required",
				country: "required"
			},
			
			messages: {
				email: {
					required: "Please enter e-mail address",
					email: "Please enter a valid e-mail address"
				},
				fullname: "Please enter full name",
				password: "Please enter password",
				confirmPassword: {
					required: "Please confirm password",
					equalTo: "Passwords don't match"
				},
				phone: "Please enter phone number",
				address: "Please enter address",
				city: "Please enter city",
				zipCode: "Please enter zip code",
				country: "Please enter country"
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
	    });
		
	});