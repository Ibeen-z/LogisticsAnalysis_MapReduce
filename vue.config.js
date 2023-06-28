module.exports = {
  publicPath: "/",
  lintOnSave: false,
  transpileDependencies: true,
  devServer: {
    proxy: {
      "/api": {
        target: "http://127.0.0.1:80/",
        changOrigin: true,
      },
      "/amap": {
        target: "https://restapi.amap.com",
        changOrigin: true,
        pathRewrite: {
          "^/amap": "",
        },
      },
      "/baidu": {
        target: "https://api.map.baidu.com",
        changOrigin: true,
        pathRewrite: {
          "^/baidu": "",
        }
      },
    }
  }
}