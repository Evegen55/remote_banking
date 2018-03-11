# An example of modern web application by implementing remote banking system
### designed with REST, microservices and OAuth authorization

This app is developing with two git services:
- [GitHub](https://github.com/Evegen55/remote_banking)
- [Bitbucket](https://bitbucket.org/Johnn55/remote_banking)

The high-level architecture design:

![**high-level**](https://raw.githubusercontent.com/Evegen55/remote_banking/master/src/test/resources/for_readme/high-level_remote_banking.png)

Source of scheme is  [here](https://www.lucidchart.com/documents/view/90174cab-00d1-43a2-886b-0d83f6922d4f)

The first version of a database (with MySQL RDBMS):

![**first version**](https://raw.githubusercontent.com/Evegen55/remote_banking/master/src/test/resources/for_readme/first_rdbms.PNG)

**NOTE!**

Before deployment write credentials to a database at db_connections.properties:

- datasource.username
- datasource.password

Deployment to a google cloud:

 - [With no billing](https://youtu.be/5wNI4Btpbos)
 - [With enabled billing](TODO)
 - [External link](TODO)
 
 To run an application do: `./gradlew update bootRun`
 
 (Liquibase update task creates a schema and populates it by demo data.)
 
 ## License
 
 Copyright (C) 2018 - 2018 [Evgenii Lartcev](https://github.com/Evegen55/) and contributors
 
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
