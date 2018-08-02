FROM dockerreg.training.local:5000/java
MAINTAINER Ketan Reddy <ketan.reddy@citi.com>

CMD ["/opt/itrs/netprobe/netprobe.linux_64", "$APPNAME", "-port", "7036", "-nopassword", "&"]

RUN echo "#!/bin/bash \n java $JAVAOPT -jar /usr/share/apollo/server.jar" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8081

# Add the service itself
ADD target/ApolloServer-1.0-SNAPSHOT.jar /usr/share/apollo/server.jar
