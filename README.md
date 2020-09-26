**This is a sample java CRUD application built on top of Spring Boot.**


# **Building**

`mvn clean verify -Dskip.integration.tests=false -Dmaven.test.failure.ignore=false`


# **Database Setup**

The application is expecting a running PostgreSQL database. Connection strings can be defined in `application.properties` as usual.

SQL script for creating the database can be found in *src/main/resources/create_db.sql*

# **Running (on embedded tomcat)**

`java -jar target/homework-0.0.1-SNAPSHOT.jar`

*Note: Currently the embedded tomcat server is connecting to a database that is running on my home server*

# **Sample Requests**

*The server is listening on port 8080 by default.*

**Fetch All Machines**

URL: http://localhost:8080/api/machines  
Request Verb: GET  
Request Body: *[empty]*  
Response Body: *[empty]*

**Fetch a single Machine**

URL: http://localhost:8080/api/machines/{id}  
Request Verb: GET  
Request Body: *[empty]*  
Response Body: 
```json
{
    "id": "10112233-4455-6677-8899-aabbccddeeff",
    "name": "Test 1",
    "createdAt": "2020-09-23T22:38:00"
}
```

**Create Machine**


URL: http://localhost:8080/api/machines/{id}  
Request Verb: POST  
Request Body:  
```json
{
    "name": "Test 1"
}
```
Response Body: 
```json
{
    "id": "a2370f6e-0d0b-457c-a15f-12dd4ff11aa1",
    "name": "Test 1",
    "createdAt": "2020-09-26T02:05:41.3623474"
}
```

**Update Machine**


URL: http://localhost:8080/api/machines/{id}  
Request Verb: PUT  
Request Body:  
```json
{
    "id": "a2370f6e-0d0b-457c-a15f-12dd4ff11aa1",
    "name": "Renamed Test 1"
}
```
Response Body: 
```json
{
    "id": "a2370f6e-0d0b-457c-a15f-12dd4ff11aa1",
    "name": "Renamed Test 1",
    "createdAt": "2020-09-26T02:05:41.3623474"
}
```

**Delete Machine**

URL: http://localhost:8080/api/machines/{id}  
Request Verb: DELETE  
Request Body: *[empty]*  
Response Body: *[empty]*



### **TODO**

- Configure logging as desired
- Configure Deployment as desired (Tomcat Dedicated? Docker?)
- Configure proper database
- Add Continuus Integration on Github