// variables globales
var formulaire;
var activateValidationTrue;
var activateValidationFalse;
var clientValidation;
var loading;
var lang;
var double1;
var double2;
var double3;

// document ready
$(document).ready(function() {
	// on annule le parsing des validateurs du formulaire
	// formulaire.data('validator', null);
	// on le refait
	// $.validator.unobtrusive.parse(formulaire);

	// références globales
	formulaire = $("#form");
	activateValidationTrue = $("#clientValidationTrue");
	activateValidationFalse = $("#clientValidationFalse");
	clientValidation = $("#clientValidation");
	loading = $("#loading");
	lang = $("#lang");
	double1 = $("#double1");
	double2 = $("#double2");
	double3 = $("#double3");
	// on gère deux locales [fr_FR, en_US]
	// les réels sont par défaut au format anglo-saxon
	if (lang.val() == 'fr_FR') {
		// on les met au format français
		var value1 = double1.val().replace(".", ",");
		double1.val(value1);
		var value2 = double2.val().replace(".", ",");
		double2.val(value2);
		var value3 = double3.val().replace(".", ",");
		double3.val(value3);
	}
	// liens de validation
	// clientValidation est un champ caché positionné par le serveur
	var validate = clientValidation.val();
	setClientValidation2(validate === "true");
});

// post formulaire
function postForm01() {
	// mode de validation côté client
	var validationActive = clientValidation.val() === "true";
	if (validationActive) {
		// on efface les erreurs du serveur
		clearServerErrors();
		// validation du formulaire
		if (!formulaire.validate().form()) {
			// pas de submit
			return false;
		}
	}
	// réels au format anglo-saxon
	var value1 = double1.val().replace(",", ".");
	double1.val(value1);
	var value2 = double2.val().replace(",", ".");
	double2.val(value2);
	var value3 = double3.val().replace(",", ".");
	double3.val(value3);
	// on laisse le submit se faire
	return true;
}

// validation côté client
function setClientValidation(activate) {
	// on gère l'activation / désactivation de la validation client
	setClientValidation2(activate);
	// on mémorise le choix de l'utilisateur dans le champ caché
	clientValidation.val(activate ? "true" : "false");
	// ajustements supplémentaires
	if (activate) {
		// la validation client est active
		// on efface tous les messages d'erreur du serveur
		clearServerErrors();
		// on valide le formulaire
		formulaire.validate().form();
	} else {
		// la validation client est inactive
		// on efface tous les messages d'erreur du client
		clearClientErrors();
	}
}

// validation client
function setClientValidation2(activate) {
	// liens
	if (activate) {
		// la validation client est active
		activateValidationTrue.hide();
		activateValidationFalse.show();
		// on parse les validateurs du formulaire
		$.validator.unobtrusive.parse(formulaire);
	} else {
		// la validation client est inactive
		activateValidationFalse.hide();
		activateValidationTrue.show();
		// on désactive les validateurs du formulaire
		formulaire.data('validator', null);
	}
}

// clear client errors
function clearClientErrors() {
	// on efface les msg d'erreur du client
	$(".field-validation-error").each(function(index) {
		$(this).text("");
	});
	// on change la classe CSS des saisies erronées
	$(".input-validation-error").each(function(index) {
		$(this).removeClass("input-validation-error");
	});

}

function clearServerErrors() {
	// on efface les msg d'erreur du serveur
	$(".error").each(function(index) {
		$(this).text("");
	});
}

// locale
function setLocale(locale) {
	// on met à jour la locale
	lang.val(locale);
	// on soumet le formulaire - pour une raison ignorée cela ne déclenche pas les validateurs du client
	// c'est pourquoi on n'a pas inhibé la validation
	document.form.submit();
}
