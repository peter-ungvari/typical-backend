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

module.exports =
	
	findAll: -> db.any 'select * from person'

	findNextPage: (name, limit) -> db.any '
		select *
		from person
		where name > $1
		order by name
		limit $2',
		[name, limit]

	findByName: (name) -> 
		db.oneOrNone 'select * from person where name = $1', name

	create: (name, age) -> db.none '
		insert into person (name, age) 
	    values ($1, $2)',
	    [name, age]

	deleteByName: (name) ->
		db.none 'delete from person where name = ${name}',
			name: name
