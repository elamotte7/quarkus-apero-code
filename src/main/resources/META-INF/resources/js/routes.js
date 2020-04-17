/*global app, Router */

(function (app, Router) {

    'use strict';

    var router = new Router();

    ['all', 'active', 'tasted'].forEach(function (visibility) {
        router.on(visibility, function () {
            app.visibility = visibility;
        });
    });

    router.configure({
        notfound: function () {
            window.location.hash = '';
            app.visibility = 'all';
        }
    });

    router.init();

})(app, Router);
