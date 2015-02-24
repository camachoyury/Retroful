// server.js

// BASE SETUP
// =============================================================================

// call the packages we need

 
var express    = require('express');        // call express               
// define our app using express
var app        = express(); 
var bodyParser = require('body-parser');

var port = process.env.PORT || 8080;        // set our port

// ROUTES FOR OUR API
// ===================================================
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
var router = express.Router();              // get an instance of the express Router
var user = {
		  	username: 'coder',
		  	password:'coder',
		  	email: 'camachoyury@sgmail.com.com',
		  	firstName: 'Yury',
		  	lastName: 'Camacho'
	};
// test route to make sure everything is working (accessed at GET http://localhost:8080/api)
router.get('/', function(req, res) {
    res.json(user);   
});

router.post('/', function(req, res) {
    	var name = req.param('username');
    	var pass = req.param('password');
	        if(name == user.username && pass == user.password) {
				res.json(user);     
	        }
	        else{
	        	res.json({ message: 'User not found!' }); 
	        }
    
});


app.use('/api/user', router);

// START THE SERVER
// =============================================================================
app.listen(port);
console.log('Server on ' + port);
