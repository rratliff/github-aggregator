# Github Aggregator

## Run the code

To run the code, you will need Java 17 installed.

The server can be launched using the Gradle wrapper: 

```
./gradlew bootRun
```

The server runs on port 8080. An example curl request is provided in `http/user.sh`.

## Tooling

* The service is a Spring Boot application using Java 17 and Gradle.
* We use Spring MVC for the REST API, contained in the package `com.rratliff.githubaggregator.api`.
* The Github interactions are contained in the package `com.rratliff.githubaggregator.github`.
    * No caching is implemented at this time, but it would be implemented in the `GithubService` class.
* Uses RestClient (from Spring Framework) and Jackson (part of Spring Boot BOM) for the API interactions.

## Additional interesting decisions

* Decided to use a full data model class for the User object.
* For the repository object, we use JsonNode and fetch the specific fields we need. (Just 2 of them)
* Use RestClient statusHandler to handle 404s and throw our own exception.
* Use try-catch in the controller method to turn the UserNotFoundException into a 404 response.

## Naming things

The `GithubService` class does a translation between the github model class and the api model class, and therefore has a 
clunky-looking naming reference: `com.rratliff.githubaggregator.api.User`. This could be considered a "Gateway Pattern" 
in PofEAA terminology, or a very simplistic "Anti-corruption layer" in DDD terminology. The goal is to highlight that Github's API
may evolve separately from our own API and we want to keep the object definitions separate.

## Testing

* Spring Mock MVC allows us to test the rest controller and the api boundary
* Wiremock allows us to test the github api interactions.
