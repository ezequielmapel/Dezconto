var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var passport = require('passport');
var google = require('../config/google');
var ensureLoggedIn = require('connect-ensure-login').ensureLoggedIn;
var cookieParser = require('cookie-parser');

router.use(require('express-session')({ secret: 'keyboard cat', resave: true, saveUninitialized: true }));
router.use(passport.initialize());
router.use(passport.session());
router.use(cookieParser('keyboard cat'));
router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());






router.get('/', function(req, res, next) {
  
  res.render('lojista');
  
});


router.post('/cadastro', function(req, res){

  var lojNome = req.body.nome;
  var lojSobrenome = req.body.sobrenome;
  var lojEmail = req.body.email;
  var lojSenha = req.body.senha;
  var lojNmr = req.body.nmr;
 
 
});



router.get('/auth/google',
  passport.authenticate('google', {  scope: [
    'https://www.googleapis.com/auth/userinfo.profile',
    'https://www.googleapis.com/auth/userinfo.email'
] }));

router.get('/auth/google/callback', 
  passport.authenticate('google', {failureRedirect: '/', successReturnToOrRedirect: '/lojista/homepage'}));


router.get('/homepage', ensureLoggedIn('/lojista/auth/google'), function(req, res){
  console.log(req.user);
  res.render('account', {nomeLojista: req.user.displayName});
});

passport.serializeUser(function(user, done) {
  done(null, user);
});

passport.deserializeUser(function(user, done) {
  done(null, user);
});

module.exports = router;
