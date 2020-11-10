'use strict'
// const CompressionPlugin = require("compression-webpack-plugin");
module.exports = {
  // 基本路径
  // baseUrl: process.env.NODE_ENV === 'production' ? './' : './',
  publicPath: process.env.NODE_ENV === 'production' ? './' : './',
  // 输出文件目录
  outputDir: 'dist',
  // 放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
  assetsDir: '',
  // eslint-loader 是否在保存的时候检查
  lintOnSave: false,
  // 生产环境是否生成 sourceMap 文件
  productionSourceMap: false,
  // runtimeCompiler: true, // 是否使用包含运行时编译器的 Vue 构建版本

  // webpack-dev-server 相关配置
  devServer: {
    host: '0.0.0.0',
    port: 10002,
    https: false,
    hotOnly: false,
    // proxy: { //设置代理
    //   '/': {
    //     target: 'http://81.70.158.7:33066',
    //     changeOrigin: true
    //   }
    // },
    open: false,
    index: 'index.html',    //  默认启动页面
    before: app => {} // `app` 是一个 express 实例
  },
  

  chainWebpack: config => {
    // const svgRule = config.module.rule('svg')
    // // 清除已有的所有 loader。如果你不这样做，接下来的 loader 会附加在该规则现有的 loader 之后。
    // svgRule.uses.clear()
    // svgRule
    //   .use('svg-sprite-loader')
    //   .loader('svg-sprite-loader')
    //   .options({
    //       symbolId: 'icon-[name]'
    //   })
    
    // // 修复HMR
    // config.resolve.symlinks(true)
    
    // scss公共变量配置
    const oneOfsMap = config.module.rule('scss').oneOfs.store
    oneOfsMap.forEach(item => {
      item
        .use('sass-resources-loader')
        .loader('sass-resources-loader')
        .options({
            // Provide path to the file with resources
            // 要公用的scss的路径
            resources: './src/styles/variables.scss'
        })
        .end()
    })
  },


}
  