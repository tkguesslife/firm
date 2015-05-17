var path = require('path');

module.exports = function (grunt) {
    grunt.initConfig({
        express: {
            dev: {
                options: {
                    server: path.resolve('./server'),
                    port: 3000,
                    hostname: '*'
                }
            }
        },
        requirejs: {
            options: {
                paths: {
                    'appFiles': './app'
                },
                removeCombined: true,
                out: './app/requirejs/appFirm-combined.js',
                optimize: 'none',
                name: 'main'
            },
            dev: {
                options: {
                    optimize: 'none'
                }
            },
            release: {
                options: {
                    optimize: 'uglify'
                }
            }
        },
        watch: {
            scripts: {
                files: ['./app/**/*.js', './app/**/**/**/*.js', '!./app/requirejs/appFirm-combined.js'],
                tasks: ['dev'],
                options: {
                    spawn: false
                }
            }
        }

    });

    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-requirejs');
    grunt.loadNpmTasks('grunt-express');
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.registerTask('dev', ['requirejs:dev', 'watch']);
    grunt.registerTask('release', ['requirejs:release', 'express:dev', 'express-keepalive']);
};