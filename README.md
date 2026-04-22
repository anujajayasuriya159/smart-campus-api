# Smart Campus Sensor & Room Management API

## 1. Overview of the API project

This project(Smart Campus API) is a RESTful wed project developed by using Java and JAX-RS and it follows the standerd HTTP methods, stateless communication and resource based URLs. The resources are used for rooms, sensors and sensor readings(sub resources)

### Relationships as follows:

* A room can contain multiple sensors(sensor readings)
* A sensor can be only belongs to one room(unique)
* A sensor has multiple readings

---

## 2. Instructions to Build and launch the server

1. Clone the repository

2. Open the project in Apache Netbeans

3. Requirments:

   * Java 17 is installed
   * Maven is installed
     
4. Starting the server:

   * Open Main.java file
   * Click **Run**

6. The project server:
   
   * http://localhost:8080/api/v1/

---

## 3. Sample curl Commands demonstration

### 1. Creating a Room

```
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d "{\"id\":\"LAB-301\",\"name\":\"Library\",\"capacity\":50,\"sensorIds\":[]}
```

### 2. Getting All the Rooms

```
curl http://localhost:8080/api/v1/rooms
```

### 3. Creating a Sensor

```
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d "{\"id\":\"TEMP-001\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":20,\"roomId\":\"LAB-301\"}"
```

### 4. Filtering Sensors by type

```
curl "http://localhost:8080/api/v1/sensors?type=Temperature"
```

### 5. Adding Sensor Readings

```
curl -X POST http://localhost:8080/api/v1/sensors/TEMP-001/readings \
-H "Content-Type: application/json" \
-d "{\"id\":\"T1\",\"timestamp\":1234567890,\"value\":26}"
```

---

## 4. Answers to Coursework Questions

### Question 1 (Part 1)

Answer:

In JAX-RS a new session is created for each individual HTTP request because in JAX-RS it automatically creates classes with every HTTP request. As a result of this, it optimizes the the concurrency safety.

### Question 2 (Part 1)

Answer:

Compared between HATEOAS and static documentsion, the HATEOAS of preferred because it enables the ability to contain links in the responses for the clients can seamlessly navigate betweenn sections and establing the APIs more flexible.

### Question 3 (Part 2)

Answer:

The choice between returning IDs and returnig full room object depends on the situation. When returning only IDs reduces the network traffic but the trade off is it requires additional requests for the details. Returning the full room object improves client usability but the trade off is it elevate the response size.

### Question 4 (Part 2)

Answer:

According to my implementation the DELETE operation is idempotent. When the client first delets a specific room it removes the room and attempting to delete the same room again does not alter the system state.

### Question 5 (Part 3)

Answer:

In this situation the JAX-RS handles the problem by returning a 415 Unsupported Media Type because the @Consume (MediaType.APPLICATION_JSON) only approves JSON format files.

### Question 6 (Part 3)

Answer: 

Normally the path parameters are fixed resorces and @QueryParam is significantly better because the are flexible and keeps the URLs clean.

### Question 7 (Part 4)

Answer:

Conpaired to defining all the nested paths, the sub resource locators are markedly better because they isolates logic into separate classes which enhances the structure and code maintainability.

### Question 8 (Part 5)

Answer: 

The HTTP 422 is often considered to be more accurate than the standered HTTP 402 is because the HTTP 402 is used to validate a request and check does it include a unacceptable data(unacceptable room ID) and the HTTP 404 is for only validating unavailable resources  

### Question 9 (Part 5)

Answer:

Exposing these details such as class names details can reduce the security of the system to the attackers.To prevent this exposure of internal JAVA stack traces to external API consumers a GlobalExceptionMapper was used.

### Question 10 (Part 5)

Answer:

To reduce the code duplication and maintainability of the code itself.
