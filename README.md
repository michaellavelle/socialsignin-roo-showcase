socialsignin-roo-showcase
=========================

This project demonstrates how to add spring-social capability to a Roo generated project, using a number of SocialSignin projects
to help developers to get up and running quickly: 

- <a href="https://github.com/socialsignin/spring-social-security" target="_blank">spring-social-security</a> is used for local-user authentication based on third party connection status, 
  removing the need for local username/password account management

- socialsignin-provider modules are used, allowing

 	- developers to obtain and interact with spring-social APIs directly for a number of common-use cases, rather than  dealing with 	       lower-level spring-social connection API
	
	- easy configuration  and registration of spring-social components through component-scanning

- <a href="https://github.com/michaellavelle/spring-social-roo-connectionrepository" target="_blank">spring-social-roo-connection-repository</a> is used to enable Roo-backed JPA persistence to be used to store connections instead
of using the Jdbc versions in the spring-social-core library.  

To run this application:

- Get the code: git clone https://github.com/socialsignin/socialsignin-roo-showcase.git
- <a href="https://dev.twitter.com/apps">Register a Twitter applicaiton</a> and add your Consumer key and Consumer secret to 
<a href="https://github.com/socialsignin/socialsignin-roo-showcase/blob/master/src/main/resources/org/socialsignin/roo/showcase/socialsignin.properties">socialsignin.properties</a>
-- mvn jetty:run , then click on the "Search Tweets" link from the homepage.

This project was created using Roo as follows:
```
project --topLevelPackage org.socialsignin.roo.showcase --projectName socialsignin-roo-showcase --java 6 --packaging JAR
web mvc setup
security setup
jpa setup --provider HIBERNATE --database HYPERSONIC_IN_MEMORY

```
- SocialSignIn repo and dependencies added to pom:

```
  <repository>
    	<id>opensourceagility-snapshots</id>
   		<url>http://repo.opensourceagility.com/snapshots </url>
	</repository>
```

```
 <!-- Start SocialSignin dependencies -->
          	<dependency>
		<groupId>org.socialsignin</groupId>
			<artifactId>socialsignin-twitter</artifactId>
			<version>1.0.2-SNAPSHOT</version>
		</dependency>
		<dependency>
  			<groupId>javax.persistence</groupId>
  			<artifactId>persistence-api</artifactId>
  			<version>1.0</version>
  			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib</artifactId>
			<version>2.2.2</version>
		</dependency>
<!-- End SocialSignin dependencies -->

```

- Showcase code created:
	
	- <a href="https://github.com/socialsignin/socialsignin-roo-showcase/blob/master/src/main/java/org/socialsignin/roo/showcase/controller/ShowcaseController.java" target="_blank">ShowcaseController</a> created, having access to TwitterProviderService, 
	demonstrating use of each of the 3 use-cases supported by SocialSignIn. 
	- Creation of view jsp and creation of corresponding tiles view definition.
	- Link added to index.jsp to showcase controller url.

- Code additions/modificiations to support this showcase controller

	Created 2 new views to support a user logging in via a third-party-provider and choosing a username, made some minor
	amendments to existing header to display logged in status, and added messages to messages.properties to support these
	views.  Created corresponding tiles view definitions.

	AuthenticationDetailViewPreparer component created, and wired into views in views.xml (added into header attribute in
	default.jspx  preparer="authenticationDetailViewPreparer" )
	
- Config modifications to support this showcase controller

	Properties file created 

	SocialConfig configuration class created

	<class>org.springframework.social.connect.roo.UserConnection</class> added to persistence.xml

	Modified applicationContext.xml, applicationContext-security.xml and webmvc-config.xml as follows:
