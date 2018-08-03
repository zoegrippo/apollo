FROM dockerreg.training.local:5000/java
MAINTAINER Ketan Reddy <ketan.reddy@citi.com>

CMD ["/opt/itrs/netprobe/netprobe.linux_64", "$APPNAME", "-port", "7036", "-nopassword", "&"]

ENTRYPOINT ["java", "${JAVAOPT}", "-jar", "/usr/share/apollo/runner.jar"]

ADD target/apolloRunner-1.0-SNAPSHOT.jar /usr/share/apollo/runner.jar
