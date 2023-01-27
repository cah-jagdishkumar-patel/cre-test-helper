fly --target cre-test-helper login --team-name strikers --concourse-url  https://concourse6.dev.cahcommtech.com
fly -t cre-test-helper destroy-pipeline -p cre-test-helper
fly -t cre-test-helper set-pipeline -c pipeline.yml -p cre-test-helper
fly -t cre-test-helper unpause-pipeline -p cre-test-helper
fly -t cre-test-helper expose-pipeline -p cre-test-helper
cd ..
./gradlew build -x testClasses

