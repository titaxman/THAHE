// variables globales
var content;
var loading;
var erreur;
var page1;
var page2;
var value1;
var value2;
var session = {
		"cpt1" : 0,
		"cpt3" : 0
	};

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
	// on poste la session
	var post = JSON3.stringify(session);
	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-13',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'POST',
		data : post,
		dataType : 'json',
		beforeSend : onBegin,
		success : function(data) {
			// on mémorise la session
			session = data.session;
			// on met à jour les deux zones
			if (data.zone1) {
				$("#zone1-content").html(data.zone1);
				$("#zone1").show();
			} else {
				$("#zone1").hide();
			}
			if (data.zone3) {
				$("#zone3").show();
				$("#zone3-content").html(data.zone3);
			} else {
				$("#zone3").hide();
			}
		},
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
	// on mémorise la page 1
	page1 = content.html();
	// on mémorise les valeurs saisies
	value1 = $("#text1").val().trim();
	value2 = $("#text2").val().trim();
	// valeur postée
	var post = JSON3.stringify({
		"value1" : value1,
		"value2" : value2,
		"pageRequired" : page2 ? false : true
	});
	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-14',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'POST',
		data : post,
		dataType : 'json',
		beforeSend : onBegin,
		success : function(data) {
			// erreur ?
			if (data.erreur) {
				// affichage erreur
				erreur.html(data.erreur);
				erreur.show();
			} else {
				// pas d'erreur
				erreur.hide();
				// page 2
				if (page2) {
					// on utilise la page en cache
					content.html(page2);
				} else {
					// on mémorise la page 2
					page2 = data.page2;
					// on l'affiche
					content.html(data.page2);
				}
				// on la met à jour avec les infos du serveur
				$("#value1").text(data.value1);
				$("#value2").text(data.value2);
			}
		},
		error : onError,
		complete : onComplete
	})
}

// retour page 1
function retourPage1() {
	// on régénère la page 1
	content.html(page1);
	// on régénère les saisies
	$("#text1").val(value1);
	$("#text2").val(value2);
}
