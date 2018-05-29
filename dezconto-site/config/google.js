var passport = require('passport');
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy;

var config = require('./config.js');
var firebase = require('firebase');
var configfb = require('./firebase');

firebase.initializeApp(configfb);

// Get a reference to the database service
var database = firebase.database();


function checkWriteLojista(name, email, imageUrl){
  // Função pra checar se o usuário existe e escrevendo caso não
  var nLoj = firebase.database().ref('lojista/');
  nLoj.orderByChild('email').equalTo(email).once("value", function(snapshot){
    if(!snapshot.val()){
      firebase.database().ref('lojista/').push().set({
        username: name,
        email: email,
        profile_picture : imageUrl
     });
    }
  });
  
}

function writeUserData(name, email, imageUrl) {
  // Função que escreve um novo usuário no bd
  
  if(!email){ 
    firebase.database().ref('lojista/').push().set({
        username: name,
        email: email,
        profile_picture : imageUrl
      });
    }
  }

passport.use(new GoogleStrategy({
    clientID: config.GoogleAuth.consumerKey,
    clientSecret: config.GoogleAuth.consumerSecret,
    callbackURL: config.GoogleAuth.callbackURL
  },
  function(accessToken, refreshToken, profile, done) {
    //console.log(profile);
    checkWriteLojista(profile.displayName, profile.emails[0].value, profile.photos[0].value);
    done(null, profile)
    
  }
));

