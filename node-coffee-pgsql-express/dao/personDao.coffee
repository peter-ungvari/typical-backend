promise = require 'bluebird'
pgp = (require 'pg-promise')
	promiseLib: promise

cn =
	user: 'typical-backend-spring'
	password: 'typical-backend-spring'
	database: 'typical-backend-spring'
	host: 'localhost'
	port: '5432'

db = pgp cn

findAll = ->
	db.any 'select * from person'

module.exports =
	findAll: findAll
