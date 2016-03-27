express = require 'express'
validator = require 'express-validator'
bodyParser = require 'body-parser'
personService = require '../service/personService'

person = express.Router()

person.use bodyParser.json()

person.use bodyParser.urlencoded
	extended: true

person.use validator
	errorFormatter: (param, msg, value) ->
		errorMessage: "Validation error: #{param} #{msg}."

person.get '/', (req, res) ->
	res.send
		index:
			list: 'GET /list'
			create: 'POST /create'
			delete: 'DELETE /delete'

person.get '/list', (req, res) ->
	personService.findAll().then (data) ->
		res.send data
	.catch (error) -> res.status 500
	.send error

person.get '/list/after/:name/max/:max', (req, res) ->
	req.checkParams 'max', 'Should be a positive integer'
	.isInt { min: 1 }

	errors = req.validationErrors()
	if errors
		res.status 400
		.send errors
		return

	personService.findNextPage req.params.name, req.params.max
	.then (data) -> res.send data
	.catch (error) -> res.status 500
	.send error

person.get '/:name', (req, res) ->
	req.checkParams 'name', 'Should be the name of a person'
	.notEmpty()

	errors = req.validationErrors()
	if errors
		res.status 400
		.send errors
		return

	personService.findByName req.params.name
	.then (data) -> res.send(data)
	.catch (error) -> res.status 500
	.send error

person.post '/create/dummy', (req, res) ->
	personService.create 'Otto', 15
	.then -> res.sendStatus 200
	.catch (error) -> res.status 500
	.send error

person.post '/create/form', (req, res) ->
	req.checkBody
		name:
			notEmpty: 'Should be a name of a person'
		age:
			isInt: 
				min: 0
				errorMessage: 'Should be a non-negative integer'

	errors = req.validationErrors()
	if errors
		res.status 400
		.send errors
		return

	personService.create req.body.name, req.body.age
	.then -> res.sendStatus 200
	.catch (error) -> res.status 500
	.send error

person.post '/create', (req, res) ->
	personService.create req.body.name, req.body.age
        
person.delete '/delete/:name', (req, res) ->
	personService.deleteByName req.params.name
	.then -> res.sendStatus 200
	.catch (error) -> res.status 500
	.send error

module.exports = person