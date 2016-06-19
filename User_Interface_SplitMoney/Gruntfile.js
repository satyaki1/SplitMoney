// our wrapper function (required by grunt and its plugins)
// all configuration goes inside this function
module.exports = function (grunt) {

    // CONFIGURE GRUNT
    grunt.initConfig({
        // get the configuration info from package.json file
        // this way we can use things like name and version (pkg.name)
        pkg: grunt.file.readJSON('package.json'),

        // all of our configuration goes here
        concat: {
            dist: {
                options: {
                    separator: ';'
                },
                src: [
                    'Vendor_JS/*.js', // All JS in the libs folder
                ],
                dest: 'build/production.js',
            }
        },

        uglify: {
            options: {
                compress: true
            },
            build: {
                src: 'build/production.js',
                dest: 'build/production.min.js'
            }
        }

    });

    // 3. Where we tell Grunt we plan to use this plug-in.
    grunt.loadNpmTasks('grunt-contrib-concat');

    // Load the plugin that provides the "uglify" task
    grunt.loadNpmTasks('grunt-contrib-uglify');

    // Default task(s)
    grunt.registerTask('default', ['concat','uglify']);
};
