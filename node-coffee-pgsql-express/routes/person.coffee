express = require 'express'
personDao = require '../dao/personDao'
person = express.Router()

person.get '/', (req, res) ->
	res.send
		index:
			list: 'GET /list'
			create: 'POST /create'
			delete: 'DELETE /delete'

person.get '/list', (req, res) ->
	personDao.findAll (persons) ->
		res.send persons

module.exports = person