var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var bodyParser = require('body-parser');

var app = express();

var urlencodedParser = bodyParser.urlencoded({extended: true});
app.use(bodyParser.json());
app.use(urlencodedParser);





//var indexRouter = require('./routes/index');
//var usersRouter = require('./routes/users');
//var lojistaRouter = require('./routes/lojista');


// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
//app.use('/', indexRouter);
//app.use('/users', usersRouter);
//app.use('/lojista', lojistaRouter);


  
app.get('/lojista', function(req, res){res.render('lojista');});



app.post('/lojista', urlencodedParser, function(req, res){
  
	// var lojNome = req.body.nome;
	// var lojSobrenome = req.body.sobrenome;
	// var lojEmail = req.body.email;
	// var lojSenha = req.body.senha;
	// var lojNmr = req.body.nmr;
	// var lojPorcentagem = req.body.porcentagem;
	
  
	console.log(req.body.nome);
	//res.send(lojNome);
 });



app.listen('8080', function(){
	console.log("Listen on 8080");
});

module.exports = app;
