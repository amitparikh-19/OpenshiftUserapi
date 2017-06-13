/**
 * Created by Kopal Darbari
 *
 * This module has the configuration necessary for the angular-translate module
 * For more details see:
 * http://angular-translate.github.io/docs/#/guide
 *
 */

'use strict';

var i18nModule = angular.module('i18nModule', ['pascalprecht.translate']);

i18nModule.config(['$translateProvider', function ($translateProvider) {

    //configure staticFilesLoader
    $translateProvider.useStaticFilesLoader({
        prefix: 'i18n/rb-',
        suffix: '.json'
    });

    //load 'en' table on startup
    $translateProvider.preferredLanguage('en');

    //in case of more than one language we should fallback to english
    $translateProvider.fallbackLanguage('en');


}]);