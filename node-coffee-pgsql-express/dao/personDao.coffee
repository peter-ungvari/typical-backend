cn =
	user: 'typical-backend-spring'
	password: 'typical-backend-spring'
	database: 'typical-backend-spring'
	host: 'localhost'
	port: '5432'

pgp = require 'pg-promise'
db = pgp cn

findAll = (handler) ->
		db.any 'select * from person'
			.then (data) ->
				console.log data

module.exports =
	findAll: findAll