FROM dockerreg.training.local:5000/centos
MAINTAINER Ketan Reddy <ketan.reddy@citi.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/apollo/server.jar"]

# Add the service itself
ADD target/ApolloServer-1.0-SNAPSHOT.jar /usr/share/apollo/server.jar