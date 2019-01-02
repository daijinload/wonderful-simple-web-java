#!/bin/sh -ex

# pkill -9 -f com.mycompany.jettytest
mvn clean install
# JAVA_HOME=/home/daijin/.sdkman/candidates/java/11.0.1-open /home/daijin/incubator-netbeans-10.0-vc5/nbbuild/netbeans/java/maven/bin/mvn "-Dexec.args=-classpath %classpath com.mycompany.jettytest.EmbeddedJettyMain" -Dexec.executable=java -Dexec.classpathScope=runtime org.codehaus.mojo:exec-maven-plugin:1.5.0:exec
JAVA_HOME=/home/daijin/.sdkman/candidates/java/11.0.1-open /usr/bin/mvn "-Dexec.args=-classpath %classpath com.mycompany.jettytest.EmbeddedJettyMain" -Dexec.executable=java -Dexec.classpathScope=runtime org.codehaus.mojo:exec-maven-plugin:1.5.0:exec
