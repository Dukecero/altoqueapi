const controller = {};

controller.list = async(req,res) =>{
    // res.json(customers);
    req.getConnection((err, conn) =>{
      conn.query('SELECT * FROM detalle_pedido', async(err, count) =>{
        if (err) {
            res.json(err);
        }else {
            res.json(count);
            }
      });
    });
};

controller.find = async(req,res) =>{
    req.getConnection((err, conn) =>{
      conn.query('SELECT * FROM detalle_pedido where det_codigo = ?',[req.params.id], async(err, count) =>{
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
              req.body.clidireccion,
              req.body.clicodigo,
              // req.body.emp_codigo,
              1,
              // req.body.ped_numero_pedido,
              1234567,
              req.body.clifechaentrega,
              req.body.cliobservacion
           ]
           conn.query('CALL insertar_pedido(?) ', [data], (err, users) => {
               if (err) {
                   res.json({success:false, msg:'No se pudo registrar',data:err});
                   console.log(err);
               }else {
                   res.json({success:true, msg:'Respuesta Registrada ',data:users[0]});
               }
           })
   });
};

controller.savedetalle = async(req,res) =>{
   req.getConnection((err, conn) =>{
           const data = [
              req.body.cantidad,
              req.body.precio,
              req.body.id,
              req.body.titulo
           ]
           conn.query('CALL insertar_detallepedido(?) ', [data], (err, users) => {
               if (err) {
                   res.json({success:false, msg:'No se pudo registrar',data:err});
                   console.log(err);
               }else {
                   res.json({success:true, msg:'Respuesta Registrada ',data:users[0]});
               }
           })
   });
};




module.exports = controller;
