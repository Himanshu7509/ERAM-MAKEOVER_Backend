# Erum Makeover - Admin API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication

All admin endpoints require **ADMIN** role authentication. Include the JWT token in the Authorization header:

```
Authorization: Bearer <admin_token>
```

---

## Contact Management Endpoints

### 1. Get All Contact Messages

Retrieve all contact form submissions.

**Endpoint:** `GET /contact/getContacts`

**Authentication:** Required (ADMIN role)

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
[
  {
    "id": "string",
    "name": "string",
    "email": "string",
    "phone": "string",
    "message": "string",
    "createdAt": "2026-02-21T10:30:00"
  }
]
```

---

### 2. Get Contact by ID

Retrieve a specific contact message by ID.

**Endpoint:** `GET /contact/getContacts/{id}`

**Authentication:** Required (ADMIN role)

**Path Parameters:**

| Field | Type   | Required | Description                    |
|-------|--------|----------|--------------------------------|
| id    | string | Yes      | Contact message ID             |

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
"Message deleted successfully"
```

---

### 3. Delete Contact Message

Delete a contact message by ID.

**Endpoint:** `DELETE /contact/getContacts/{id}`

**Authentication:** Required (ADMIN role)

**Path Parameters:**

| Field | Type   | Required | Description                    |
|-------|--------|----------|--------------------------------|
| id    | string | Yes      | Contact message ID             |

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
"Message deleted successfully"
```

---

### 4. Search Contacts by Name

Search contact messages by name.

**Endpoint:** `GET /contact/search`

**Authentication:** Required (ADMIN role)

**Query Parameters:**

| Field | Type   | Required | Description                    |
|-------|--------|----------|--------------------------------|
| name  | string | Yes      | Name to search for             |

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
[
  {
    "id": "string",
    "name": "string",
    "email": "string",
    "phone": "string",
    "message": "string",
    "createdAt": "2026-02-21T10:30:00"
  }
]
```

---

## Demo Registration Endpoints

### 1. Get All Demo Registrations

Retrieve all demo registration requests.

**Endpoint:** `GET /demo/all`

**Authentication:** Required (ADMIN role)

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
[
  {
    "id": "string",
    "userId": "string",
    "name": "string",
    "email": "string",
    "phone": "string",
    "registeredAt": "2026-02-21T10:30:00"
  }
]
```

---

## Course Management Endpoints

### 1. Add New Course

Create a new course.

**Endpoint:** `POST /courses/admin`

**Authentication:** Required (ADMIN role)

**Request Body:**
```json
{
  "title": "string",
  "description": "string",
  "price": 0.0,
  "duration": "string",
  "level": "string",
  "imageUrl": "string",
  "whatYouWillLearn": ["string"]
}
```

**Request Parameters:**

| Field            | Type    | Required | Description                    |
|------------------|---------|----------|--------------------------------|
| title            | string  | Yes      | Course title                   |
| description      | string  | Yes      | Course description             |
| price            | number  | Yes      | Course price                   |
| duration         | string  | Yes      | Course duration (e.g., "6 Months") |
| level            | string  | Yes      | Course level (Beginner/Advanced) |
| imageUrl         | string  | No       | Course banner image URL        |
| whatYouWillLearn | array   | No       | List of learning outcomes      |

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
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
```

---

### 2. Update Course

Update an existing course.

**Endpoint:** `PUT /courses/admin/{id}`

**Authentication:** Required (ADMIN role)

**Path Parameters:**

| Field | Type   | Required | Description                    |
|-------|--------|----------|--------------------------------|
| id    | string | Yes      | Course ID                      |

**Request Body:**
```json
{
  "title": "string",
  "description": "string",
  "price": 0.0,
  "duration": "string",
  "level": "string",
  "imageUrl": "string",
  "whatYouWillLearn": ["string"]
}
```

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
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
```

---

### 3. Delete Course

Delete a course by ID.

**Endpoint:** `DELETE /courses/admin/{id}`

**Authentication:** Required (ADMIN role)

**Path Parameters:**

| Field | Type   | Required | Description                    |
|-------|--------|----------|--------------------------------|
| id    | string | Yes      | Course ID                      |

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
"Course deleted successfully"
```

---

## Enrollment Management Endpoints

### 1. Get All Enrollments

Retrieve all course enrollments.

**Endpoint:** `GET /enrollments/admin`

**Authentication:** Required (ADMIN role)

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```json
[
  {
    "Enrollmeentid": "string",
    "userId": "string",
    "fullName": "string",
    "email": "string",
    "courseId": "string",
    "phone": "string",
    "message": "string",
    "enrolledAt": "2026-02-21T10:30:00"
  }
]
```

---

## Image Upload Endpoints

### 1. Upload Image

Upload an image to S3 storage.

**Endpoint:** `POST /api/images/upload`

**Authentication:** Required (ADMIN role)

**Content-Type:** `multipart/form-data`

**Request Body:**

| Field | Type   | Required | Description                    |
|-------|--------|----------|--------------------------------|
| file  | file   | Yes      | Image file to upload           |

**Response:**

**Status Code:** `200 OK`

**Response Body:**
```
https://s3-bucket-url/CourseImage/filename.jpg
```

**Error Response:**

**Status Code:** `500 Internal Server Error`

**Response Body:**
```
Upload failed: [error message]
```

---

## Data Models

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
| email        | string   | Email address                       |
| phone        | string   | Phone number for contact            |
| registeredAt | datetime | Timestamp when registered           |

### Course

| Field            | Type    | Description                         |
|------------------|---------|-------------------------------------|
| courseId         | string  | Unique identifier (MongoDB ID)      |
| title            | string  | Course title                        |
| description      | string  | Course description                  |
| price            | number  | Course price                        |
| duration         | string  | Course duration                     |
| level            | string  | Course level (Beginner/Advanced)    |
| imageUrl         | string  | Course banner image URL             |
| whatYouWillLearn | array   | List of learning outcomes           |

### Enrollment

| Field        | Type     | Description                         |
|--------------|----------|-------------------------------------|
| Enrollmeentid| string   | Unique identifier (MongoDB ID)      |
| userId       | string   | ID of the enrolled user             |
| fullName     | string   | Full name of the user               |
| email        | string   | User email address                  |
| courseId     | string   | ID of the enrolled course           |
| phone        | string   | Contact phone number                |
| message      | string   | Enrollment message                  |
| enrolledAt   | datetime | Timestamp when enrolled             |

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
| 401  | Unauthorized - Invalid or missing token                    |
| 403  | Forbidden - Admin role required                            |
| 404  | Not Found - Resource not found                             |
| 500  | Internal Server Error - Server-side error                  |

---

## Notes

- All admin endpoints require ADMIN role authentication
- Contact messages can be viewed, searched, and deleted by admins
- Demo registrations are read-only for admins
- Course CRUD operations are restricted to admins
- Image uploads are stored in S3 under the "CourseImage" folder
