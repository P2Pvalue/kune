set :application, "kune"
set :repository,  "https://git.gitorious.org/kune/p2pvalue-trunk.git"

set :scm, :git
# Or: `accurev`, `bzr`, `cvs`, `darcs`, `git`, `mercurial`, `perforce`, `subversion` or `none`

set :db_pwd, 'db4kune'

set :use_sudo, false

role :web, "your web-server here"                          # Your HTTP server, Apache/etc
role :app, "your app-server here"                          # This may be the same as your `Web` server
role :db,  "your primary db-server here", :primary => true # This is where Rails migrations will run
role :db,  "your slave db-server here"

# if you want to clean up old releases on each deploy uncomment this:
after "deploy:restart", "deploy:cleanup"

namespace :deploy do
  task :finalize_update do
    # Link files
    run "ln -s #{ shared_path}/src/main/resources/kune.properties #{ release_path }/src/main/resources/"
    run "rm #{ release_path}/src/main/resources/wave-server.properties"
    run "ln -s #{ shared_path}/src/main/resources/wave-server.properties #{ release_path }/src/main/resources/"
    run "ln -s #{ shared_path}/kune-data #{ release_path }/"

    # Change database password
    db_files = %w{pom.xml bin/liquibase-migrate.sh bin/liquibase-rollback.sh bin/i18n-db2gwt.sh src/main/resources/db/liquibase.properties src/main/resources/META-INF/persistence.xml}.map{ |f| "#{ release_path }/#{ f }" }.join(" ")

    run "sed -i 's/db4kune/#{ db_pwd }/g' #{ db_files }"

    run "cd #{ release_path } && mvn assembly:assembly -P production"
    
    # Backup database
    run "mkdir -p #{ current_path }/bck"
    run "mysqldump -u kune -p#{ db_pwd } kune_prod > #{ current_path }/bck/mysql.dump"
  end

  task :restart, roles: :app, except: { no_release: true } do
    run "cd #{ release_path } && bin/restart.sh"
  end

end

