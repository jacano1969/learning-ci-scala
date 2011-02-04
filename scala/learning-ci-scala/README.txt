# ---
# Learning "Programming collective intelligence" in Scala
# ---

*** 0. creating project

mvn org.apache.maven.plugins:maven-archetype-plugin:1.0-alpha-7:create \
  -DarchetypeGroupId=org.scala-tools.archetypes \
  -DarchetypeArtifactId=scala-archetype-simple \
  -DarchetypeVersion=1.2 \
  -DremoteRepositories=http://scala-tools.org/repo-releases \
  -DgroupId=learning-ci-scala -DartifactId=learning-ci-scala

*** 1. auto compiling when saving

seratchs-iMac:learning-ci-scala seratch$ ls -ltr /usr/local/bin/sbt*
-rw-r--r--@ 1 seratch  staff  928236  2  3 00:02 /usr/local/bin/sbt-launch.jar
-rwxr-xr-x  1 seratch  staff      64  2  3 00:05 /usr/local/bin/sbt

seratchs-iMac:learning-ci-scala seratch$ cat /usr/local/bin/sbt
#!/bin/sh
java -Xmx512M -jar `dirname $0`/sbt-launch.jar "$@"
seratchs-iMac:learning-ci-scala seratch$ 

seratchs-iMac:learning-ci-scala seratch$ sbt 
[info] Building project learning-ci-scala 1.0 against Scala 2.8.0
[info]    using sbt.DefaultProject with sbt 0.7.4 and Scala 2.7.7
> ~ compile
[info] 
[info] == compile ==
[info]   Source analysis: 0 new/modified, 0 indirectly invalidated, 0 removed.
[info] Compiling main sources...
[info] Nothing to compile.
[info]   Post-analysis: 50 classes.
[info] == compile ==
[success] Successful.
[info] 
[info] Total time: 0 s, completed Feb 3, 2011 12:35:06 AM
1. Waiting for source changes... (press enter to interrupt)

> 

Windows - sbt.cmd
java -Xmx512M -jar %~dp0/sbt-launch.jar

