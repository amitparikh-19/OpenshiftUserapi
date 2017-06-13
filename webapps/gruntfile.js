	/**
 * @Author: Kopal D
 *
 * This file is used for building application
 * 
 * @params:
 * i18n  		- Builds i18n files.
 * annot 		- annots DI for angulars modules & controllers.
 * clean-annot	- cleans the temporary files created for annot operation.
 * concat		- concats all the angular js controllers * modules into a single file.
 * minify 		- minifies the angular files.
 *  
 */


module.exports = function (grunt) {
   
    'use strict';

    require('load-grunt-tasks')(grunt);
    grunt.loadNpmTasks('grunt-contrib-clean');
    
    grunt.initConfig({
       
    	"merge-json": {
            "i18n": {
                files: {
                    "aui/i18n/rb-en.json": ["aui/modules/*/i18n/*-en-rb.json",  "aui/i18n/*.json" ]
                }
            }
        },
        
        "clean": {
        	dist:{
        	src: ['aui/main.annot.js',
        	      'aui/**/*.annot.js',
        	      'aui/modules/**/*.annot.js',
        	      'aui/modules/**/**/*.annot.js',
        	      'aui/components/**/**/*.annot.js']
        	},
         sim:{
        	src: ['sim/main.annot.js',
        	      'sim/**/*.annot.js',
        	      'sim/modules/**/*.annot.js',
        	      'sim/modules/**/**/*.annot.js',
        	      'sim/components/**/**/*.annot.js']
        	}
        },
        
        "ngAnnotate": {
        	dist: {
        		files: [{
                    expand: true,
                    src: [
                          'aui/main.js', '!aui/main.annot.js',
                          'aui/http.service.js', '!aui/http.service.js',
                          'aui/**/*.js', '!aui/**/*.annot.js',
                          'aui/modules/**/*.js', '!aui/modules/**/*.annot.js',
                          'aui/modules/**/**/*.js', '!aui/modules/**/**/*.annot.js',
                          'aui/components/**/**/*.js', '!aui/components/**/**/*.annot.js'
                          ],
                    ext: '.annot.js',
                    extDot: 'last'
                }],
        	},
        	sim: {
        		files: [{
                    expand: true,
                    src: [
                          'sim/main.js', '!sim/main.annot.js',
                          'sim/http.service.js', '!sim/http.service.js',
                          'sim/**/*.js', '!sim/**/*.annot.js',
                          'sim/modules/**/*.js', '!sim/modules/**/*.annot.js',
                          'sim/modules/**/**/*.js', '!sim/modules/**/**/*.annot.js',
                          'sim/components/**/**/*.js', '!sim/components/**/**/*.annot.js'
                          ],
                    ext: '.annot.js',
                    extDot: 'last'
                }],
        	}
        },
        
        "concat": {
           modules: {
               	src: [  
               	        'aui/main.annot.js',
                        'aui/modules/*/*.annot.js',
                        'aui/interceptors/*.annot.js',
                        'aui/directives/*.annot.js',
                        'aui/layouts/*.annot.js',
                        'aui/i18n/i18nModule.annot.js',
                        'aui/http.service.annot.js',
                     ],
                     dest: 'build/aui/mod.app.js'
               },
           modules_sim: {
           	src: [  
           	        'sim/main.annot.js',
                    'sim/modules/*/*.annot.js',
                    'sim/directives/*.annot.js',
                    'sim/i18n/i18nModule.annot.js',
                    'sim/http.service.annot.js',
                    'sim/layouts/*.annot.js',
                 ],
                 dest: 'build/sim/mod.app.js'
           },
           js: {
            	src: [
                      '!aui/modules/*/*/*.js', 'aui/modules/*/*/*.annot.js',
                      '!aui/components/**/**/*.js', 'aui/components/**/**/*.annot.js'
                     ],
                     	dest: 'build/aui/con.app.js'
                },
          js_sim: {
                	src: [
                          '!sim/modules/*/*/*.js', 'sim/modules/*/*/*.annot.js',
                          '!sim/components/**/**/*.js', 'sim/components/**/**/*.annot.js'
                         ],
                         	dest: 'build/sim/con.app.js'
                    },      
           app: {
        	   src: ['build/aui/mod.app.js', 'build/aui/con.app.js'],
        	   dest: 'build/aui/app.js'
               },
           app_sim: {
            	   src: ['build/sim/mod.app.js', 'build/sim/con.app.js'],
            	   dest: 'build/sim/app.js'
                   }
        },
        
        "uglify": 
        {
        	dist: {
            js: {
                src: ['build/aui/app.js'],
                dest: 'build/aui/app.min.js'
            	}
        	},
        	sim: {
                js: {
                    src: ['build/sim/app.js'],
                    dest: 'build/sim/app.min.js'
                	}
            	}
        },
        
        "copy": 
        {
        	main: {
        		files: [
        		           {src: ['index.html'], dest: 'build/index.html'},
        		           {expand: true, src: ['libs/**'], dest: 'build/'},
        		           {expand: true, src: ['resources/**','!resources/styles/**'], dest: 'build/'},
        		           {expand: true, src: ['aui/**/**','!**/*.js'], dest: 'build/'},
        		           {expand: true, src: ['sim/**/**','!**/*.js'], dest: 'build/'},
        		           {expand: true, src: ['misc/**/**','!**/*.js'], dest: 'build/'}
        			   ]
        	      }
        	
        },
       
        "connect": {
            server: {
                options: {
                    port: 9000,
                    open: true,
                    livereload: 35729,
                    base: 'build/'
                    }
            }
        },
        
        "watch": {
            options: {
                livereload: 35729
            },
            js: {
                files:
                    [
                     'aui/**',
                     'sim/**',
                     'resources/**'
                    ],
                tasks: ['build']
            }
        },
        "sass": {
            options: {
                sourceMap: true,
                outputStyle: 'expanded'
            },
            dist: {
                files: {
                    'build/resources/styles/login.css': 'resources/styles/login.scss',
                    'build/resources/styles/style.css': 'resources/styles/style.scss',

                }
            }
        }
    });

    grunt.registerTask('i18n', ['merge-json']);
    grunt.registerTask('annot', ['ngAnnotate:dist']);
    grunt.registerTask('clean-annot', ['clean:dist']);
    grunt.registerTask('concatx', ['concat:modules','concat:js','concat:app']);
    grunt.registerTask('copyx', ['copy']);
    grunt.registerTask('minify', ['uglify:dist']);
    grunt.registerTask('connectx', ['connect']);
    grunt.registerTask('all', ['i18n','annot','concatx','minify','clean-annot']);
    grunt.registerTask('dev', ['all','copyx','sass','connect','watch']);
    grunt.registerTask('build', ['all','sim','copyx','sass']);
    
    grunt.registerTask('sim', ['ngAnnotate:sim','concat:modules_sim','concat:js_sim','concat:app_sim','uglify:sim','clean:sim','copyx','sass']);
    grunt.registerTask('sim-dev', ['ngAnnotate:sim','concat:modules_sim','concat:js_sim','concat:app_sim','uglify:sim','clean:sim','copyx','sass','connect','watch']);

};
