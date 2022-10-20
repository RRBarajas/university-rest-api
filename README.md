# Choice University REST API

REST API used to handle web facing requests.  
This service uses a SOAP Client to get all the relevant data from a SOAP service.  
In doing so, it acts as a wrapper between the UI-Facing applications and the DB facing services, 
adding custom validations and user-friendly requests and responses.

## Getting started

### SOAP Client setup
Since this service is primarily a SOAP Client, it is extremely that the referenced SOAP Service is up and running.  

The SOAP Client URL is defined in the `UniversitySoapApiClient` bean creation.  
Make sure you have updated the `client.setDefaultUri()` to the URL of your SOAP API.


The URL is also used in the POM's `maven-jaxb2-plugin` to generate Java classes based on the SOAP Service WSDL.  
This makes our project dependent on the API exposing the WSDL file.  
In addition, we are storing the generated classes in a particular package.  

Please update the following properties from the plugin to match your services:  
```bash
<schema><url>: URL in which the SOAP API serves the WSDL.
*You can add many URL nodes if there are multiple schemas.

<generatePackage>: The package name where you want to store the generated classes.
NOTE: If you change this, make sure you also change the following:
- The setContextPath() in UniversitySoapApiConfig.class.
- The new package is component scaned in UniversityWebConfig.class.
- The references to the generated classes throughout the project.
```

If the SOAP API is not exposing the WSDL, or we simply don't want to use the plugin, 
we can remove it from the POM, but we will have to manually create the classes needed to parse the SOAP responses.  
Be mindful that doing this could break the code, and you may need to refactor the code. 


### Building the project

```bash
mvn clean install
```

### Tomcat setup
This project makes use of `tomcat7-maven-plugin` to automate the deployment into the tomcat server. 
However, in order to do so, we must first perform some configuration steps.  
If you have a preferred method to deploy the application and don't want to use the plugin, remove the plugin from the `pom.xml` (or just don't run the commands).  
If you do want to use the plugin, follow this configuration before:

#### Setting tomcat user
Once we have our tomcat installed, we must create a user with appropriate permissions.  
To do so, we must edit the file `$CATALINA_HOME\conf\tomcat-users.xml` and add a user.  

For example:
```bash
<user username="YOUR_TOMCAT_USERNAME" password="YOUR_TOMCAT_PASSWORD" roles="manager-gui, manager-script, manager-jmx" />
```
_*This user has more permissions than necessary, but it's just an example._

#### Setting maven server
With our user created, we can now define a Maven server, for which our plugin will connect to.  
This is done by editing the maven `settings.xml` file and adding a server.  

For example:
```bash
<servers>
  <server>
    <id>YOUR_TOMCAT_SERVER_NAME</id>
    <username>YOUR_TOMCAT_USERNAME</username>
    <password>YOUR_TOMCAT_PASSWORD</password>
  </server>
</servers>
```
_*Make sure you use the same user and pass established before._

#### Setting pom plugin
Once our settings have been edited, we must update the plugin configuration with the proper values.  
Pay special attention to the following properties:

For example:
```bash
- warFile: It searches for the default artifact name (artifactId-version). Change it if you are changing your WAR filename.
- warDirectory: Set to the build directory. Change it if your WAR is located elsewhere.
- url: URL of the tomcat. Just change the port if you are using a different one.
- server: Put whatever you named your server in the maven settings.
- path: Path to where your service will be deployed. Change it at will.
```

#### Running the plugin
With all the setup done, you can use the following self-descriptive plugin commands:
```bash
mvn tomcat7:deploy
mvn tomcat7:undeploy
mvn tomcat7:redeploy
```
_*Before running any of the commands, your tomcat should be up and running._  
_**This also assumes you already ran the `install` command._ 

### Code coverage

We plan to use Jacoco to validate the code coverage; however, that is not yet implemented.  
In order to execute the coverage report you can run the following command:

```bash
TBD
```

## Additional information 

### Guides
The following guides illustrate how to use some features concretely:

* [Consuming a SOAP web service](https://spring.io/guides/gs/consuming-web-service/)

