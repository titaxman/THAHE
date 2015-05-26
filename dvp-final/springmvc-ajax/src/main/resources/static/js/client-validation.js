// méthodes de validation côté client
// utilisation des bibliothèques suivantes
//[jquery, jquery.validate, jquery.validate.unobtrusive, globalize, globalize.culture.fr-FR, globalize.culture.en-US]

//utilisation des liens suivants
//http://blog.instance-factory.com/?p=268
//http://jsfiddle.net/LDDrk/

// logs
var logs = {
	date : false,
	number : false,
	range : false,
	max : false,
	min : false,
	past : false,
	int : false,
	assertfalse : false,
	custom3 : false
}

// -------------- date
$.validator.addMethod("date", function(value, element, param) {
	// validité
	var valide = Globalize.parseDate(value, "yyyy-MM-dd") != null;
	// logs
	if (logs.date) {
		console.log(JSON.stringify({
			"[date] value" : value,
			"[date] valide" : valide
		}));
	}
	// résultat
	return valide;
});

$.validator.unobtrusive.adapters.add("date", [], function(options) {
	options.rules["date"] = options.params;
	options.messages["date"] = options.message.replace("''", "'");
});

// -------------- number
$.validator.addMethod("number", function(value, element, param) {
	// on gère les cultures [fr-FR] et [en-US] uniquement
	var pattern_fr_FR = /^\s*[-+]?[0-9]*\,?[0-9]+\s*$/;
	var pattern_en_US = /^\s*[-+]?[0-9]*\.?[0-9]+\s*$/;
	var culture = Globalize.culture().name;
	// test de validité
	var valide;
	if (culture === "fr-FR") {
		valide = pattern_fr_FR.test(value);
	} else if (culture === "en-US") {
		valide = pattern_en_US.test(value);
	} else {
		valide = !isNaN(Globalize.parseFloat(value));
	}
	// logs
	if (logs.number) {
		console.log(JSON.stringify({
			"[number] value" : value,
			"[number] culture" : culture,
			"[number] valide" : valide
		}));
	}
	// résultat
	return valide;
});

$.validator.unobtrusive.adapters.add("number", [], function(options) {
	options.rules["number"] = options.params;
	options.messages["number"] = options.message.replace("''", "'");
});

// -------------- range à utiliser conjointement avec [int] ou [number]
$.validator.addMethod("range", function(value, element, param) {
	// logs
	if (logs.range) {
		console.log(JSON.stringify({
			"[range] value" : value,
			"[range] param" : param
		}));
	}
	// validité
	var val = Globalize.parseFloat(value);
	if (isNaN(val)) {
		// logs
		if (logs.min) {
			console.log(JSON.stringify({
				"[range] valide" : true
			}));
		}
		// terminé
		return true;
	}
	var min = Globalize.parseFloat(param.min);
	var max = Globalize.parseFloat(param.max);
	var valide = val >= min && val <= max;
	// logs
	if (logs.range) {
		console.log(JSON.stringify({
			"[range] valide" : valide
		}));
	}
	// terminé
	return valide;
});

$.validator.unobtrusive.adapters.add("range", [ "min", "max" ], function(options) {
	options.rules["range"] = options.params;
	options.messages["range"] = options.message.replace("''", "'");
});

// -------------- max à utiliser conjointement avec [int] ou [number]
$.validator.addMethod("max", function(value, element, param) {
	// logs
	if (logs.max) {
		console.log(JSON.stringify({
			"[max] value" : value,
			"[max] param" : param
		}));
	}
	// validité
	var val = Globalize.parseFloat(value);
	if (isNaN(val)) {
		// logs
		if (logs.max) {
			console.log(JSON.stringify({
				"[max] valide" : true
			}));
		}
		// résultat
		return true;
	}
	var max = Globalize.parseFloat(param.value);
	var valide = val <= max;
	// logs
	if (logs.max) {
		console.log(JSON.stringify({
			"[max] valide" : valide
		}));
	}
	// résultat
	return valide;
});

$.validator.unobtrusive.adapters.add("max", [ "value" ], function(options) {
	options.rules["max"] = options.params;
	options.messages["max"] = options.message.replace("''", "'");
});

// -------------- min à utiliser conjointement avec [int] ou [number]
$.validator.addMethod("min", function(value, element, param) {
	// logs
	if (logs.min) {
		console.log(JSON.stringify({
			"[min] value" : value,
			"[min] param" : param
		}));
	}
	// validité
	var val = Globalize.parseFloat(value);
	if (isNaN(val)) {
		// logs
		if (logs.min) {
			console.log(JSON.stringify({
				"[min] valide" : true
			}));
		}
		// résultat
		return true;
	}
	var min = Globalize.parseFloat(param.value);
	var valide = val >= min;
	// logs
	if (logs.min) {
		console.log(JSON.stringify({
			"[min] valide" : valide
		}));
	}
	// résultat
	return valide;
});

$.validator.unobtrusive.adapters.add("min", [ "value" ], function(options) {
	options.rules["min"] = options.params;
	options.messages["min"] = options.message.replace("''", "'");
});

// -------------- past
$.validator.addMethod("past", function(value, element, param) {
	// validité
	var valide = value <= new Date().toISOString().substring(0, 10);
	// logs
	if (logs.past) {
		console.log(JSON.stringify({
			"[past] value" : value,
			"[past] valide" : valide
		}));
	}
	// résultat
	return valide;
});

$.validator.unobtrusive.adapters.add("past", [], function(options) {
	options.rules["past"] = options.params;
	options.messages["past"] = options.message.replace("''", "'");
});

// -------------- future
$.validator.addMethod("future", function(value, element, param) {
	var now = new Date().toISOString().substring(0, 10);
	return value > now;
});

$.validator.unobtrusive.adapters.add("future", [], function(options) {
	options.rules["future"] = options.params;
	options.messages["future"] = options.message.replace("''", "'");
});

// -------------- notblank
$.validator.addMethod("notblank", function(value, element, param) {
	return $.trim(value).length != 0;
});

$.validator.unobtrusive.adapters.add("notblank", [], function(options) {
	options.rules["notblank"] = options.params;
	options.messages["notblank"] = options.message.replace("''", "'");
});

// -------------- int
$.validator.addMethod("int", function(value, element, param) {
	// validité
	valide = /^\s*[-\+]?\s*\d+\s*$/.test(value);
	// logs
	if (logs.int) {
		console.log(JSON.stringify({
			"[int] value" : value,
			"[int] valide" : valide,
		}));
	}
	// résultat
	return valide;
});

$.validator.unobtrusive.adapters.add("int", [], function(options) {
	options.rules["int"] = options.params;
	options.messages["int"] = options.message.replace("''", "'");
});

// -------------- assertfalse
$.validator.addMethod("assertfalse", function(value, element, param) {
	// logs
	if (logs.assertfalse) {
		console.log(JSON.stringify({
			"[assertfalse] value" : value
		}));
		console.log("[assertfalse] element");
		console.log(element);
		console.log(JSON.stringify({
			"[assertfalse] param" : param
		}));
	}
	// test validité
	return value === "false";
});

$.validator.unobtrusive.adapters.add("assertfalse", [], function(options) {
	// logs
	if (logs.assertfalse) {
		console.log(JSON.stringify({
			"[assertfalse] options.params" : options.params
		}));
		console.log(JSON.stringify({
			"[assertfalse] options.message" : options.message
		}));
		console.log(JSON.stringify({
			"[assertfalse] options.messages" : options.messages
		}));
	}
	// code
	options.rules["assertfalse"] = options.params;
	options.messages["assertfalse"] = options.message.replace("''", "'");
});

// -------------- asserttrue
$.validator.addMethod("asserttrue", function(value, element, param) {
	return value === "true";
});

$.validator.unobtrusive.adapters.add("asserttrue", [], function(options) {
	options.rules["asserttrue"] = options.params;
	options.messages["asserttrue"] = options.message.replace("''", "'");
});

// -------------- custom3 utilisé conjointement avec [number]
$.validator.addMethod("custom3", function(value1, element, param) {
	// seconde valeur
	var value2 = $("#" + param.field).val();
	// logs
	if (logs.custom3) {
		console.log(JSON.stringify({
			"[custom3] value1" : value1,
			"[custom3] param" : param,
			"[custom3] value2" : value2
		}))
	}
	// première valeur
	var valeur1 = Globalize.parseFloat(value1);
	if (isNaN(valeur1)) {
		// on laisse le validateur [number] faire le travail
		if (logs.custom3) {
			console.log(JSON.stringify({
				"[custom3] valide" : true
			}))
		}
		return true;
	}
	// seconde valeur
	var valeur2 = Globalize.parseFloat(value2);
	if (isNaN(valeur2)) {
		// on ne peut faire le calcul de validité
		if (logs.custom3) {
			console.log(JSON.stringify({
				"[custom3] valide" : false
			}))
		}
		return false;
	}
	// calcul de validité
	var min = Globalize.parseFloat(param.min);
	var max = Globalize.parseFloat(param.max);
	var somme = valeur1 + valeur2;
	var valide = somme >= min && somme <= max;
	// logs
	if (logs.custom3) {
		console.log(JSON.stringify({
			"[custom3] valide" : valide
		}))
	}
	// résultat
	return valide;
});

$.validator.unobtrusive.adapters.add("custom3", [ "field", "max", "min" ], function(options) {
	options.rules["custom3"] = options.params;
	options.messages["custom3"] = options.message.replace("''", "'");
});
