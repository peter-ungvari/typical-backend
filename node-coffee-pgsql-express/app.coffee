require 'coffee-script/register'

express = require 'express'
person = require './router/person'

app = express()

app.use '/person', person

app.get '/', (req, res) ->
	res.send
		index:
			person: 'GET /person'

server = app.listen 3000, -> console.log 'typical-backend-node app listening on port 3000'