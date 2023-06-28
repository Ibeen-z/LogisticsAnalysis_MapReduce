const express = require('express')
const router = express.Router()

// 导入用户路由处理函数模块
const logisticsHandle = require('../router_handler/user')

// 登录
router.get('/cityData', logisticsHandle.cityData)
router.get('/shoppingMode', logisticsHandle.shoppingMode)
router.get('/orderCountry', logisticsHandle.orderCountry)
router.get('/daysForShipping', logisticsHandle.daysForShipping)

module.exports = router 