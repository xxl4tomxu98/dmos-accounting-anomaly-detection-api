# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/html/#build-image)
* [Spring Integration JDBC Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/jdbc.html)
* [Spring Integration JPA Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/jpa.html)
* [Spring Integration R2DBC Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/r2dbc.html)
* [Spring Integration Test Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/testing.html)
* [Spring Integration Security Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/security.html)
* [Spring Integration HTTP Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/http.html)
* [Spring Integration Web Services Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/ws.html)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [OAuth2 Client](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-security-oauth2-client)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#production-ready)
* [Cloud Bootstrap](https://spring.io/projects/spring-cloud-commons)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JDBC](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-sql)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/2.5.3/reference/html/spring-boot-features.html#boot-features-r2dbc)
* [Function](https://cloud.spring.io/spring-cloud-function/)
* [Okta Spring Boot documentation](https://github.com/okta/okta-spring-boot#readme)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Web Services](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-webservices)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-security)
* [Spring Integration](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-integration)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-spring-hateoas)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-security-oauth2-server)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Acessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)
* [Okta-Hosted Login Page Example](https://github.com/okta/samples-java-spring/tree/master/okta-hosted-login)
* [Custom Login Page Example](https://github.com/okta/samples-java-spring/tree/master/custom-login)
* [Okta Spring Security Resource Server Example](https://github.com/okta/samples-java-spring/tree/master/resource-server)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Integrating Data](https://spring.io/guides/gs/integration/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)

### Additional Links

These additional references should also help you:

* [R2DBC Homepage](https://r2dbc.io)
* [Various sample apps using Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function/tree/master/spring-cloud-function-samples)

## OAuth 2.0 and OIDC with Okta

If you don't have a free Okta developer account, you can create one with [the Okta CLI](https://cli.okta.com):

```bash
$ okta register
```

Then, register your Spring Boot app on Okta using:

```bash
$ okta apps create
```

Select **Web** > **Okta Spring Boot Starter** and accept the default redirect URIs.

