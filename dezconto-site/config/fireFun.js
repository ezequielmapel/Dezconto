// --  Firebase Functions  --
/*
	Aqui estará as funções que precisarão
	utilizar o banco de dados em real time.

*/
var firebase = require('firebase');
var database = firebase.database();




module.exports = {
	// Função que escreve um novo cupom
	writeCupom: function(nomeLoja, fotoLoja,idCupom, nomeCupom, desCupom, catCupom, valCupom, qtdCupom, validadePromo, validadeCupom){
		var nCupom = database.ref('cupom/'+idCupom).set({
            nomeLoja: nomeLoja,
            fotoLoja: fotoLoja,
			nomeCupom : nomeCupom,
            idCupom: idCupom,
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
			nomeCupom: nomeCupom,
			idCupom: idCupom,
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

		var char = {'1':'A', '2':'B', '3':'C', '4':'D'};
		var nChar = char[Math.floor((Math.random() * 4) + 1)];
		var numero = Math.floor((Math.random() * 99) + 1);
		var idCupom = nomeCupom.replace(' ','') + valCupom + numero + nChar;

		return idCupom;
	},

	
	readCupom: function(res, req, index){
		/// LER CUPONS DO FIREBASE
		
		var rCupons = database.ref('lojista_cupom').child(req.user.id);
		rCupons.once('value', function(snapshot){
            treeCupom = snapshot.val()
			res.render('account', {nomeLojista: req.user.displayName, imgProfile:req.user.photos[0].value, slideIndex: index, cuponsLojista:treeCupom});
		});
		//console.log("?" + cupons);

	},
    
    updateReadCupom: function(res, req, index){
		/// LER CUPONS DO FIREBASE
		
		var rCupons = database.ref('lojista_cupom').child(req.user.id);
		rCupons.once('value', function(snapshot){
            treeCupom = snapshot.val()
			res.render('account', {nomeLojista: req.user.displayName, imgProfile:req.user.photos[0].value, slideIndex: index, cuponsLojista:treeCupom});
            
          
		});
		//console.log("?" + cupons);
	},

	validarCupom: function(res, idLojista, idCupom){
		var idCupom = idCupom;
		
		/*var cupom = database.ref('cupom').orderByKey().equalTo(idCupom).once('value', function(snapshot){
			if(snapshot.val() == null){
				res.send('4px solid red');			
			}else{
				res.send('4px solid green')
			}			
		});*/
		
		var lojCupom  = database.ref('lojista_cupom').child(idLojista).orderByKey().equalTo(idCupom).once('value', function(snapshot){
			if(snapshot.val() == null){
				res.send('4px solid red');			
			}else{
				res.send('4px solid green');
			};	
	});
		
	},

	validarUsuario: function(res, emailUser, idCupom){
		var usuario = database.ref('user').orderByChild('email').equalTo(emailUser).once('value', function(snapshot){
			if(snapshot.val() == null){
				res.send('4px solid red');			
			}else{
				res.send('4px solid green');
			}		
		});
	},

	validarBoth: function(res, idCupom, emailUser, valCompra){
		// Função para validar / gerar o calculo
		var usuario = database.ref('user').orderByChild('email').equalTo(emailUser).once('value', function(snapshot){
		var sp = snapshot.val();
		for (var el in sp){
			var id = sp[el]['userId'];
			console.log('antes');
			var cupom = database.ref('usuario_cupom/'+id).child(idCupom).once('value', function(snapshot){
					console.log('depois');
				if(snapshot.val() != null){
						console.log('dentro');
					console.log(snapshot.val().valCupom +' '+ valCompra);
					var valCupom = snapshot.val().valCupom;
					var calc =   valCompra - (valCompra*(valCupom/100));
					res.send(calc.toString());			
				};			
			});		
		}
		
	});
	}
}

