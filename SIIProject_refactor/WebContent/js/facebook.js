var token;

function fbLogin() {
	FB.getLoginStatus(function(response) {
		  if (response.status === 'connected') {
		    console.log('Logged in.');
		  }
		  else {
			  console.log('Please log in.');
			  FB.login(function(response) {}, {scope : 'public_profile,user_friends,user_posts'});
		  }
		  token = response.authResponse.accessToken;
		  document.getElementById('myToken').innerHTML = "Il tuo token è: " + token;
		  document.getElementById('getTokenBtn').setAttribute("hidden", "hidden");
		  document.getElementById('accessBtn').removeAttribute("hidden");
		});
}

function fbLogout() {
	FB.getLoginStatus(function(response) {
		if (response.status === 'connected') {
			console.log('Logged out');
			FB.logout();
		}
		else {
			console.log('You are already logged out');
		}
	});
}