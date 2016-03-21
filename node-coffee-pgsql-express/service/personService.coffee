personDao = require '../dao/personDao'

module.exports =
	findAll: personDao.findAll
	findNextPage: personDao.findNextPage
	findByName: personDao.findByName
	create: personDao.create
	deleteByName: personDao.deleteByName