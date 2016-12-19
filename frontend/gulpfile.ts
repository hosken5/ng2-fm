"use strict";

const gulp = require("gulp");
const del = require("del");
const tsc = require("gulp-typescript");
const sourcemaps = require('gulp-sourcemaps');
const tsProject = tsc.createProject("tsconfig.json");
const tslint = require('gulp-tslint');
var browserSync = require( 'browser-sync');
var runSequence = require('run-sequence');


var bs  = browserSync.create();

var reload  = bs.reload;

/**
 * Remove build directory.
 */
gulp.task('clean', (cb) => {
    return del(["build"], cb);
});

/**
 * Lint all custom TypeScript files.
 */
gulp.task('tslint', () => {
    return gulp.src("src/**/*.ts")
        .pipe(tslint({
            formatter: 'prose'
        }))
        .pipe(tslint.report());
});

/**
 * Compile TypeScript sources and create sourcemaps in build directory.
 */
//gulp.task("compile", ["tslint"], () => {
gulp.task("compile", [], () => {
    let tsResult = gulp.src("src/**/*.ts")
        .pipe(sourcemaps.init())
        .pipe(tsc(tsProject));
    return tsResult.js
        .pipe(sourcemaps.write(".", {sourceRoot: '/src'}))
        .pipe(gulp.dest("build")).pipe(browserSync.stream({match: '**/*.js'}));
});

/**
 * Copy all resources that are not TypeScript files into build directory.
 */
gulp.task("resources", () => {
    return gulp.src(["src/**/*", "!**/*.ts"])
        .pipe(gulp.dest("build"));
});

/**
 * Copy all required libraries into build directory.
 */
gulp.task("libs", () => {
    return gulp.src([
            'core-js/client/shim.min.js',
            'systemjs/dist/system-polyfills.js',
            'systemjs/dist/system.src.js',
            'reflect-metadata/Reflect.js',
            'rxjs/**/*.js',
            'zone.js/dist/**',
            '@angular/**/bundles/**',
            'moment/moment.js',
            'ng2-bootstrap/bundles/ng2-bootstrap.umd.min.js',
            'bootstrap/dist/css/*',
            'bootstrap/dist/fonts/*',
            'angular-confirmation-popover/dist/umd/angular-confirmation-popover.js',
            'angular2-bootstrap-confirm/dist/umd/angular2-bootstrap-confirm.js',
            'angular2-bootstrap-confirm/position/*',
            'angular2-in-memory-web-api/**',
            'angular2-select/**',
            'primeng/**',
            'font-awesome/css/**',
            'font-awesome/fonts/**',
        ], {cwd: "node_modules/**"}) /* Glob required here. */
        .pipe(gulp.dest("build/lib"));
});

/**
 * Watch for changes in TypeScript, HTML and CSS files.
 */
gulp.task('watch', function () {
    gulp.watch(["src/**/*.ts"], ['compile']).on('change', function (e) {
        console.log('TypeScript file ' + e.path + ' has been changed. Compiling.');
        reload();
    });
    gulp.watch(["src/**/*.html", "src/**/*.css"], ['resources']).on('change', function (e) {
        console.log('Resource file ' + e.path + ' has been changed. Updating.');
        reload();
    });
});

/**
 * Build the project.
 */
gulp.task("build", ['compile', 'resources', 'libs'], () => {
    console.log("Building the project ...");
});


gulp.task("default",[],()=>{
    runSequence(
       ['build'], ['watch'],['sync']
    );
});

gulp.task('sync', ()=>{
    bs.init({
        // informs browser-sync to proxy our expressjs app which would run at the following location
        proxy: 'http://localhost:8102',
        // informs browser-sync to use the following port for the proxied app
        // notice that the default port is 3000, which would clash with our expressjs
        port: 4000,
        browser: ["google chrome"],
        notify: true,
        ui: {
            port: 4001,
            weinre: {
                port: 4002
            }
        },
        open: "local"
        //"files": [
        //    "build/**/*.{html,htm,css,js}"
        //]
    });
});