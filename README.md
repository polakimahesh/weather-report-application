API Documentation for Weather Report Service
---------------------------------------------------------------------
This document provides detailed information about the available endpoints and how to interact with the API for registering users, logging in, and getting weather reports.

Implemented a system that uses a free IP geolocation API (like ipstack) to determine the user's city and country. Based on this, the system fetches the current weather data using OpenWeatherMap. Additionally, Implemented rate-limiting compliance to prevent exceeding API request limits, making the system reliable and efficient for users.

Table of Contents
-------------------------
1.Register User
2.Login User
3.Get Weather Report by IP Address


1. Register User
Endpoint POST /users/register

This endpoint registers a new user by providing required user details such as userName, password, and age. The registration will be successful if all the fields are provided.

Request
URL: http://localhost:8080/users/register
Method: POST
Headers:Content-Type: application/json
Body:
{
    "userName": "Mahesh",
    "password": "Mahesh123",
    "age": 26
}
Response
Status: 201 Created
{
    "message": "Created Successfully!"
}

2. Login User
Endpoint: POST /users/login
Description
This endpoint allows a registered user to log in using their credentials. Upon successful login, a JWT (JSON Web Token) will be returned for authenticated access to other protected resources.

Request
URL: http://localhost:8080/users/login

Method: POST

Headers:Content-Type: application/json
Body:
{
    "userName": "Mahesh",
    "password": "Mahesh123"
}
Response
Status: 200 OK
{
    "Jwt_Token": "eyJhbGciOiJIUzI1NiJ9.eyJwcm9qZWN0IjoiU3ByaW5nIHNlY3VyaXR5Iiwic3ViIjoiTWFoZXNoIiwiaWF0IjoxNzMzMzM1NjYxLCJleHAiOjE3MzMzMzkyNjF9._Mr_7je-Yp2vgtJFezDOUW-drZva9ecerMGaYAoE1q8"
}


API Documentation for Weather Report Service
This document provides detailed information about the available endpoints and how to interact with the API for registering users, logging in, and getting weather reports.

Table of Contents
Register User
Login User
Get Weather Report by IP Address
Error Responses
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1. Register User
Endpoint
arduino
Copy code
POST /users/register
Description
This endpoint registers a new user by providing required user details such as userName, password, and age. The registration will be successful if all the fields are provided.

Request
URL: http://localhost:8080/users/register

Method: POST

Headers:

Content-Type: application/json
Body:

json
Copy code
{
    "userName": "Mahesh",
    "password": "Mahesh123",
    "age": 26
}
Response
Status: 201 Created

json
Copy code
{
    "message": "Created Successfully!"
}
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2. Login User
Endpoint
bash
Copy code
POST /users/login
Description
This endpoint allows a registered user to log in using their credentials. Upon successful login, a JWT (JSON Web Token) will be returned for authenticated access to other protected resources.

Request
URL: http://localhost:8080/users/login

Method: POST

Headers:

Content-Type: application/json
Body:

json
Copy code
{
    "userName": "Mahesh",
    "password": "Mahesh123"
}
Response
Status: 200 OK

json
Copy code
{
    "Jwt_Token": "eyJhbGciOiJIUzI1NiJ9.eyJwcm9qZWN0IjoiU3ByaW5nIHNlY3VyaXR5Iiwic3ViIjoiTWFoZXNoIiwiaWF0IjoxNzMzMzM1NjYxLCJleHAiOjE3MzMzMzkyNjF9._Mr_7je-Yp2vgtJFezDOUW-drZva9ecerMGaYAoE1q8"
}

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

3. Get Weather Report by IP Address

GET /weather-report/weather-by-ip
Description
This endpoint provides the current weather report for a given IP address. If no IP address is provided, the request will return the weather data for a default IP address.

Request
URL:
For specific IP: http://localhost:8080/weather-report/weather-by-ip?ip=<IP_ADDRESS>
For default IP: http://localhost:8080/weather-report/weather-by-ip
Method: GET

Headers:

Authorization: Bearer <JWT_TOKEN> (Use the token received from the login response)
Query Parameters
ip (Optional): IP address to get the weather for. If not provided, the weather for a default IP will be used.
Response
Case 1: If IP address is provided:
Status: 200 OK

{
    "data": {
        "ip": "203.200.0.0",
        "location": {
            "city": "Bengaluru",
            "country": "India"
        },
        "weather": {
            "temperature": 294.68,
            "humidity": 89,
            "description": "mist"
        }
    },
    "message": "success",
    "status": 200
}
Case 2: If IP address is not provided (default IP used):
Status: 200 OK

{
    "data": {
        "ip": "123.123.123.123",
        "location": {
            "city": "Beijing",
            "country": "China"
        },
        "weather": {
            "temperature": 269.09,
            "humidity": 34,
            "description": "clear sky"
        }
    },
    "message": "success",
    "status": 200
}

Authentication & Authorization
----------------------------------------------
To access protected endpoints like GET /weather-report/weather-by-ip, you must include a JWT token in the Authorization header.
The token can be obtained by logging in via the POST /users/login endpoint with valid credentials.
Header format:

Authorization: Bearer <JWT_TOKEN>
Example: Calling Weather Report with Authentication

HttpResponse<String> response = Unirest.get("http://localhost:8080/weather-report/weather-by-ip")
    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJwcm9qZWN0IjoiU3ByaW5nIHNlY3VyaXR5Iiwic3ViIjoiRXNod2FyIiwiaWF0IjoxNzMzMzM0OTUzLCJleHAiOjE3MzMzMzg1NTN9.R_ECIS9Wlic9zTS8pCgCwP6YJvSf2Uw70W3zqx2SIUQ")
    .asString();

Conclusion:
This API allows users to register, log in, and fetch weather data by IP address with JWT authentication. All endpoints are secured and require valid authentication for accessing protected resources. Proper error handling is implemented to ensure a user-friendly experience.



