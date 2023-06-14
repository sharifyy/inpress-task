# InPress Weather App assignment

## Introduction

This application provides current weather, and forecast weather APIs integrating with the free https://openweathermap.org.

## API Documentation

The API documentation for this project is available at `/swagger-ui.html`. It provides detailed information about the
available endpoints, request/response formats, and authentication requirements.

## Endpoints

This project exposes two endpoints to fetch weather data:

1. **Current Weather**
    - URL: `/api/v1/weather`
    - Method: GET
    - Description: Retrieves the current weather information.

2. **Forecast Weather**
    - URL: `/api/v1/weather/forecast`
    - Method: GET
    - Description: Retrieves the forecasted weather information.

Please refer to the API documentation for further details on request parameters and response formats.

## Exception Handling

This project follows a standardized approach for exception handling. Each exception is associated with an error code that maps to an enumeration, allowing for easy localization and translation of error messages into multiple languages.

### Message Localization

To support multiple languages, the error messages are stored in a messages.properties file. This file contains key-value pairs, where the keys are the error codes, and the values are the corresponding error messages in the desired language.

## Monitoring with Prometheus and Grafana

This project is configured with Prometheus and Grafana for monitoring purposes. You can access the monitoring tools as
follows:

- Prometheus: Access Prometheus at `http://localhost:9090` to view metrics and monitor the health of your application.
- Grafana: Access Grafana at `http://localhost:3000`. Use the following credentials to log in:
    - Username: admin
    - Password: admin

You can either create your own custom dashboard in Grafana or import a predefined dashboard by following these steps:

1. Log in to Grafana using the provided credentials.
2. Click on the "+" icon in the left sidebar to create a new dashboard.
3. Select "Import" and choose the option to upload a JSON file.
4. Navigate to the `resources/grafana` directory in your project and select the `springboot-apm-dashboard_rev3.json`
   file.
5. Customize the dashboard as needed.

## Running the Project

To run the project, follow these steps:

1. Create a Docker image for the project by executing the following command:

   $ mvn spring-boot:build-image -Dspring-boot.build-image.imageName=inpress/task:1

This command will build a Docker image for the project with the tag `inpress/task:v1`.

2. Once the Docker image is built, locate the docker-compose.yml file in the root of the project. you can run the project using Docker Compose:

   $ docker-compose up

This command will start all the required services, including the project, Prometheus, Grafana, and other dependencies
defined in the Docker Compose file.

3. Access the project endpoints at the specified URLs and interact with the API as needed.

Note: Ensure that you have Docker and Docker Compose installed on your system before running the project.








