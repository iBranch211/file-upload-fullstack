const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api', // You can pass in an array too eg. ['/api', '/another/path']
        createProxyMiddleware({
            target: "http://localhost:9090",
            changeOrigin: true,
        })
    );
};