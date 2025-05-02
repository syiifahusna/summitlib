# Summit Lib

A RESTful Library API that enables users to browse books by category, search by titles, authors and isbn, view detailed book information, register and authenticate accounts.


## Rest API

REST API endpoints used in the **SummitLib** application. It includes basic endpoints for user registration, authentication, and book-related functionalities for both public and admin users.

---

## 🔗 Base URL

http://127.0.0.1:8080/summitlib/api

---

## Public Endpoints

These endpoints do not require authentication.

### User Management

#### Register a New User
- **URL**: `/public/register`
- **Method**: `POST`
- **Request Body**:
```json
{
    "email": "example@mail.com",
    "username": "username",
    "password": "password123"
}
```
- **Description**: Creates a new user account with the provided details.

#### User Login
- **URL**: `/public/login`
- **Method**: `POST`
- **Request Body**:
```json
{
    "username": "username",
    "password": "password123"
}
```
- **Description**: Authenticates a user and returns a JWT token for accessing protected endpoints.

### Book Operations

#### Get All Books
- **URL**: `/public/book/all`
- **Method**: `GET`
- **Description**: Retrieves a list of all books in the library.

#### Get Recommended Books
- **URL**: `/public/book/recommended`
- **Method**: `GET`
- **Description**: Retrieves a list of recommended books.

#### Get Book Categories
- **URL**: `/public/book/categories`
- **Method**: `GET`
- **Description**: Retrieves all available book categories.

#### Get Books by Category ID
- **URL**: `/public/book/categories/{categoryId}`
- **Method**: `GET`
- **Description**: Retrieves category details
- **Example**: `/public/book/categories/9`

#### Search for Books
- **URL**: `/public/book/find`
- **Method**: `GET`
- **Query Parameters**:
  - `searchTerm`: The term to search for
  - `type`: The type of search (e.g., "title")
  - `offset`: The starting position for paginated results
- **Example**: `/public/book/find?searchTerm=Truth&type=title&offset=0`
- **Description**: Searches for books based on the provided search term and type.

### Other Operations

#### Get Advertisements
- **URL**: `/public/advertisement`
- **Method**: `GET`
- **Description**: Retrieves current library advertisements.

## Protected Endpoints

These endpoints require authentication using a JWT token in the Authorization header.

### Admin Book Operations

#### Add Multiple Books
- **URL**: `/admin/book/saves`
- **Method**: `POST`
- **Headers**:
  - `Authorization`: `Bearer {jwt_token}`
- **Request Body**: Array of book objects
```json
[
  {
    "title": "Book Title",
    "authors": [{"name": "Author Name"}],
    "desc": "Book description",
    "categoryId": 8,
    "edition": 1,
    "language": "English",
    "isbn10": "1234567890",
    "isbn13": "9781234567890",
    "publisherId": 6,
    "pages": 300
  },
  ...
]
```
- **Description**: Adds multiple books to the library at once.

#### Add a Single Book
- **URL**: `/admin/book/save`
- **Method**: `POST`
- **Headers**:
  - `Authorization`: `Bearer {jwt_token}`
- **Request Body**: Single book object
```json
{
  "title": "Book Title",
  "authors": [{"name": "Author Name"}],
  "desc": "Book description",
  "categoryId": 8,
  "edition": 1,
  "language": "English",
  "isbn10": "1234567890",
  "isbn13": "9781234567890",
  "publisherId": 6,
  "pages": 300
}
```
- **Description**: Adds a single book to the library.

## Authentication

For protected endpoints, include the JWT token in the Authorization header:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## Response Format

API responses are returned in JSON format. Successful responses typically include the requested data, while error responses include an error message.



