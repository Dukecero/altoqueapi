const express = require('express');
const router = express.Router();

const customerController = require('../controllers/categorias')

router.get('/categoria',customerController.list);
router.get('/categoria/find/:id',customerController.find);

module.exports = router;
