// données globales
var loading;
var formulaire;
var résultats;
var a, b;

function postForm() {
	// formulaire valide ?
	if (!formulaire.validate().form()) {
		// formulaire invalide - terminé
		return;
	}
	// on gère deux locales [fr_FR, en_US]
	// les réels [a,b] doivent être postés au format anglo-saxon dans tous les cas
	// ils le seront par le filtre [CultureFilter]

	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-02',
		headers : {
			'X-Requested-With' : 'XMLHttpRequest'
		},
		type : 'POST',
		data : formulaire.serialize(),
		dataType : 'html',
		beforeSend : function() {
			loading.show();
		},
		success : function(data) {
			resultats.html(data);
		},
		complete : function() {
			loading.hide();
		},
		error : function(jqXHR) {
			résultats.html(jqXHR.responseText);
		}
	})
}

function beforeSend(jqXHR, settings) {
	// avant le POST
	// les nombres doivent être postés au format anglo-saxon
	var culture = Globalize.culture().name;
	if (culture === 'fr-FR') {
		checkCulture(1);
		settings.data = formulaire.serialize();
	}
}

function afterComplete(jqXHR, settings) {
	// après le POST
	// les nombres doivent être remis au format français si nécessaire
	var culture = Globalize.culture().name;
	if (culture === 'fr-FR') {
		checkCulture(2);
	}
}

function checkCulture(mode) {
	if (mode == 1) {
		// on met les nombres [a,b] au format anglo-saxon
		var value1 = a.val().replace(",", ".");
		a.val(value1);
		var value2 = b.val().replace(",", ".");
		b.val(value2);
	}
	if (mode == 2) {
		// on met les nombres au format français
		var value1 = a.val().replace(".", ",");
		a.val(value1);
		var value2 = b.val().replace(".", ",");
		b.val(value2);
	}
}

// au chargement du document
$(document).ready(function() {
	// on récupère les références des différents composants de la page
	loading = $("#loading");
	formulaire = $("#formulaire");
	resultats = $('#resultats');
	a = $("#a");
	b = $("#b");
	// on cache certains éléments
	loading.hide();
	// on parse les validateurs du formulaire
	$.validator.unobtrusive.parse(formulaire);
	// on gère deux locales [fr_FR, en_US]
	// les réels [a,b] sont envoyés par le serveur au format anglo-saxon
	// on les met au format français si nécessaire
	checkCulture(2);
});
