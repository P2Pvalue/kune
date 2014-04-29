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

    # Change database password
    db_files = %w{pom.xml bin/liquibase-migrate.sh bin/liquibase-rollback.sh bin/i18n-db2gwt.sh src/main/resources/db/liquibase.properties src/main/resources/META-INF/persistence.xml}.map{ |f| "#{ release_path }/#{ f }" }.join(" ")

    run "sed -i 's/db4kune/#{ db_pwd }/g' #{ db_files }"

    run "cd #{ release_path } && mvn assembly:assembly -P production -Dliquibase.should.run=false"

    # A custom folder where we will deploy .jar 
    set :jar_deploy_folder, "#{ release_path }/local-release"

    # The kune-data/ folder
    set :data_folder, "#{ jar_deploy_folder }/kune-data"

    # A custom folder to place the static web resources
    set :webapp_deploy_folder, "#{ jar_deploy_folder }/webapp"

    # A custom folder to place config files
    set :config_folder, "#{ jar_deploy_folder }/config"

    # Deploy binary .jar file
    run "cp #{ release_path }/target/kune-*-complete.jar #{ jar_deploy_folder }"

    # Deploy Webapp files
    %w{ css object-embed-devel.html others static tutorials wse-dev.html wse.html ws.html }.each do |file|
      run "cp -r #{ release_path }/src/main/webapp/#{ file } #{ webapp_deploy_folder }"
    end

    %w{ web.xml localhost.cer localhost.key }.each do |file|
      run "cp -r #{ release_path }/src/main/webapp/WEB-INF/#{ file } #{ webapp_deploy_folder }/WEB-INF"
    end

    # Deploy GWT files
    %w{ ws wse }.each do |file|
      run "cp -r #{ release_path }/target/kune-*/#{ file } #{ webapp_deploy_folder }"
    end

    # Configuration
    run "ln -s /etc/kune #{ jar_deploy_folder }/config"

    # Data
    run "ln -s #{ shared_path}/kune-data #{ data_folder }"

    # Backup database
    run "mkdir -p #{ current_path }/bck"
    run "mysqldump -u kune -p#{ db_pwd } kune_prod > #{ current_path }/bck/mysql.dump"
  end

  task :restart, roles: :app, except: { no_release: true } do
    run "cd #{ release_path }/local-release/webapp && ./restart.sh"
  end

end

