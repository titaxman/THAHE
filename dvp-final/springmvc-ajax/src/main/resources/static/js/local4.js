// données globales
var loading;
var formulaire;
var résultats;
var entete;
var titre;
var labelHeureCalcul;
var heureCalcul;
var aplusb;
var amoinsb;
var afoisb;
var adivb;
var erreur;
var msgErreur;

function postForm() {
	// formulaire valide ?
	if (!formulaire.validate().form()) {
		// formulaire invalide - terminé
		return;
	}
	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-05',
		headers : {
			'Accept' : 'application/json'
		},
		type : 'POST',
		data : formulaire.serialize(),
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
	// on montre l'image animée
	loading.show();
	// on cache certains éléments
	entete.hide();
	résultats.hide();
	erreur.hide();
}

// à réception de la réponse du serveur
// en cas de succès
function onSuccess(data) {
	console.log("onSuccess");
	// on remplit la zone des résultats
	titre.text(data.titre);
	labelHeureCalcul.text(data.labelHeureCalcul);
	heureCalcul.text(data.heureCalcul);
	entete.show();
	// résultats sans erreur
	if (!data.msgErreur) {
		aplusb.text(data.aplusb);
		amoinsb.text(data.amoinsb);
		afoisb.text(data.afoisb);
		adivb.text(data.adivb);
		résultats.show();
		return;
	}
	// résultats avec erreur
	msgErreur.text(data.msgErreur);
	erreur.show();
}

// à réception de la réponse du serveur
// en cas d'échec
function onError(jqXHR) {
	console.log("onError");
	// erreur système
	msgErreur.text(jqXHR.responseText);
	erreur.show();
}

// après [onSuccess, onError]
function onComplete() {
	console.log("onComplete");
	// on cache l'image animée
	loading.hide();
}

// au chargement du document
$(document).ready(function() {
	// on récupère les références des différents composants de la page
	loading = $("#loading");
	formulaire = $("#formulaire");
	résultats = $("#résultats");
	entete = $("#entete");
	titre = $("#titre");
	labelHeureCalcul = $("#labelHeureCalcul");
	heureCalcul = $("#heureCalcul");
	aplusb = $("#aplusb");
	amoinsb = $("#amoinsb");
	afoisb = $("#afoisb");
	adivb = $("#adivb");
	erreur = $("#erreur");
	msgErreur = $("#msgErreur");
	// on cache certains éléments
	entete.hide();
	résultats.hide();
	erreur.hide();
	loading.hide();
});
