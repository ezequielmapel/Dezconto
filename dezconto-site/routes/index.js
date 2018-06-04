var express = require('express');
var router = express.Router();
var passport = require('passport');

router.use(require('express-session')({ secret: 'keyboard cat', resave: true, saveUninitialized: true }));
router.use(passport.initialize());
router.use(passport.session());

/* GET home page. */
router.get('/', function(req, res, next) {
	if(req.user != null){
		res.redirect('lojista/homepage');
	}else{
		res.render('index');
	}
	
});

module.exports = router;
