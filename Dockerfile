# For reference only

FROM websphere-liberty
ADD target/KeepCalmServer.war /opt/ibm/wlp/usr/servers/defaultServer/dropins/
ENV LICENSE accept
EXPOSE 9080

## Running the container locally
# mvn clean install
# docker build -t keepcalmserver:latest .
# docker run -d --name myjavacontainer keepcalmserver
# docker run -p 9080:9080 --name myjavacontainer keepcalmserver
# Visit http://localhost:9080/KeepCalmServer/

## Push container to Bluemix
# Install cli and dependencies: https://console.ng.bluemix.net/docs/containers/container_cli_cfic_install.html#container_cli_cfic_install
# docker tag getstartedjava:latest registry.ng.bluemix.net/<my_namespace>/getstartedjava:latest
# docker push registry.ng.bluemix.net/<my_namespace>/getstartedjava:latest
# bx ic images # Verify new image
