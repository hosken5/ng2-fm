(function (global) {
    System.config({
        paths: {
            // paths serve as alias
            'npm:': '/static/lib/'
        },
        // map tells the System loader where to look for things
        map: {
            // our app is within the app folder
            app: '/static/app/',
            sample: '/static/sample/',
            '@angular/core': 'npm:@angular/core/bundles/core.umd.js',
            '@angular/common': 'npm:@angular/common/bundles/common.umd.js',
            '@angular/compiler': 'npm:@angular/compiler/bundles/compiler.umd.js',
            '@angular/platform-browser': 'npm:@angular/platform-browser/bundles/platform-browser.umd.js',
            '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
            '@angular/http': 'npm:@angular/http/bundles/http.umd.js',
            '@angular/router': 'npm:@angular/router/bundles/router.umd.js',
            '@angular/forms': 'npm:@angular/forms/bundles/forms.umd.js',
            'moment': 'npm:moment/moment.js',
            'angular2-in-memory-web-api': 'npm:angular2-in-memory-web-api',
            'ng2-bootstrap/ng2-bootstrap': 'npm:ng2-bootstrap/bundles/ng2-bootstrap.umd.min.js',
            'angular2-bootstrap-confirm':'npm:angular2-bootstrap-confirm',
            'angular2-bootstrap-confirm/position':'npm:angular2-bootstrap-confirm/position/index.js',
            'angular2-select': 'npm:angular2-select',
            // other libraries
            'rxjs': 'npm:rxjs',
            'primeng':                   'npm:primeng'
        },
        // packages tells the System loader how to load when no filename and/or no extension
        packages: {
            sample: {
                main: './main.js',
                defaultExtension: 'js'
            },
            app: {
                main: './main.js',
                defaultExtension: 'js'
            },
            rxjs: {
                defaultExtension: 'js'
            },
            'angular2-in-memory-web-api': {
                main: './index.js',
                defaultExtension: 'js'
            },
            'angular2-bootstrap-confirm':
            {
                defaultExtension: 'js',
                main:'./dist/umd/angular2-bootstrap-confirm.js'
            },
            'angular2-select': {
                main: 'index.js',
                defaultExtension: 'js'
            },
            primeng: {
                defaultExtension: 'js'
            }
        }
    });
})(this);
