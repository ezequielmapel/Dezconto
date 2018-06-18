var passport = require('passport');
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy;

var config = require('./config.js');
var firebase = require('firebase');
var configfb = require('./firebase');

firebase.initializeApp(configfb);

// Get a reference to the database service
var database = firebase.database();


function checkWriteLojista(reqId, name, email, imageUrl, termosDeUso, novidades){
  // Função pra checar se o usuário existe e escrevendo caso não
  var data = new Date();
  dataS = data.getUTCDate() + '-' + (data.getUTCMonth()+1) + '-' + data.getUTCFullYear();
  
  var nLoj = firebase.database().ref('lojista/');
  nLoj.orderByChild('emailLojista').equalTo(email).once("value", function(snapshot){
    if(!snapshot.val()){
      firebase.database().ref('lojista/'+reqId).set({
        nomeLojista: name,
        emailLojista: email,
        profile_picture : imageUrl,
        descPerm: false,
        termosDeUso: false,
        novidades:false,
        cnpj: false,
        cupom: false,
        data: dataS,
        end: {cidade: false, rua: false, bairro: false, estado: false},
        telefone: false,
        categoriaLoj: false

     });
    }
  });
  
  
}

passport.use(new GoogleStrategy({
    clientID: config.GoogleAuth.consumerKey,
    clientSecret: config.GoogleAuth.consumerSecret,
    callbackURL: config.GoogleAuth.callbackURL
  },
  function(accessToken, refreshToken, profile, done) {
    checkWriteLojista(profile.id, profile.displayName, profile.emails[0].value, profile.photos[0].value, true, true);
    done(null, profile)
    
  }
));

