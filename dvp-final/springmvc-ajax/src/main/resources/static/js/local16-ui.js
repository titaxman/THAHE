// variables globales
var content;
var loading;
var erreur;
var page1;
var page2;
var value1;
var value2;

// au chargement du document
$(document).ready(function() {
	// on récupère les références des différents composants de la page
	loading = $("#loading");
	loading.hide();
	erreur = $("#erreur");
	erreur.hide();
	content = $("#content");
});

// update Page 1
function postForm() {
	// on met à jour la page 1
	var deferred = $.Deferred();
	loading.show();
	updatePage1(deferred, {
		'sender' : "postForm",
		'info' : 10
	});
	// affichage résultats
	deferred.done(postFormDone);
}

function postFormDone(result) {
	// fin attente
	loading.hide();
	// on récupère les données
	var data = result.data
	// pour démo
	console.log(JSON3.stringify(result.sendMeBack));
	// on analyse le status
	switch (result.status) {
	case 1:
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
		break;
	case 2:
		// affichage erreur
		erreur.html(data);
		break;
	}
}

// validation valeurs saisies
function valider() {
	// on mémorise la page 1
	page1 = content.html();
	// on mémorise les valeurs saisies
	value1 = $("#text1").val().trim();
	value2 = $("#text2").val().trim();
	// pas d'erreur
	erreur.hide();
	// on demande la page 2
	var deferred = $.Deferred();
	loading.show();
	getPage2(deferred, {
		'sender' : 'valider',
		'info' : 20
	}, value1, value2, page2 ? false : true);
	// affichage résultats
	deferred.done(validerDone);
}

function validerDone(result) {
	// fin attente
	loading.hide();
	// on récupère les données
	var data = result.data
	// pour démo
	console.log(JSON3.stringify(result.sendMeBack));
	// on analyse le status
	switch (result.status) {
	case 1:
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
		break;
	case 2:
		// affichage erreur
		erreur.html(data);
		erreur.show();
		break;
	}
}

// retour page 1
function retourPage1() {
	// on régénère la page 1
	content.html(page1);
	// on régénère les saisies
	$("#text1").val(value1);
	$("#text2").val(value2);
}
