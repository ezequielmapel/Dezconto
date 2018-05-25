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





var indexRouter = require('./routes/index');
//var usersRouter = require('./routes/users');
var lojistaRouter = require('./routes/lojista');


// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use('/', indexRouter);
//app.use('/users', usersRouter);
app.use('/lojista', lojistaRouter);


  



app.listen('8080', function(){
	console.log("Listen on 8080");
});

module.exports = app;
