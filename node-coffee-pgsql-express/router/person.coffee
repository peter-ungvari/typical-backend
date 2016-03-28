express = require 'express'
compression = require 'compression'
validator = require 'express-validator'
bodyParser = require 'body-parser'
personService = require '../service/personService'

person = express.Router()

person.use compression()

person.use bodyParser.json()

person.use bodyParser.urlencoded
	extended: true

person.use validator
	errorFormatter: (param, msg, value) ->
		errorMessage: "Validation error: #{param} #{msg}."

person.use (req, res, next) ->
	req.handle = ({validate, service}) ->
		validate() if validate
		errors = req.validationErrors()
		if errors
			return res.status(400).send errors
		service()
			.then (data) ->
				if data then res.send data else res.sendStatus 200
			.catch (error) ->
				res.status(500).send error
	next()	

person.get '/', (req, res) ->
	res.send
		index:
			list: ['GET /list', 'GET /list/after/:name/max/:max']
			find: 'GET /name/:name'
			create: ['POST /create/form', 'POST /create']
			delete: 'DELETE /delete'

person.get '/list', (req, res) -> req.handle
	service : -> personService.findAll()

person.get '/list/after/:name/max/:max', (req, res) -> req.handle
	validate : -> req.checkParams('max', 'Should be a positive integer').isInt { min: 1 }
	service : -> personService.findNextPage req.params.name, req.params.max

person.get '/name/:name', (req, res) -> req.handle
	validate : ->
		req.checkParams 'name', 'Should be the name of a person'
		.notEmpty()
	service : -> personService.findByName req.params.name

person.post '/create/dummy', (req, res) -> req.handle
	service : -> personService.create 'Otto', 15

person.post '/create/form', (req, res) -> req.handle 
	validate : ->
		req.checkBody
			name:
				notEmpty: 'Should be a name of a person'
			age:
				isInt: 
					min: 0
					errorMessage: 'Should be a non-negative integer'
	service : -> personService.create req.body.name, req.body.age

person.post '/create', (req, res) -> req.handle
	service : -> personService.create req.body.name, req.body.age
        
person.delete '/delete/:name', (req, res) -> req.handle
	service : -> personService.deleteByName req.params.name
	
module.exports = person