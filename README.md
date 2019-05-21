# Project Nasa

Automating the Test for certifying the search end point using Rest Assured

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for de testing purposes. 

### Prerequisites

```
Install spring boot with TestNg plugin and create a Maven project using rest assured libraries
```

## Running the tests

### **Functional Tests:

#### For all the scenarios if the status code is 200 then only the response body is verified.**

-  Validate the  status code by providing 2 parameters

  End Point: https://images-api.nasa.gov/search?center=JPLResponse&nasa_id=PIA12909

-  Validate the  response by giving only  3 parameters and the no of hits returned

  End Point: https://images-api.nasa.gov/search?center=JPL&nasa_id=PIA12909?q=apollo11 

-   Verify by the response code of next page by navigating from one page to other page.

- Validate the  no of items returned in the response with  4 different keyword in the end point 

  End Point:https://images-api.nasa.gov/search?keywords=ranger,ranger 9,launch,Moon.

- Validate the content type in the response if it JSON is present in the response or not.

- Validate the Headers in the responses " Content-Length --> 970"

  

## Built With

- Spring Boot - The  framework used

- [Maven](https://maven.apache.org/) - Dependency Management

- TestNG - plugin

- Java - Programming language

- Rest Assured - Tools

  

![](C:\Users\Munni\Documents\image2.png)
