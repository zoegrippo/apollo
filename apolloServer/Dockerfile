FROM dockerreg.training.local:5000/java
MAINTAINER Ketan Reddy <ketan.reddy@citi.com>

CMD ["/opt/itrs/netprobe/netprobe.linux_64", "$APPNAME", "-port", "7036", "-nopassword", "&"]

ENTRYPOINT ["/usr/bin/java", "$JAVA_OPTS", "-jar", "/usr/share/apollo/server.jar"]

# Add the service itself
ADD target/ApolloServer-1.0-SNAPSHOT.jar /usr/share/apollo/server.jar
