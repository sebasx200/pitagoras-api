# CES3 Exam
This is the CES3 exam about rest api using java EE and Tomcat repository.

# Summary
In this exam you can manage courses, adding them, listing them, and filter them by some params.

# Project Structure

The idea of the project folder management is make it modular and easy to learn.
To achieve that the structure is the following:

```plaintext
├───main
│   ├───java
│   │   └───com
│   │       └───ces3
│   │           └───exam
│   │               └───pitagorasapi
│   │                   │   HelloServlet.java
│   │                   │
│   │                   ├───dao
│   │                   │       CourseDAO.java
│   │                   │       CourseDAOImpl.java
│   │                   │
│   │                   ├───dto
│   │                   ├───model
│   │                   │       Course.java
│   │                   │
│   │                   ├───service
│   │                   │       CourseService.java
│   │                   │
│   │                   ├───servlet
│   │                   │       CourseServlet.java
│   │                   │
│   │                   └───utils
│   │                           UtilMethods.java
│   │
│   ├───resources
│   └───webapp
│       │   index.jsp
│       │
│       └───WEB-INF
│               beans.xml
│               web.xml
│
└───test
    ├───java
    └───resources
```

---

# Folder and content explanation

### **`servlet`**
In this folder is located the Servlet class which handles the https request made to the different endpoint configured for the API.

### **`dao`**
Folder that contains the DAO interface and the class CourseDAOImpl.

### **`model`**
Here is the entity Course which handles the structure required for the wanted data.

### **`service`**
This contains the class that interact wit the DAO implementations and handles the business logic.

### **`utils`**
This saves some util methods to handle a way to iterate over the request body.

### **`remaining folders`**
Contains some folders for frontend purposes and additional configuration

The project was created using intellij selecting Java EE template -> web application -> with maven using java 22 JDK.

---

## **`API documentation`**

#### **`courses`**
for managing courses (Create and List)

---

## Base URL

`http://localhost:8085/pitagoras_api/`

---
### Courses endpoint

### 1. Retrieve all courses

**`GET`** /courses

**Response:**

```json
[
  {
    "id": 1,
    "name": "Excel basico",
    "code": "EX120",
    "teacher": "Hernan Hernandez",
    "maxCapacity": 30,
    "enrolledStudents": 23,
    "faculty": "Engineering",
    "prerequisites": [],
    "level": 1,
    "startDate": "2025-10-03"
  },
  {
    "id": 2,
    "name": "Excel intermedio",
    "code": "EX121",
    "teacher": "Hernan Hernandez",
    "maxCapacity": 30,
    "enrolledStudents": 23,
    "faculty": "Engineering",
    "prerequisites": [
      "EX120"
    ],
    "level": 2,
    "startDate": "2025-10-03"
  },
  {
    "id": 3,
    "name": "Excel avanzado",
    "code": "EX122",
    "teacher": "Hernan Hernandez",
    "maxCapacity": 30,
    "enrolledStudents": 23,
    "faculty": "Engineering",
    "prerequisites": [
      "EX120",
      "EX121"
    ],
    "level": 3,
    "startDate": "2025-10-03"
  }
]
```

### 2. Retrieve course by faculty name

**`GET`** /courses/faculty?name=X (it is not necessary full coincidence)

**Response:**

```json
[
    {
        "id": 1,
        "name": "Excel basico",
        "code": "EX120",
        "teacher": "Hernan Hernandez",
        "maxCapacity": 30,
        "enrolledStudents": 23,
        "faculty": "Engineering",
        "prerequisites": [],
        "level": 1,
        "startDate": "2025-10-03"
    },
    {
        "id": 2,
        "name": "Excel intermedio",
        "code": "EX121",
        "teacher": "Hernan Hernandez",
        "maxCapacity": 30,
        "enrolledStudents": 23,
        "faculty": "Engineering",
        "prerequisites": [
            "EX120"
        ],
        "level": 2,
        "startDate": "2025-10-03"
    },
    {
        "id": 3,
        "name": "Excel avanzado",
        "code": "EX122",
        "teacher": "Hernan Hernandez",
        "maxCapacity": 30,
        "enrolledStudents": 23,
        "faculty": "Engineering",
        "prerequisites": [
            "EX120",
            "EX121"
        ],
        "level": 3,
        "startDate": "2025-10-03"
    }
]
```

### 3. Retrieve course by id

**`GET`** /courses?id=1

**Response:**

```json
{
  "value": {
    "id": 1,
    "name": "Excel basico",
    "code": "EX120",
    "teacher": "Hernan Hernandez",
    "maxCapacity": 30,
    "enrolledStudents": 23,
    "faculty": "Engineering",
    "prerequisites": [],
    "level": 1,
    "startDate": "2025-10-03"
  }
}
```

### 4. Retrieve the route necessary for taking a course (prerequisites)

**`GET`** /courses/route?code=EX122 (code = course code)

**Response:**

```json
[
  {
    "id": 1,
    "name": "Excel basico",
    "code": "EX120",
    "teacher": "Hernan Hernandez",
    "maxCapacity": 30,
    "enrolledStudents": 23,
    "faculty": "Engineering",
    "prerequisites": [],
    "level": 1,
    "startDate": "2025-10-03"
  },
  {
    "id": 2,
    "name": "Excel intermedio",
    "code": "EX121",
    "teacher": "Hernan Hernandez",
    "maxCapacity": 30,
    "enrolledStudents": 23,
    "faculty": "Engineering",
    "prerequisites": [
      "EX120"
    ],
    "level": 2,
    "startDate": "2025-10-03"
  }
]
```

### 5. Post a new course

**`POST`** /courses (prerequisites: if a course does not have prerequisites, this can be empty)

**Response:**

```json
{
  "id": 1,
  "name": "Excel basico",
  "code": "EX120",
  "teacher": "Hernan Hernandez",
  "maxCapacity": 30,
  "enrolledStudents": 23,
  "faculty": "Engineering",
  "prerequisites": [],
  "level": 1,
  "startDate": "2025-10-03"
}
```
### 6. Post a new course with prerequisites

**`POST`** /courses (prerequisites: if a course does not have prerequisites, this can be empty)

**Response:**

```json
{
  "id": 3,
  "name": "Excel avanzado",
  "code": "EX122",
  "teacher": "Hernan Hernandez",
  "maxCapacity": 30,
  "enrolledStudents": 23,
  "faculty": "Engineering",
  "prerequisites": ["EX120","EX121"],
  "level": 3,
  "startDate": "2025-10-03"
}
```

---

The project was tested using Tomcat 9.0.100 as server