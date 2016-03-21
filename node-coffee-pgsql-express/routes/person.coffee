express = require 'express'
bodyParser = require 'body-parser'
personService = require '../service/personService'
person = express.Router()

person.use bodyParser.json()

person.use bodyParser.urlencoded
	extended: true

person.get '/', (req, res) ->
	res.send
		index:
			list: 'GET /list'
			create: 'POST /create'
			delete: 'DELETE /delete'

person.get '/list', (req, res) ->
	personService.findAll().then (data) ->
		res.send data

person.get '/list/after/:name/max/:max', (req, res) ->
	personService.findNextPage req.params.name, req.params.max
	.then (data) -> res.send data

person.get '/:name', (req, res) ->
	personService.findByName req.params.name
	.then (data) -> res.send(data)

person.post '/create/dummy', (req, res) ->
	personService.create 'Otto', 15
	.then -> res.sendStatus 200

person.post '/create/form', (req, res) ->
	personService.create req.body.name, req.body.age
	.then -> res.sendStatus 200

person.post '/create', (req, res) ->
	personService.create req.body.name, req.body.age
        
person.delete '/delete/:name', (req, res) ->
	personService.deleteByName req.params.name
	.then -> res.sendStatus 200

module.exports = person