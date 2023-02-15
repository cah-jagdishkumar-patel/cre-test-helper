#export VAULT_ADDR=https://vault.dev.cahcommtech.com
#vault login -method=ldap username=jagdishkumar.patel
#vault write concourse/strikers/deploy-keys cre-test-helper=@/Users/jagdishkumar.patel/.ssh/id_ed25519
#vault write concourse/strikers/deploy-keys connect-sidecar-tools=@/Users/jagdishkumar.patel/.ssh/id_ed25519
cd ../../outcomes-pipeline-tools
./gradlew run --args="cre-test-helper"
#cp pipeline.yml ../cre-test-helper/concourse/
#fly --target cre-test-helper login --team-name strikers --concourse-url  https://concourse6.dev.cahcommtech.com
#fly -t cre-test-helper destroy-pipeline -p cre-test-helper
#fly -t cre-test-helper set-pipeline -c pipeline.yml -p cre-test-helper
#fly -t cre-test-helper unpause-pipeline -p cre-test-helper
#fly -t cre-test-helper expose-pipeline -p cre-test-helper
#cd ..
#./gradlew build -x testClasses
