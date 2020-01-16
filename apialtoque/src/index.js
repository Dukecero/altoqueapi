const express = require('express');
const methodOverride = require('method-override');
const path = require('path');
const morgan = require('morgan');
const mysql = require('mysql');
const myConnection = require('express-myconnection');
const ipc = [];
let data;

// Initializations
const app = express();

// Settings
//require('./config/app');
app.set('json spaces',2);
app.set('port',process.env.PORT || 3030);

const host = '192.168.1.217';

// Middlewares
app.use(morgan('dev'));
app.use(myConnection(mysql,{
    host: 'localhost',
    user: 'root',
    password: '',
    port: 3306,
    database: 'altoquebd',
},'single'));
app.use(express.urlencoded({extended:false}));
app.use(express.json());
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Authorization, X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Allow-Request-Method');
    res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, DELETE');
    res.header('Allow', 'GET, POST, OPTIONS, PUT, DELETE');
    next();
});
app.use(methodOverride('_method'));

app.use(express.urlencoded({extended:false}));

// Global Variables

//routes
app.use(require('./routes/users'));
app.use(require('./routes/categorias'));
app.use(require('./routes/insumos'));
app.use(require('./routes/pedidos'));
app.use(require('./routes/recetas'));
app.use(require('./routes/detalle_recetas'));

// Server is listining
app.listen(app.get('port'),host,() => {
     console.log(`Servidor corriendo en http://${host}:${app.get('port')}`);
});
