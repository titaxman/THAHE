// données globales
var content;

function gotoPage(num) {
	// on fait un appel Ajax à la main
	$.ajax({
		url : '/ajax-07',
		type : 'POST',
		data : 'num=' + num,
		dataType : 'html',
		beforeSend : function() {
		},
		success : function(data) {
			content.html(data);
		},
		complete : function() {
		},
		error : function(jqXHR) {
			// erreur système
			content.html(jqXHR.responseText);
		}
	})
}

// au chargement du document
$(document).ready(function() {
	// on récupère les références des différents composants de la page
	content = $("#content");
});
