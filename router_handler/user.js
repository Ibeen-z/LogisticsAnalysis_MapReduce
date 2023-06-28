const db = require('../db/index');
// 登录的处理函数
exports.login = (req, res) => {
     res.send('login OK')
}

/**城市订单数 数据 */
exports.cityData = (req, res) => {
     const sql = `SELECT city.name,warehouse.count FROM city JOIN warehouse WHERE warehouse.keyname = city.py;`
     db.query(sql, (err, results) => {
          if (err) return res.cc(err);
          res.send({
               status: 0,
               message: '查询成功！',
               data: results
           })
     })
}

exports.shoppingMode = (req, res) => {
     const sql = `SELECT keyname AS name,count AS value FROM shoppingmode;`
     db.query(sql, (err, results) => {
          if (err) return res.cc(err);
          res.send({
               status: 0,
               message: '查询成功！',
               data: results
           })
     })
}
exports.orderCountry = (req, res) => {
     const sql = `SELECT keyname AS name,count AS value FROM ordercountry;`
     db.query(sql, (err, results) => {
          if (err) return res.cc(err);
          res.send({
               status: 0,
               message: '查询成功！',
               data: results.sort((a,b)=>{
                    return b.value-a.value
                  })
           })
     })
}
exports.daysForShipping = (req, res) => {
     const sql = `SELECT keyname AS name,count AS value FROM daysForShapping;`
     db.query(sql, (err, results) => {
          if (err) return res.cc(err);
          res.send({
               status: 0,
               message: '查询成功！',
               data: results
           })
     })
}