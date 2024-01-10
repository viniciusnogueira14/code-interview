# Code Interview
This project was created as a test for evaluation in a Code Interview.

## About the project
The project was created using the following technologies and frameworks:
* [Java 1.8](https://docs.oracle.com/javase/8/docs/api/)
* [Spring Boot 2.7.18](https://docs.spring.io/spring-boot/docs/2.7.18/api/)
* [Maven](https://maven.apache.org/index.html)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [H2 Database](https://www.h2database.com/html/main.html) - [[Maven Dependency]](https://mvnrepository.com/artifact/com.h2database/h2)
* [Project Lombok](https://projectlombok.org/) - [[Maven Dependency]](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* [Model Mapper](https://modelmapper.org/user-manual/) - [[Maven Dependency]](https://mvnrepository.com/artifact/org.modelmapper/modelmapper)
* [Open CSV](https://opencsv.sourceforge.net/) - [[Maven Dependency]](https://mvnrepository.com/artifact/com.opencsv/opencsv)
* [Spring Doc Open API](https://springdoc.org/) - [[Maven Repository]](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui)


## Known Backlog
### General
* Finish the suite of Tests (Integrated, API and more cases of Unit Tests);
* Implement caching strategy for the most called results, to improve the performance;
* Implement the CI/CD strategy (Docker, Kubernetes, Pipelines, etc.);
* Create an integration with SonarQube and define a level for the Quality Gate;

### Controller Module
* Implement an ExceptionHandler class;
* Implement endpoints to create the `CarPosition` and `PointOfInterest` objects accepting a CSV File as the RequestBody parameter;
* Implement GetMethods to retrieve `CarPosition` and `PointOfInterest` information;
* Adjust the property `timeAtPointOfInterest` in the class `CarAtPointOfInterest` to receive the full time format;
* Improve the JSON property names at the `CarAtPointOfInterestTime` object;

### Service Module
#### CarPositionService
* Implement the method `findByParameters` using Specification instead of if-else conditions;

#### PointOfInterestService
* Implement the find methods correctly;

#### TimeAtPointOfInterestService
* Refactor the method `calculateTimeSpentInsidePointOfInterest` to decrease the complexity of the code;
* Possibly filter the final Map result to remove the `PointOfInterest` objects that has no time spent inside them;
* Change the `findAll()` method for another with some kind of filter (Point Name, Customer, etc.);