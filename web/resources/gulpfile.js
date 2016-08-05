'use strict';

var gulp = require('./gulp')([
    'browserify'
]);

gulp.task('build', ['browserify']);
