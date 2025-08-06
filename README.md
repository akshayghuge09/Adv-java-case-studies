# Hotel Booking Application - User Service with JWT Security

This is a Spring Boot application that provides user authentication and management services with JWT security for a hotel booking system.

## Project Structure

```
src/main/java/com/cdac/
├── Application.java                          # Main Spring Boot application
├── controller/
│   └── UserSignUpSignInController.java      # REST endpoints for user operations
├── custom_exception_handler/
│   └── GlobalExceptionHandler.java          # Global exception handling
├── custom_exceptions/
│   ├── InvalidCredentialsException.java     # Authentication exceptions
│   ├── UserAlreadyExistsException.java      # Duplicate user exceptions
│   └── UserNotFoundException.java           # User not found exceptions
├── dto/
│   ├── ApiResponse.java                     # Standardized API response wrapper
│   ├── AuthRequest.java                     # Login request DTO
│   ├── AuthResponse.java                    # Authentication response DTO
│   ├── BaseDTO.java                        # Base DTO with common fields
│   ├── UserReqDTO.java                     # User registration request DTO
│   └── UserRespDTO.java                    # User response DTO
├── entities/
│   ├── BaseEntity.java                      # Base entity with audit fields
│   ├── UserEntity.java                      # User entity implementing UserDetails
│   └── UserRole.java                        # User role enum
├── repository/
│   └── UserRepository.java                  # JPA repository for user operations
├── security/
│   ├── CustomJwtFilter.java                # JWT authentication filter
│   ├── CustomUserDetailsServiceImpl.java    # UserDetailsService implementation
│   ├── JwtUtils.java                       # JWT utility methods
│   └── SecurityConfiguration.java           # Spring Security configuration
└── service/
    ├── UserService.java                     # User service interface
    └── UserServiceImpl.java                 # User service implementation
```

## Features

- **User Registration**: Secure user registration with password encryption
- **User Authentication**: JWT-based authentication with login
- **Role-Based Access Control**: Support for CUSTOMER, HOTEL_OWNER, and ADMIN roles
- **Password Management**: BCrypt password encoding and change password functionality
- **Global Exception Handling**: Standardized error responses
- **Input Validation**: Bean validation for request DTOs
- **CORS Support**: Cross-origin resource sharing configuration

## Technology Stack

- **Spring Boot 3.2.0**: Main framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database operations
- **MySQL**: Database
- **JWT**: Token-based authentication
- **BCrypt**: Password hashing
- **Lombok**: Code reduction
- **Maven**: Build tool

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Setup Instructions

### 1. Database Setup

Create a MySQL database or update the application.properties with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

### 2. JWT Configuration

Update the JWT secret in application.properties:

```properties
jwt.secret=your-secret-key-here-make-it-very-long-and-secure-for-production
jwt.expiration=86400000
```

### 3. Build and Run

```bash
# Navigate to project directory
cd hotel-booking-app

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api/v1`

## API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/auth/register` | Register a new user | Public |
| POST | `/auth/login` | Login user | Public |
| GET | `/auth/profile` | Get current user profile | Authenticated |
| POST | `/auth/change-password` | Change user password | Authenticated |

### Admin Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/auth/users` | Get all users | ADMIN only |
| GET | `/auth/users/{id}` | Get user by ID | ADMIN only |
| PUT | `/auth/users/{id}` | Update user | ADMIN only |
| DELETE | `/auth/users/{id}` | Delete user | ADMIN only |

## Request/Response Examples

### User Registration

**Request:**
```json
POST /api/v1/auth/register
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "contactNo": "1234567890",
    "role": "CUSTOMER"
}
```

**Response:**
```json
{
    "success": true,
    "message": "User registered successfully",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9...",
        "tokenType": "Bearer",
        "email": "john@example.com",
        "role": "CUSTOMER",
        "name": "John Doe"
    }
}
```

### User Login

**Request:**
```json
POST /api/v1/auth/login
{
    "email": "john@example.com",
    "password": "password123"
}
```

**Response:**
```json
{
    "success": true,
    "message": "Login successful",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9...",
        "tokenType": "Bearer",
        "email": "john@example.com",
        "role": "CUSTOMER",
        "name": "John Doe"
    }
}
```

## Security Features

- **JWT Authentication**: Stateless authentication using JSON Web Tokens
- **Password Encryption**: BCrypt password hashing
- **Role-Based Authorization**: @PreAuthorize annotations for method-level security
- **CORS Configuration**: Cross-origin resource sharing support
- **Input Validation**: Bean validation for request validation
- **Global Exception Handling**: Centralized error handling

## User Roles

- **CUSTOMER**: Can register, login, view profile, and change password
- **HOTEL_OWNER**: Same as CUSTOMER (future hotel management features)
- **ADMIN**: Full access to all endpoints including user management

## Error Handling

The application provides standardized error responses:

```json
{
    "success": false,
    "message": "Error message",
    "data": null
}
```

Common error scenarios:
- User already exists (409 Conflict)
- User not found (404 Not Found)
- Invalid credentials (401 Unauthorized)
- Validation errors (400 Bad Request)
- Server errors (500 Internal Server Error)

## Testing

You can test the API endpoints using tools like:
- Postman
- cURL
- Swagger UI (if configured)

## Future Enhancements

- Email verification
- Password reset functionality
- Refresh token implementation
- Rate limiting
- Audit logging
- Unit and integration tests

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.