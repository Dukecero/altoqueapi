const controller = {};

controller.list = async(req,res) =>{
    // res.json(customers);
    req.getConnection((err, conn) =>{
      conn.query('SELECT * FROM categoria', async(err, count) =>{
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
      conn.query('SELECT * FROM categoria where cat_codigo = ?',[req.params.id], async(err, count) =>{
        if (err) {
            res.json(err);
        }else {
            res.json(count[0]);
            }
      });
    });
};


module.exports = controller;
