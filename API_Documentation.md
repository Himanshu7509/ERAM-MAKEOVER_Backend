# Erum Makeover API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication

The API uses JWT (JSON Web Token) for authentication. After successful login or registration, include the token in the Authorization header for protected endpoints:

```
Authorization: Bearer <token>
```

---

## User Authentication Endpoints

### 1. User Registration

Register a new user account.

**Endpoint:** `POST /auth/signup`

**Request Body:**
```json
{
  "fullName": "string",
  "email": "string",
  "password": "string"
}
```

**Request Parameters:**

| Field    | Type   | Required | Description                    |
|----------|--------|----------|--------------------------------|
| fullName | string | Yes      | Full name of the user          |
| email    | string | Yes      | User's email address           |
| password | string | Yes      | User's password (will be hashed)|

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
{
  "token": "string",
  "email": "string",
  "role": "USER"
}
```

**Response Fields:**

| Field | Type   | Description                    |
|-------|--------|--------------------------------|
| token | string | JWT token for authentication   |
| email | string | Registered user's email        |
| role  | string | User role (always "USER")      |

**Error Responses:**

| Status Code | Description                                  |
|-------------|----------------------------------------------|
| 400         | Email already exists                         |
| 500         | Internal server error                        |

---

### 2. User Login

Authenticate a user and receive a JWT token.

**Endpoint:** `POST /auth/login`

**Request Body:**
```json
{
  "email": "string",
  "password": "string"
}
```

**Request Parameters:**

| Field    | Type   | Required | Description                    |
|----------|--------|----------|--------------------------------|
| email    | string | Yes      | User's email address           |
| password | string | Yes      | User's password                |

**Response:**

**Status Code:** `200 OK`

**Response Body (User):**
```json
{
  "token": "string",
  "email": "string",
  "role": "USER"
}
```

**Response Body (Admin):**
```json
{
  "token": "string",
  "email": "string",
  "role": "ADMIN"
}
```

**Response Fields:**

| Field | Type   | Description                    |
|-------|--------|--------------------------------|
| token | string | JWT token for authentication   |
| email | string | Authenticated user's email     |
| role  | string | User role ("USER" or "ADMIN")  |

**Error Responses:**

| Status Code | Description                                  |
|-------------|----------------------------------------------|
| 400         | Invalid credentials                          |
| 404         | User not found                               |
| 500         | Internal server error                        |

---

## Data Models

### User

| Field    | Type   | Description                    |
|----------|--------|--------------------------------|
| id       | string | Unique identifier (MongoDB ID) |
| fullName | string | Full name of the user          |
| email    | string | User's email address           |
| password | string | Hashed password                |

### Role Enum

| Value  | Description                    |
|--------|--------------------------------|
| USER   | Regular user role              |
| ADMIN  | Administrator role             |

### Contact

| Field     | Type     | Description                    |
|-----------|----------|--------------------------------|
| id        | string   | Unique identifier (MongoDB ID) |
| name      | string   | Name of the contact            |
| email     | string   | Contact email address          |
| phone     | string   | Contact phone number           |
| message   | string   | Message content                |
| createdAt | datetime | Timestamp when created         |

### DemoRegistration

| Field        | Type     | Description                         |
|--------------|----------|-------------------------------------|
| id           | string   | Unique identifier (MongoDB ID)      |
| userId       | string   | ID of the logged-in user            |
| name         | string   | Name for demo registration          |
| email        | string   | Email (auto-set from token)         |
| phone        | string   | Phone number for contact            |
| registeredAt | datetime | Timestamp when registered           |

### Profile

| Field                 | Type   | Description                         |
|-----------------------|--------|-------------------------------------|
| profileId             | string | Unique identifier (MongoDB ID)      |
| userId                | string | ID of the logged-in user            |
| fullName              | string | Full name                           |
| email                 | string | Email address                       |
| phoneNumber           | string | Phone number                        |
| dateOfBirth           | date   | Date of birth                       |
| gender                | string | Gender                              |
| city                  | string | City                                |
| state                 | string | State                               |
| pinCode               | string | PIN code                            |
| courseName            | string | Selected course name                |
| batchTiming           | string | Preferred batch timing              |
| priorExperience       | string | Prior experience (Yes/No)           |
| experienceDescription | string | Description of experience           |
| skillLevel            | string | Current skill level                 |
| whyJoin               | string | Reason for joining                  |
| careerGoal            | string | Career goal                         |
| message               | string | Additional message                  |
| ImageUrl              | string | Profile image URL                   |

---

## Error Handling

All error responses follow this format:

```json
{
  "message": "Error description"
}
```

Common HTTP Status Codes:

| Code | Meaning                                                    |
|------|------------------------------------------------------------|
| 200  | OK - Request successful                                    |
| 400  | Bad Request - Invalid input or email already exists        |
| 401  | Unauthorized - Invalid or missing token                    |
| 404  | Not Found - Resource not found                             |
| 500  | Internal Server Error - Server-side error                  |

---

## Notes

- Passwords are hashed using BCrypt before storage
- JWT tokens include user email, full name, ID, and role
- The same login endpoint works for both USER and ADMIN roles
- The system checks for ADMIN credentials first, then USER credentials during login

---

## Contact Endpoints (User)

### 1. Submit Contact Form

Submit a contact form message. This endpoint is public and does not require authentication.

**Endpoint:** `POST /contact`

**Request Body:**
```json
{
  "name": "string",
  "email": "string",
  "phone": "string",
  "message": "string"
}
```

**Request Parameters:**

| Field   | Type   | Required | Description                    |
|---------|--------|----------|--------------------------------|
| name    | string | Yes      | Name of the contact            |
| email   | string | Yes      | Contact email address          |
| phone   | string | Yes      | Contact phone number           |
| message | string | Yes      | Message content                |

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
{
  "id": "string",
  "name": "string",
  "email": "string",
  "phone": "string",
  "message": "string",
  "createdAt": "2026-02-21T10:30:00"
}
```

---

## Demo Registration Endpoints (User)

### 1. Register for Demo

Register for a demo session. User must be logged in.

**Endpoint:** `POST /demo/register`

**Authentication:** Required (JWT Token with USER role)

**Request Body:**
```json
{
  "name": "string",
  "phone": "string"
}
```

**Request Parameters:**

| Field   | Type   | Required | Description                              |
|---------|--------|----------|------------------------------------------|
| name    | string | Yes      | Name for demo registration               |
| phone   | string | Yes      | Phone number for contact                 |

**Note:** The `email` field is automatically set from the logged-in user's token.

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
{
  "id": "string",
  "userId": "string",
  "name": "string",
  "email": "string",
  "phone": "string",
  "registeredAt": "2026-02-21T10:30:00"
}
```

**Error Responses:**

| Status Code | Description                                  |
|-------------|----------------------------------------------|
| 401         | Unauthorized - Invalid or missing token      |
| 403         | Forbidden - User role required               |

---

## Course Endpoints (User)

### 1. Get All Courses

Retrieve all available courses. This endpoint is public.

**Endpoint:** `GET /courses`

**Authentication:** Not required

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
[
  {
    "courseId": "string",
    "title": "string",
    "description": "string",
    "price": 0.0,
    "duration": "string",
    "level": "string",
    "imageUrl": "string",
    "whatYouWillLearn": ["string"]
  }
]
```

---

## Profile Endpoints (User)

### 1. Create or Update Profile

Create a new profile or update existing profile for the logged-in user.

**Endpoint:** `POST /api/profiles`

**Authentication:** Required (JWT Token)

**Request Body:**
```json
{
  "fullName": "string",
  "email": "string",
  "phoneNumber": "string",
  "dateOfBirth": "1990-01-01",
  "gender": "string",
  "city": "string",
  "state": "string",
  "pinCode": "string",
  "courseName": "string",
  "batchTiming": "string",
  "priorExperience": "string",
  "experienceDescription": "string",
  "skillLevel": "string",
  "whyJoin": "string",
  "careerGoal": "string",
  "message": "string",
  "ImageUrl": "string"
}
```

**Request Parameters:**

| Field                 | Type   | Required | Description                    |
|-----------------------|--------|----------|--------------------------------|
| fullName              | string | No       | Full name                      |
| email                 | string | No       | Email address                  |
| phoneNumber           | string | No       | Phone number                   |
| dateOfBirth           | date   | No       | Date of birth (YYYY-MM-DD)     |
| gender                | string | No       | Gender                         |
| city                  | string | No       | City                           |
| state                 | string | No       | State                          |
| pinCode               | string | No       | PIN code                       |
| courseName            | string | No       | Selected course name           |
| batchTiming           | string | No       | Preferred batch timing         |
| priorExperience       | string | No       | Prior experience (Yes/No)      |
| experienceDescription | string | No       | Description of experience      |
| skillLevel            | string | No       | Current skill level            |
| whyJoin               | string | No       | Reason for joining             |
| careerGoal            | string | No       | Career goal                    |
| message               | string | No       | Additional message             |
| ImageUrl              | string | No       | Profile image URL              |

**Note:** The `userId` field is automatically set from the logged-in user's token.

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
{
  "profileId": "string",
  "userId": "string",
  "fullName": "string",
  "email": "string",
  "phoneNumber": "string",
  "dateOfBirth": "1990-01-01",
  "gender": "string",
  "city": "string",
  "state": "string",
  "pinCode": "string",
  "courseName": "string",
  "batchTiming": "string",
  "priorExperience": "string",
  "experienceDescription": "string",
  "skillLevel": "string",
  "whyJoin": "string",
  "careerGoal": "string",
  "message": "string",
  "ImageUrl": "string"
}
```

---

### 2. Get Profile

Retrieve the profile of the logged-in user.

**Endpoint:** `GET /api/profiles`

**Authentication:** Required (JWT Token)

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
{
  "profileId": "string",
  "userId": "string",
  "fullName": "string",
  "email": "string",
  "phoneNumber": "string",
  "dateOfBirth": "1990-01-01",
  "gender": "string",
  "city": "string",
  "state": "string",
  "pinCode": "string",
  "courseName": "string",
  "batchTiming": "string",
  "priorExperience": "string",
  "experienceDescription": "string",
  "skillLevel": "string",
  "whyJoin": "string",
  "careerGoal": "string",
  "message": "string",
  "ImageUrl": "string"
}
```

**Error Responses:**

| Status Code | Description                                  |
|-------------|----------------------------------------------|
| 401         | Unauthorized - Invalid or missing token      |
