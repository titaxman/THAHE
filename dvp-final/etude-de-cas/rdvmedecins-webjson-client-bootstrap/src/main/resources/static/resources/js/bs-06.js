function deconnecter() {
	showInfo("Déconnexion demandée...");
}

function setLang(lang) {
	if (lang == calendar_infos.langue) {
		return;
	}
	calendar_infos.langue = lang;
	updateCalendar(true);
	afficherAgenda();
}

function showInfo(message) {
	$("#info").text(message);
}

function initNavBar3() {
	// dropdown des langues
	$('.dropdown-toggle').dropdown();
	// l'image animée
	loading = $("#loading");
	loading.hide();
}

function retourAccueil() {
	showInfo("option [Retour accueil] cliquée...");
}

function retourAgenda() {
	showInfo("option [Retour agenda] cliquée...");
}

function validerRv() {
	showInfo("option [Valider] cliquée...");
}

function setMenu(show) {
	// les liens du menu
	var lnkAfficherAgenda = $("#lnkAfficherAgenda");
	var lnkAccueil = $("#lnkAccueil");
	var lnkValiderRv = $("#lnkValiderRv");
	var lnkRetourAgenda = $("#lnkRetourAgenda");
	// on les met dans un dictionnaire
	var options = {
		"lnkAccueil" : lnkAccueil,
		"lnkAfficherAgenda" : lnkAfficherAgenda,
		"lnkValiderRv" : lnkValiderRv,
		"lnkRetourAgenda" : lnkRetourAgenda
	}
	// on cache tous les liens
	for ( var key in options) {
		options[key].hide();
	}
	// on affiche ceux qui sont demandés
	for (var i = 0; i < show.length; i++) {
		var option = show[i];
		options[option].show();
	}
}

function afficherAgenda() {
	// on affiche médecin et date
	var idMedecin = $('#idMedecin option:selected').val();
	if (calendar_infos.date) {
		showInfo("Vous avez sélectionné le médecin d'id=" + idMedecin + " et le jour " + calendar_infos.date);
	}
}

function initChoixMedecinJour() {
	// calendrier
	var calendar_container = $("#calendar_container");
	calendar_infos = {
		"container" : calendar_container,
		"html" : calendar_container.html(),
		"today" : moment().format('YYYY-MM-DD'),
		"langue" : "fr"
	}
	// création calendrier
	updateCalendar();
	// le select des médecins
	$('#idMedecin').selectpicker();
	$('#idMedecin').change(function(e) {
		afficherAgenda();
	})
	// le menu
	setMenu([]);
}

function updateCalendar(renew) {
	if (renew) {
		// régénération du calendrier actuel
		calendar_infos.container.html(calendar_infos.html);
	}
	// initialisation du calendrier
	var calendar = $("#calendar");
	var settings = {
		format : "yyyy-mm-dd",
		startDate : calendar_infos.today,
		language : calendar_infos.langue,
	};
	calendar.datepicker(settings);
	// sélection de la date courante
	if (calendar_infos.date) {
		calendar.datepicker('setDate', calendar_infos.date)
	}
	// évts
	calendar.datepicker().on('hide', function(e) {
		// affichage jour sélectionné
		displayJour();
	});
	calendar.datepicker().on('changeDate', function(e) {
		// on note la nouvelle date
		calendar_infos.date = moment(calendar.datepicker('getDate')).format("YYYY-MM-DD");
		// affichage infos agenda
		afficherAgenda();
		// affichage jour sélectionné
		displayJour();
	});
	// affichage jour sélectionné
	displayJour();
}

// affiche le jour sélectionné
function displayJour() {
	if (calendar_infos.date) {
		var displayjour = $("#displayjour");
		moment.locale(calendar_infos.langue);
		jour = moment(calendar_infos.date).format('LL');
		displayjour.val(jour);
	}
}

// ------------ document ready
var calendar_infos = {};

$(document).ready(function() {
	// initialisation document
	console.log("document.ready");
});
