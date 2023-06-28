const mysql = require('mysql2/promise');
const fs = require('fs');

const initData = async () => {

     // 创建数据库连接
     const connection = await mysql.createConnection({
          host: '127.0.0.1',
          user: 'root',
          password: '111111',
          database: 'logistics'
     });
     // 读取JSON文件
     const jsonData = fs.readFileSync('./data/cityPyToHz.json');
     const jsonData2 = JSON.parse(jsonData)

     jsonData2.city.forEach(async item => {
          item.list.forEach(async i2 => {
               await connection.query('INSERT INTO city (name, py) VALUES (?, ?)', [
                    i2.name,
                    i2.pinyin
               ]);
          })

     })
     // 将JSON数据插入到表中

     console.log('写入成功')
     // 关闭数据库连接
     await connection.end();
}

const initChina = async() => {
      // 创建数据库连接
      const connection = await mysql.createConnection({
          host: '127.0.0.1',
          user: 'root',
          password: '111111',
          database: 'logistics'
     });
     const jsonData = fs.readFileSync('./data/china.json');
     await connection.query('INSERT INTO china (data) VALUES (?)', [
          JSON.stringify(jsonData)
     ]);
}

// initData()
initChina()