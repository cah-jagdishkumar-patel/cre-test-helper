fly --target strikers login --team-name strikers --concourse-url  https://concourse6.dev.cahcommtech.com
fly -t strikers destroy-pipeline -p cre-test-helper
fly -t strikers sp -c cre-test-helper-pipeline.yml -p cre-test-helper
fly -t strikers unpause-pipeline -p cre-test-helper
fly -t strikers expose-pipeline -p cre-test-helper