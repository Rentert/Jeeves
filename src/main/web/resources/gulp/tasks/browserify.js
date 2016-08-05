var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');

module.exports = function() {
    return browserify('js/jeeves/app.js')
        .bundle()
        // Передаем имя файла, который получим на выходе, vinyl-source-stream
        .pipe(source('bundle.js'))
        .pipe(gulp.dest('./js/jeeves'));
};
