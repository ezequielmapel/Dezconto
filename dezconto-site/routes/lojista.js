var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var passport = require('passport');
var google = require('../config/google');
var ensureLoggedIn = require('connect-ensure-login').ensureLoggedIn;
var cookieParser = require('cookie-parser');

// Require FireData
var fireFun = require('../config/fireFun');

router.use(require('express-session')({ secret: 'keyboard cat', resave: true, saveUninitialized: true }));
router.use(passport.initialize());
router.use(passport.session());
router.use(cookieParser('keyboard cat'));
router.use(bodyParser.urlencoded({ extended: true }));
router.use(bodyParser.json());



router.get('/', function(req, res, next) {
  res.render('lojista');
  
});

router.get('/homepage/sair', function(req, res){
	req.logout();
	res.redirect('/lojista');
});


router.post('/cadastro', function(req, res){

  var lojNome = req.body.nome;
  var lojSobrenome = req.body.sobrenome;
  var lojEmail = req.body.email;
  var lojSenha = req.body.senha;
  var lojNmr = req.body.nmr;
 
 
});




// Autenticação do Google
router.get('/auth/google',
  passport.authenticate('google', {  scope: [
    'https://www.googleapis.com/auth/userinfo.profile',
    'https://www.googleapis.com/auth/userinfo.email'
] }));

router.get('/auth/google/callback', 
  passport.authenticate('google', {failureRedirect: '/', successReturnToOrRedirect: '/lojista/homepage/'}));



passport.serializeUser(function(user, done) {
  done(null, user);
});

passport.deserializeUser(function(user, done) {
  done(null, user);
});




// HOMEPAGE do lojista

router.get('/homepage', ensureLoggedIn('/lojista/auth/google'), function(req, res, next){
//  res.render('account', {nomeLojista: req.user.displayName, imgProfile:req.user.photos[0].value, slideIndex:1});
    fireFun.readCupom(res, req, 1);
});


// CALCULADORA

router.get('/calculadora', function(req, res){
  var idCupom = req.query.idCupom;
  var emailUser = req.query.emailUser;

  fireFun.validarCupom(req.user.id, idCupom, emailUser);
});



router.post('/homepage/gerenciar-cupons', ensureLoggedIn('/lojista/auth/google'), function(req, res){
  var nomeCupom = req.body.nomeCupom;
  var desCupom = req.body.desCupom;
  var catCupom = req.body.catCupom;
  var valCupom = req.body.valCupom;
  var qtdCupom = req.body.qtdCupom;
  var validadePromo = req.body.validadePromo;
  var validadeCupom = req.body.valCadaCupom;
 
  var nomeLoja = req.user.displayName;
  var fotoLoja = req.user.photos[0].value;
  var idCupom = fireFun.gerarIdCupom(req.user.displayName, nomeCupom, valCupom);

  //console.log(nomeCupom, desCupom, catCupom, valCupom, qtdCupom, validadePromo, validadeCupom);
  fireFun.writeCupom(nomeLoja,fotoLoja,idCupom, nomeCupom, desCupom, catCupom, valCupom, qtdCupom, validadePromo, validadeCupom);
  
  // Ligação lojista_cupm
  var idLoja = req.user.id;
  fireFun.writeLojaCupom(idLoja, idCupom, nomeCupom, desCupom, qtdCupom, valCupom, validadeCupom, validadePromo);
  
  // Atualizando o cupom criado no lojista
  fireFun.updateCupom(idCupom, idLoja);

  
  
  //fireFun.updateReadCupom(res, req, 3);
  res.redirect('/');
  
});




// GERENCIAR-CUPOM 





module.exports = router;
