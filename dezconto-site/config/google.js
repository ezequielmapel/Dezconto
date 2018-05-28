var passport = require('passport');
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy;

var config = require('./config.js');
var firebase = require('firebase');
var configfb = require('./firebase');

firebase.initializeApp(configfb);

// Get a reference to the database service
var database = firebase.database();


function writeUserData(name, email, imageUrl) {
    firebase.database().ref('lojista/').push().set({
      username: name,
      email: email,
      profile_picture : imageUrl
    });
  }

passport.use(new GoogleStrategy({
    clientID: config.GoogleAuth.consumerKey,
    clientSecret: config.GoogleAuth.consumerSecret,
    callbackURL: config.GoogleAuth.callbackURL
  },
  function(accessToken, refreshToken, profile, done) {
    console.log(profile);
    writeUserData(profile.displayName, profile.emails[0], profile.photos[0].value)
    done(null, profile)
    
  }
));

