const controller = {};

controller.list = async(req,res) =>{
    // res.json(customers);
    req.getConnection((err, conn) =>{
      conn.query('SELECT * FROM usuario', async(err, count) =>{
        if (err) {
            res.json(err);
        }else {
            res.json(count);
            }
      });
    });
};

controller.validate = async(req,res) =>{
        const user = req.body.username;
        const pass = req.body.password;
        req.getConnection((err, conn) =>{
            conn.query('SELECT * FROM usuario where usu_codigo = ?  and usu_clave = ?',[user, pass],(err, data) => {
                if (err) {
                    res.json(err);
                }else {
                    if (data.length > 0) {
                        res.json( {success:true,data:data,msg:'Login Succesfully.'});
                    }else {
                        res.status(401);
                        res.json( {success:false, data:data, msg:'Usuario o clave no validos'} );
                    }
                }
            });
        });
};

controller.find = async(req,res) =>{
    req.getConnection((err, conn) =>{
      conn.query('SELECT * FROM usuario where usu_codigo = ?',[req.params.id], async(err, count) =>{
        if (err) {
            res.json(err);
        }else {
            res.json(count[0]);
            }
      });
    });
};

controller.save = async(req,res) =>{
   req.getConnection((err, conn) =>{
           const data = [
               req.body.usu_codigo,
               req.body.usu_nombre,
               req.body.usu_clave,
               req.body.cli_codigo,
               // req.body.usu_rol
               "Cliente"
           ]
           conn.query('CALL insertar_usuario(?)', [data], (err, users) => {
               if (err) {
                   res.status(400);
                   res.json({success:false, msg:'No se pudo registrar',data:err});
                   // console.log(err);
               }else {
                   res.status(201);
                   res.json({success:true, msg:'Respuesta Registrada ',data:users[0]});
               }
           })


   });
};


controller.update = async(req, res) => {
    req.getConnection((err, conn) =>{
      const data = [
          req.body.usu_nombre,
          req.body.usu_clave,
          req.body.cli_codigo,
          req.body.usu_rol,
          req.params.id
      ]
        conn.query('CALL modificar_usuario(?) ', [data], (err, users) => {
            // res.send('words');
            if (err) {
                // res.json(err);
                console.log(err)
                // console.log({success:false, msg:'No se pudo registrar',data:err});
                res.json({success:false, msg:'No se pudo modificar',data:err});
            }else {
                res.json({success:true, msg:'Se pudo modificar',data:users[0]});
              }
        });
    });
  };


module.exports = controller;
