# creating project
mvn org.apache.maven.plugins:maven-archetype-plugin:1.0-alpha-7:create \
  -DarchetypeGroupId=org.scala-tools.archetypes \
  -DarchetypeArtifactId=scala-archetype-simple \
  -DarchetypeVersion=1.2 \
  -DremoteRepositories=http://scala-tools.org/repo-releases \
  -DgroupId=learning-ci-scala -DartifactId=learning-ci-scala

