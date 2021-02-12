# Phone Numbers Validation

A single page application in Java uses a SQLite database to list phone numbers.   

## Backend
The backend is a spring boot application that run inside a docker container.
You can access the API on localhost port 8080
### Building

Navigate to Backend directory
```
cd back-end
```
Generate JAR file
```
mvn clean install
```
Build Docker image
```
docker build -t jumia/back-end . 
```
### Running
To run spring boot application on port 8080 with docker.
```
docker run -p 8080:8080 jumia/back-end
```

To run Unit and Integration tests.
```
mvn test
```

### Frontend
The frontend is a single HTML page to show all numbers in a table and get filters input from the user.
You can access the webpage on localhost port 80 

Navigate to Frontend directory
```
cd front-end
```

Build and run with docker
```
docker build -t jumia/front-end .
docker run -p 80:80 jumia/front-end
```