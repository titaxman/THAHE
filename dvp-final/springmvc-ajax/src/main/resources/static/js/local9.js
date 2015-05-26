// variables globales
var content;
var loading;
var erreur;

// au chargement du document
$(document).ready(function() {
	// on récupère les références des différents composants de la page
	loading = $("#loading");
	loading.hide();
	erreur = $("#erreur");
	erreur.hide();
	content = $("#content");
});

function postForm() {
	console.log("postForm");
	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-10,
		headers : {
			'Accept' : 'application/json'
		},
		type : 'POST',
		dataType : 'json',
		beforeSend : onBegin,
		success : onSuccess,
		error : onError,
		complete : onComplete
	})
}

// avant l'appel Ajax
function onBegin() {
	console.log("onBegin");
	// image d'attente
	loading.show();
}

// à réception de la réponse du serveur
// en cas de succès
function onSuccess(data) {
	console.log("onSuccess");
	// contenu
	if (data.content) {
		content.html(data.content);
	}
	// zone 1
	if (data.zone1Active) {
		$("#zone1").show();
		if (data.zone1) {
			$("#zone1-content").html(data.zone1);
		}
	} else {
		$("#zone1").hide();
	}
	// zone 3 active ?
	if (data.zone3Active) {
		$("#zone3").show();
		if (data.zone3) {
			$("#zone3-content").html(data.zone3);
		}
	} else {
		$("#zone3").hide();
	}
	// saisies ?
	if (data.saisies) {
		$("#saisies").html(data.saisies);
	}
	// erreur ?
	if (data.erreur) {
		erreur.text(data.erreur);
		erreur.show();
	} else {
		erreur.hide();
	}
}

// à réception de la réponse du serveur
// en cas d'échec
function onError(jqXHR) {
	console.log("onError");
	// erreur système
	erreur.text(jqXHR.responseText);
	erreur.show();
}

// après [onSuccess, onError]
function onComplete() {
	console.log("onComplete");
	// image d'attente
	loading.hide();
}

// validation des valeurs saisies
function valider() {
	// valeur postée
	var post = JSON3.stringify({
		"value1" : $("#text1").val().trim(),
		"value2" : $("#text2").val().trim()
	});
	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-11A',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'POST',
		data : post,
		dataType : 'json',
		beforeSend : onBegin,
		success : onSuccess,
		error : onError,
		complete : onComplete
	})
}

// retour page 1
function retourPage1() {
	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-11B',
		headers : {
			'Accept' : 'application/json',
		},
		type : 'POST',
		dataType : 'json',
		beforeSend : onBegin,
		success : onSuccess,
		error : onError,
		complete : onComplete
	})
}
