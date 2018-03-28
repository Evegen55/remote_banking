# An example of modern web application by implementing remote banking system
### designed with REST, microservices and OAuth authorization

 This app is developing with two git services:
 - [GitHub](https://github.com/Evegen55/remote_banking)
 - [Bitbucket](https://bitbucket.org/Johnn55/remote_banking)

## The high-level architecture design:

![**high-level**](https://raw.githubusercontent.com/Evegen55/remote_banking/master/src/test/resources/for_readme/high-level_remote_banking.png)

Source of scheme is  [here](https://www.lucidchart.com/documents/view/90174cab-00d1-43a2-886b-0d83f6922d4f)

The first version of a database (with MySQL RDBMS):

![**first version**](https://raw.githubusercontent.com/Evegen55/remote_banking/master/src/test/resources/for_readme/first_rdbms.PNG)

**NOTE!**

Before deployment write credentials to a database at `db_connections.properties`:

- `datasource.username`
- `datasource.password`

Deployment to a google cloud:

 - [With no billing](https://youtu.be/5wNI4Btpbos)
 - [With enabled billing](TODO)
 - [External link](TODO)
 
## Build and run

 **Important!**

 This project has [Liquibase plugin ](liquibase.org) in order to manage RDMBS development flow.
 The main purpose to use that is to have knowledge about changes in a database.
 If you did the first creation of a database manually via init.sql file -
 do not run `update` command with gradle until you make other changes in a database.
 In that case put path to your file with `liquibase.changelogfile` field at `db_connections.properties`
 (path has to be relative to folder `$projectDir/rdbms_flow`)
 If you want to do the first creation of a database via Liquibase plugin -
 first you have to create a schema (database) as named in connection URL (remote_banking).
 Just because Liquibase has no ability to create schema.

 So to run application:
 
 - In case updating RDBMS via Liquibase plugin. Gradle will also run tests:
  
    `./gradlew update bootRun`
    
    (Liquibase update task creates a schema and populates it by demo data.)
    
 - In case start application without updating RDBMS. Gradle will also run tests:
 
    `./gradlew bootRun`

## ENDPOINTS for HTTP-methods (to tune API root see also api.root property):

### GET

 - [http://localhost:8080/rest/v1/hello/world]()
 - [http://localhost:8080/rest/v1/users]()
 - [http://localhost:8080/rest/v1/users/1]()
 - [http://localhost:8080/rest/v1/users/1/emails]()
 - [http://localhost:8080/rest/v1/send-to-jms-queue/fake-user]()

### POST

 - TODO
 
 
## License
 
 Copyright (C) 2018 - 2018 [Evgenii Lartcev](https://github.com/Evegen55/) and contributors
 
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
