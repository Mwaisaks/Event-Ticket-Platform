## Advanced Features
Venue as it's own entity

I still think it would be wise to make the start, endDate not nullable.

* Implementing a soft delete features so that when the user deletes their account, it doesn't delete the events.
* Same for Ticket and ticketType r/ship

Implement CSRF

**Tip:** implement the feature where the end date is after the start date in the CreateEventRequestDto.java
//future implementation for the organiser to specify other users who can call this method (UserController.java)
***

First of all I'm amazed with how the project was broken down; the project brief from page 6 to 25 in the buikd-event-ticket platform.pdf. What kind of high-levele thinking do I need as developer to have a very good plan where you put yourself in the shoes of the user and identify the POV of the user, I just wanna employ such a mindset when doing my personal projects. Any tips?

In the domain modelling section on page 51 I see we have the 'is a' relationship between the user entity and the different types of users and we have a 'has a' relationship between the ticket and the ticket type. Tell me about the 'has a' r/ship and the 'is a' relationship

In the REST API design(pg. 60), tell me the difference between the PUT and PATCH endpoints, When to use which?

Having a verb in the url path is not considered best practice, instead we're supposed to use a query parameter, Why's that?

the other one is  a documentation for the project which you'll as a reference throughout this chat. 
***
#### REST API Design
Using Sub-resources for resources that exists within another resource. i.e
 Examples will be included 

***
**application.properties file**
```
spring.application.name=Event-Ticket-Platform

#Database Connection
spring.datasource.url=jdbc:postgresql://localhost:5433/postgres - how our application can connect to the database
spring.datasource.username=postgres - specify the username
spring.datasource.password=changemeinprod - specify the password, the same as in docker-compose.yml, shouldn't be included during production

#JPA Configuration
spring.jpa.hibernate.ddl-auto=update - create and update the schema based on the entities in the code, before going to production you may need to lock down the db and use a db migration tool like flyway
spring.jpa.show.sql=true
spring.jpa.properties.hibernate.format_sql=true - for formatting sql
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect  - hibernate is like the impl of jpa interface, does all the hardwork
```

***
## Keycloak
- An open source, Identity and Access Management solution developed by Red Hat.

- You can add authentication and secure services with min effort and there's no need to deal with storing users or authenticating users

**Features**
Allows SSO with IAM
Authentication and authorization
Centralised management of users
Provides an adapter to integrate with SpringBoot and Spring Security
User federation
Multi-Factor Authentication (MFA)
Social login

Installation Process

A realm refers to a security and administrative domain where users, applications, and roles are managed. 

A user belongs to and logs into a realm.

**Client**

A client refers to an application or service that interacts with the Keycloak server for authentication and authorization purposes. 

**Roles in KeyCloak**

Roles are used to define and manage permissions and access levels for users and clients within a realm.

2 types:

1. Realm Roles
- defined at the realm level and are available across all clients within the realm.
- These are global permissions that apply to all clients within the realm

2. Client Roles
- Specific to individual client applications within the realm.

**Users in Keycloak**

Users can be individuals who need to access applications or services secured by Keycloak or administrators who manage the Keycloak realm and its configurations.

**Keycloak User Validation and Generating Access Tokens**

***
Keycloak Configuration on docker-compose.yml

run docker-compose up 
```
keycloak:
image: quay.io/keycloak/keycloak:latest  //you could specify a version if you like
ports:
- "9090:8080"  
environment:
KEYCLOAK_ADMIN: admin
KEYCLOAK_ADMIN_PASSWORD: admin  //not for production
volumes:
- keycloak-data:/opt/keycloak/data
command:
- start-dev
- --db=dev-file

volumes:
keycloak-data:
driver: loca

```

***
**Setting up Mapstruct**

Add mapstruct and lombok version next to the java version in pom.xml(1.6.3 goes well with 1.18.36)

Add mapstruct dependency and use dollar sign syntax(${}) to add the versions

Configure the maven compiler plugin

- lombok must come first


***
## JPA Entities

The Many side typically owns the relationship and so that's where configuration happens. 

In a ManyToMany r/ship you'll check which entity 'owns' the other

So cascades are only defined in the OneToMany r/ships only?

we won't use any referenced fields in our equals and hashcode methods..for now


***
## JWT to User

Extending the JpaRepository, gives you the save and create CRUD operations

We'll remove @Builder. 

* It requires us to add additional annotations where we've initialised using collections, atm it's ignoring it and assigning null to the variables
* We've used Jpa annotations for audits; @CreatedDate and @LastModifiedDate

**Enabling Spring JPA Audit**
* Created the JpaConfiguration class in the config package, use @Configuration and @EnableJpaAuditing
* Added orm.xml configuration file enabling the audit fields.


***
## Create Event Part IV

The CreateEventRequest and CreateEventRequestDto.java - one is used in the service layer the one ending with dto is supposed to be used fro the presantation layer

**Tip:** implement the feature where the end date is after the start date in the CreateEventRequestDto.java

//future implementation for the organiser to se caller is the organiserpecify other users who can call this method
***
#### MapStruct Guide
A Java Bean mapper - maps between 2 java beans automatically

Manual mapping can be time-consuming and has a lot of boiler-plate code.

With Mapstruct, we only need to create the interface nad the library will automatically create a concrete implementation during compile time.

Visit https://www.javaguides.net/2022/12/spring-boot-mapstruct-example-tutorial.html for more.

#### ModelMapper Guide
- a lightweight java library used for model mappings

**Steps**
* Add the maven dependency
* configure Model Mapper a s a Spring Bean - in the main class
* Inject and use ModelMapper Spring Bean in the service class

https://www.javaguides.net/2022/12/spring-boot-modelmapper-example-map.html

#### Exception handling
Created a GlobalExceptionHandler.java class in the controller package.

Create a dto for the response expected by the user, has String and sometimes type fields. Make use of lombok





