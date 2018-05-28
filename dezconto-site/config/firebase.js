
var config = require('./config.js');





var config = {
    apiKey: config.Firebase.apiKey,
    authDomain: config.Firebase.authDomain,
    databaseURL: config.Firebase.databaseURL,
    storageBucket: config.Firebase.storageBucket
  };



  module.exports = config;