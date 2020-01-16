const express = require('express');
const router = express.Router();

const customerController = require('../controllers/pedidos')

router.get('/pedidos',customerController.list);
router.get('/pedidos/find/:id',customerController.find);
router.post('/pedidos/add',customerController.save);
router.post('/detallepedidos/add',customerController.savedetalle);

module.exports = router;
