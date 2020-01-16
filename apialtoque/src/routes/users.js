const express = require('express');
const router = express.Router();

const customerController = require('../controllers/users')

// router.post('/postusers',customerController.user);
// router.post('/postuser',customerController.user_user);
router.get('/usuario',customerController.list);
router.get('/usuario/find/:id',customerController.find);
router.post('/usuario/validate',customerController.validate);
router.post('/usuario/add',customerController.save);
router.put('/usuario/update/:id', customerController.update);

module.exports = router;
