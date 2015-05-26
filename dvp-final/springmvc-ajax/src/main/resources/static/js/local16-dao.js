var session = {
	"cpt1" : 0,
	"cpt3" : 0
};

// update Page 1
function updatePage1(deferred, sendMeBack) {
	// requête HTTP
	executePost(deferred, sendMeBack, '/ajax-13', session);
}

// page 2
function getPage2(deferred, sendMeBack, value1, value2, pageRequired) {
	// requête HTTP
	executePost(deferred, sendMeBack, '/ajax-14', {
		"value1" : value1,
		"value2" : value2,
		"pageRequired" : pageRequired,
	});
}

// requête HTTP
function executePost(deferred, sendMeBack, url, post) {
	// on fait un appel Ajax à la main
	$.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		url : url,
		type : 'POST',
		data : JSON3.stringify(post),
		dataType : 'json',
		success : function(data) {
			// on mémorise la session
			if (data.session) {
				session = data.session;
			}
			// on rend le résultat
			deferred.resolve({
				"status" : 1,
				"data" : data,
				"sendMeBack" : sendMeBack
			});
		},
		error : function(jqXHR) {
			// on rend l'erreur
			deferred.resolve({
				"status" : 2,
				"data" : jqXHR.responseText,
				"sendMeBack" : sendMeBack
			});
		}
	});
}
