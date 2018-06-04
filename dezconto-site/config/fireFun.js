// --  Firebase Functions  --
/*
	Aqui estará as funções que precisarão
	utilizar o banco de dados em real time.

*/
var firebase = require('firebase');

var database = firebase.database();




module.exports = {
	// Função que escreve um novo cupom
	writeCupom: function(idCupom, nomeCupom, desCupom, catCupom, valCupom, qtdCupom, validadePromo, validadeCupom){
		var nCupom = database.ref('cupom/'+idCupom).set({
			nomeCupom : nomeCupom,
			desCupom : desCupom,
			catCupom : catCupom,
			valCupom : valCupom,
			qtdCupom : qtdCupom,
			validadePromo : validadePromo,
			validadeCupom : validadeCupom
		});

		
		 

		//console.log("KEY: >>>" + keyCupom.key);
	},

	writeLojaCupom: function(idLoja, idCupom, nomeCupom, desCupom, qtdCupom, valCupom, validadeCupom, validadePromo){
		// Função que liga lojista com o cupom em lojista_cupom	
		var nLojCupom = database.ref('lojista_cupom/'+idLoja).child(idCupom);
		nLojCupom.set({
			idLoja: idLoja,
			nomeCupom: nomeCupom, 
			desCupom:desCupom,
			qtdCupom:qtdCupom, 
			valCupom:valCupom,
			validadeCupom: validadeCupom,
			validadePromo: validadePromo 
		});
		// nLojCupom.set(idLoja);

		// nLojaCupom = database.ref('lojista_cupom/'+idLoja)
		// nLojaCupom.set(idCupom);
		// nLojaCupom = database.ref('lojista_cupom/');
	},


	updateCupom: function(idCupom, idLoja){
		var refCupom = {};
		refCupom[idCupom] = true;
		database.ref('lojista/'+idLoja).child('cupom').update(refCupom);
	},


	gerarIdCupom: function(nomeLoja, nomeCupom , valCupom){
		/*
		Gerando um id para o cupom, concatenando o nome da loja + nome do cupom + valor +
		número aleatorio entre 1 e 99 e um caracter especial (A, B, C);
		*/

		var char = {'1':'A', '2':'B', '3':'C'};
		var nChar = char[Math.floor((Math.random() * 3) + 1)];
		var numero = Math.floor((Math.random() * 99) + 1);
		var idCupom = nomeLoja.replace(' ','') + nomeCupom.replace(' ','') + valCupom + numero + nChar;

		return idCupom;
	}
}

