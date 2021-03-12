const path = require('path');

module.exports = {
    optimization: {
        minimize: false
    },
    entry: [
        './my-element.js',
        ],
    output: {
        filename: 'my-element.bundled.js',
        path: path.resolve(__dirname, 'dist'),
        publicPath: '',
    },

};