cf login -a https://api.system.np1.fuseapps.io
cf push -f manifest-stage.yml -p ../build/libs/cre-test-helper-0.0.1-SNAPSHOT.jar

