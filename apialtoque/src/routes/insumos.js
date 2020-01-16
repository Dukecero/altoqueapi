const express = require('express');
const router = express.Router();

const customerController = require('../controllers/insumos')

router.get('/insumos',customerController.list);
router.get('/insumos/find/:id',customerController.find);

module.exports = router;
