CREATE_PIPELINE=$1
APPLY_PIPELINE=$2
if [[ -z "${CREATE_PIPELINE}" ]] || [[ -z "${APPLY_PIPELINE}" ]]
then
  echo "must pass 2 params"
  exit 1
fi
#export VAULT_ADDR=https://vault.dev.cahcommtech.com
#vault login -method=ldap username=jagdishkumar.patel
#vault write concourse/strikers/deploy-keys/cre-test-helper value=@/Users/jagdishkumar.patel/.ssh/id_ed25519
#vault write concourse/strikers/deploy-keys/connect-sidecar-tools value=@/Users/jagdishkumar.patel/.ssh/id_ed25519
if [[  ${CREATE_PIPELINE} = true ]]
then
  echo "creating pipeline"
  cd ../../outcomes-pipeline-tools
  ./gradlew run --args="cre-test-helper"
  cp pipeline.yml ../cre-test-helper/deploy/
fi
if [[ ${APPLY_PIPELINE} = true ]]
then
  echo "applying pipeline"
  if [[  ${CREATE_PIPELINE} = true ]]
  then
    cd ../cre-test-helper
  else
    cd ..
  fi
  fly --target cre-test-helper login --team-name strikers --concourse-url  https://concourse6.dev.cahcommtech.com
  fly -t cre-test-helper destroy-pipeline -p cre-test-helper
  fly -t cre-test-helper set-pipeline -c deploy/pipeline.yml -p cre-test-helper
  fly -t cre-test-helper unpause-pipeline -p cre-test-helper
  fly -t cre-test-helper expose-pipeline -p cre-test-helper
  ./gradlew build -x testClasses
fi
#cf login -a https://api.system.np1.fuseapps.io
#cf push -f deploy/manifest-stage.yml -p build/libs/cre-test-helper-0.0.1-SNAPSHOT.jar

