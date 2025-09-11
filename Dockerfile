# Use official Tomcat base image
FROM tomcat:9.0-jdk11

# Remove default ROOT app (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file into Tomcat webapps
COPY target/hello-world.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
