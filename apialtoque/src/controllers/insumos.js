const controller = {};

controller.list = async(req,res) =>{
    // res.json(customers);
    req.getConnection((err, conn) =>{
      conn.query('SELECT * FROM `insumos_categoria`', async(err, count) =>{
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
      conn.query('SELECT * FROM insumos where ins_codigo = ?',[req.params.id], async(err, count) =>{
        if (err) {
            res.json(err);
        }else {
            res.json(count[0]);
            }
      });
    });
};

module.exports = controller;
