const express = require('express')
// 创建服务器实例
const app = express()

//配置 cors 跨域
const cors = require('cors')
app.use(cors())

//配置解析 application/x-www-form-urlencoded 格式的表单数据的中间件
app.use(express.urlencoded({ extended: false }))

app.use(function (req, res, next) {
  res.cc = function (err, status = 1) {
    res.send({
      status,
      message: err instanceof Error ? err.message : err,
    })
  }
  next()
})

const userRouter = require('./router/user')
app.use('/api', userRouter)
// 启动服务器
app.listen(80, () => {
  console.log('express server running at http://127.0.0.1')
})
