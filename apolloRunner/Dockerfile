FROM dockerreg.training.local:5000/java
MAINTAINER Ketan Reddy <ketan.reddy@citi.com>

ENTRYPOINT ["java", "-jar", "/usr/share/apollo/runner.jar"]

ADD target/apolloRunner-1.0-SNAPSHOT.jar /usr/share/apollo/runner.jar